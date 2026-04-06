// Binary Search Tree implementation
public class AVL {
    // Inner class for tree nodes
    public class Node{
        // Fields for node value, height, left and right children
        private int value;
        private int height;
        private Node left ;
        private Node right;

        // Constructor to initialize a node with a value
        public Node(int value){
            this.value = value;
        }
        // Getter for the node's value
        public int getValue(){
            return value;
        }
    }
    private Node root;
    // Default constructor for BST
    public AVL(){

    }
    // Returns the height of the given node, -1 if null
    public int height(Node node){
        if(node==null){
            return -1;
        }
        return node.height;
    }

    // Checks if the tree is empty
    public boolean isEmpty(){
        return root==null;
    }


    // Displays the tree starting from root
    public void display(){
        display(this.root,"Root Node : " );
    }
    // Recursive helper to display the tree with details
    private void display(Node node , String details){
        if(node == null) return ;
        System.out.println(details + node.value);
        display(node.left,"Left child of "+ node.value+ ": ");
        display(node.right,"Right child of "+ node.value + ": ");
    }

    // Pretty display the tree in a tree-like structure
    public void prettyDisplay() {
        prettyDisplay(root, "", false);
    }
    // Recursive helper for pretty display
    private void prettyDisplay(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.value);
            prettyDisplay(node.left, prefix + (isLeft ? "│   " : "    "), true);
            prettyDisplay(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    // Inserts a value into the BST
    public void insert (int value){
        root = insert(value,root);
    }

    // Recursive helper to insert a value into the subtree rooted at node
    private Node insert(int value , Node node ){
        if(node == null){
            node = new Node(value);
            return node;
        }
        if(value< node.value){
            node.left = insert(value,node.left);
        }
        if(value > node.value){
            node.right = insert(value,node.right);
        }
        node.height=Math.max(height(node.left),height(node.right))+1;
        return rotate(node);

    }
    private Node rotate(Node node){
        if(height(node.left )-height(node.right)>1){

            //left heavy
            if(height(node.left.left) - height(node.left.right)>=0){

                //left left case
                return rightRotate(node);
            }
            if(height(node.left.left)- height(node.left.right)< 0){

                //left right case
                node.left = leftRotate(node.left);

                return rightRotate(node);
            }
        }
        if(height(node.left )-height(node.right) < -1){

            //right heavy
            if(height(node.right.left) - height(node.right.right) < 0){

                //right right case
                return leftRotate(node);
            }
            if(height(node.right.left)- height(node.right.right) > 0){

                //right left case
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    public Node rightRotate(Node p){
        Node c = p.left;
        Node t = c.right;

        c.right=p;
        p.left=t;
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;
        return c;
    }

    public Node leftRotate(Node c){

        Node p = c.right;
        Node t = p.left;

        p.left=c;
        c.right=t;

        c.height = Math.max(height(c.left), height(c.right)) + 1;  // ✅ c first
        p.height = Math.max(height(p.left), height(p.right)) + 1;  // ✅ then p
        return p;
    }



    // Inserts all values from the array into the BST
    public void populate(int[] nums){
        for(int i=0;i<nums.length;i++){
            this.insert(nums[i]);
        }
    }


    // Checks if the subtree rooted at node is balanced
    private boolean balanced(Node node){
        if(node == null) return true;
        return Math.abs(height(node.left)-height(node.right))<=1 && balanced(node.left) && balanced(node.right);
    }


    // Populates the BST with values from a sorted array to create a balanced tree
    public void populateSorted(int[] nums){
        populateSorted(nums,0,nums.length);
    }
    // Recursive helper to populate from sorted array using binary search approach
    private void  populateSorted(int[] nums,int start,int end){
        if (start >= end) {
            return;
        }
        int mid= (start+end)/2;
        this.insert(nums[mid]);

        populateSorted(nums,start,mid);
        populateSorted(nums,mid+1,end);

    }
    public void preOrcder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value + " ");

    }

    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.value + " ");
        inOrder(node.right);
    }

    // Main method to test the BST
    public static void main(String args[]){
        AVL tree = new AVL();
        for(int i =0;i<1000;i++){
            tree.insert(i);
        }
        System.out.println(tree.height(tree.root));
//        tree.populate();
//        tree.prettyDisplay();
    }

}
