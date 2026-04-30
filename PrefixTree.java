import java.util.Scanner;

public class PrefixTree {

    private Node root;
    private int N;

    private class Node {
        private char val;
        private Node left, right;

        public Node(char val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public PrefixTree() {
    }

    // constructor reconstructs tree
    public PrefixTree(String preorder) {
        this.root = buildTree(preorder, new int[] { 0 });
    }

    private Node buildTree(String preorder, int[] index) {
        if (index[0] >= preorder.length()) {
            return null;
        }

        char ch = preorder.charAt(index[0]);
        index[0]++;

        Node node = new Node(ch);

        if (ch == '*') {
            node.left = buildTree(preorder, index);
            node.right = buildTree(preorder, index);
        }

        return node;
    }

    public void getEncodings() {
        System.out.println("character     bits      encoding");
        System.out.println("----------------------------------");
        getEncodings(root, "");
    }

    private void getEncodings(Node current, String encoding) {
        if (current.val != '*') {
            System.out.println("    " + current.val + "           " + encoding.length() + "           " + encoding);
            return;
        }
        getEncodings(current.left, encoding + "0");
        getEncodings(current.right, encoding + "1");
    }

    //decoding message
    public void decode(String compressedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        int bitsRead = 0;
        
        Node current = root;
        
        // process each bit
        for (int i = 0; i < compressedMessage.length(); i++) {
            bitsRead++;
            char bit = compressedMessage.charAt(i);
            
            // traverse tree based on bit
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }
            
            // if we've reached a leaf node (not '*') we found character
            if (current.val != '*') {
                decodedMessage.append(current.val);
                current = root; // Reset to root for next character
            }
        }
        
        String decodedString = decodedMessage.toString();
        int numCharacters = decodedString.length();
        
        //calculate compression ratio, assume 8 bits per character uncompressed
        int uncompressedBits = numCharacters * 8;
        double compressionRatio = (bitsRead * 100.0) / uncompressedBits;
        
        //print decoded message
        System.out.println("\n" + decodedString);
        System.out.println("\nNumber of bits: " + bitsRead);
        System.out.println("Number of characters: " + numCharacters);
        System.out.println("Compression ratio: " + compressionRatio + " %");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //read preorder traversal of the tree
        String preorder = sc.nextLine();
        //read compressed msg
        String compressedMessage = sc.nextLine();
        
        //build prefix tree
        PrefixTree tree = new PrefixTree(preorder);
        
        //display encodings
        tree.getEncodings();
        
        //decode and display results
        tree.decode(compressedMessage);
        
        sc.close();
    }
}