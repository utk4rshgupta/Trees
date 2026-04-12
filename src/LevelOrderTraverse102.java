
import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LevelOrderTraverse102 - Implements level order (breadth-first) traversal of a binary tree
 * This class traverses the tree level by level and returns a list of lists containing node values
 */
public class LevelOrderTraverse102 {

    /**
     * levelOrder - Performs level order traversal on a binary tree
     * @param root - The root node of the binary tree
     * @return List<List<Integer>> - Each inner list contains all values at that level
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Initialize result list to store levels
        List<List<Integer>> res = new ArrayList<>();
        
        // Base case: if tree is empty, return empty result
        if(root==null) return res;
        
        // Create a queue for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        // Continue until queue is empty
        while(!queue.isEmpty()){
            // Get the number of nodes at current level
            int level = queue.size();
            // Create list to store current level's node values
            List<Integer> curr = new ArrayList<>(level);
            // Process all nodes at current level
            for(int i =0;i<level;i++){
                // Remove node from front of queue
                TreeNode currentNode = queue.poll();
                // Add current node's value to current level list
                curr.add(currentNode.val);
                // Add left child to queue if it exists
                if(currentNode.left!= null){
                    queue.offer(currentNode.left);
                }
                // Add right child to queue if it exists
                if(currentNode.right!= null){
                    queue.offer(currentNode.right);
                }
            }
            // Add current level to result
            res.add(curr);
        }
        
        // Return the level order traversal
        return res;
    }
}
