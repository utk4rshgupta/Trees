
public class LevelOrderTraverseinTree102 {

    //same as level order traverse

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int level = queue.size();
            double avgLevel = 0;
            for(int i =0;i<level;i++){
                TreeNode currentNode = queue.poll();
                avgLevel += currentNode.val;
                if(currentNode.left!= null){
                    queue.offer(currentNode.left);
                }
                if(currentNode.right!= null){
                    queue.offer(currentNode.right);
                }
            }
            avgLevel = avgLevel/level;
            res.add(avgLevel);
        }
        return res;

    }
}
