public class PrefixTree<Value extends Comparable<Value> >{

    private Node root;
    private int N;

    private class Node {
        private Value val;
        private Node left, right;

        public Node(Value val) {
            this.val = val;
            this.left=null;
            this.right=null;
        }
    }
//constructor that reads preorder traversal and reconstructs tree
public PrefixTree(String preorder){
    this.root=buildTree(preorder, new int[]{0});
}
private Node buildTree(preorder, int[]index);{
    if (index[0]>=preorder.length()){
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
    public PrefixTree() {
        private Node root;
    }

    private Value get(Node x, Value val) {
        if (x == null)
            return null;
        int cmp = val.compareTo(x.val);
        if (cmp < 0)
            return get(x.left, val);
        else if (cmp > 0)
            return get(x.right, val);
        else
            return x.val;
    }
    private Node BuildTree(preorder, int[]index){
        
    }
}