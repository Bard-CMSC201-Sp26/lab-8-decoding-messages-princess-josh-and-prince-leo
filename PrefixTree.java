public class PrefixTree<Value extends Comparable<Value> >{

    private Node root;
    private int N;

    private class Node {
        private Value val;
        private Node left, right;

        public Node(Value val) {
            this.val = val;
        }
    }

    
    public PrefixTree() {
        // empty BST
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
}