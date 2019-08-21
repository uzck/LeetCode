package easy;

import basicstruct.TreeNode;

import java.util.HashMap;
import java.util.Map;


/**
 * Path Sum III
 * 找出父结点到子节点的所有路径中(不只是根到叶结点)满足和为指定数字的路径数
 */
public class Problem437 {

    /**
     * 11ms 48.74%
     * 把每个结点看做一棵树的根节点 检查子树的和
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0)
                + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }

    /**
     * 4ms 94.67%
     * 用map存储前面的值 节省时间
     * @param root
     * @param sum
     * @return
     */
    public int pathSumRecurMap(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return backtrack(root, 0, sum, map);
    }

    /**
     * backtrack one pass
     * @param root
     * @param sum
     * @param target
     * @param map
     * @return
     */
    public int backtrack(TreeNode root, int sum, int target, Map<Integer, Integer> map){
        if(root == null)
            return 0;
        sum += root.val;
        int res = map.getOrDefault(sum - target, 0);    // 查看map里有没有和为sum-target的子路径
        map.put(sum, map.getOrDefault(sum, 0)+1);
        res += backtrack(root.left, sum, target, map) + backtrack(root.right, sum, target, map);
        map.put(sum, map.get(sum)-1);   // 移除当前结点
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(-3);
        root.right.right = new TreeNode(2);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(-2);
        root.right.left.right = new TreeNode(3);
        root.right.right.left = new TreeNode(1);
        root.right.right.right = new TreeNode(2);
        Problem437 problem437 = new Problem437();
        problem437.pathSum(root, 4);
    }

}
