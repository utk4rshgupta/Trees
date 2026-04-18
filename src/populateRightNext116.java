public class populateRightNext116 {
    public Node connect(Node root){
        if(root == null){
            return null;
        }
        Node leftMost = root;
        while(leftMost.left != null){
            Node curr = leftMost;
            while(curr != null)ff{
                curr.left.next =curr.right;
                if(curr.next != null){
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }
}
