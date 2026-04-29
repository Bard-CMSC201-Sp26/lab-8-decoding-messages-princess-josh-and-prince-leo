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
    // constructor that reads preorder traversal and reconstructs tree
    public PrefixTree(String preorder) {
        this.root = buildTree(preorder, new int[] { 0 });
    }

private Node buildTree(String preorder, int[]index) {
    if (index[0] >= preorder.length()){
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
        System.out.println(current.val + "     " + encoding.length() + "     " + encoding);
        return;
    }
    getEncodings(current.left, encoding + "0");
    getEncodings(current.right, encoding + "1");
}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String p = sc.nextLine();
        String b = sc.nextLine();
        PrefixTree Wtree = new PrefixTree(p);

    }

}