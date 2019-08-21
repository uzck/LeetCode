package easy;

import basicstruct.TreeNode;

import java.util.Stack;

/**
 * Path Sum
 * 根结点到叶子结点的路径中是否存在满足和为指定值的路径数量
 */
public class Problem112 {

    /**
     * 非递归 用栈做dfs
     * 把路径之前累计的和直接加到子结点上
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumIter(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                if (cur.val == sum) return true;
            }
            if (cur.right != null) {
                cur.right.val = cur.val + cur.right.val;
                stack.push(cur.right);
            }
            if (cur.left != null) {
                cur.left.val = cur.val + cur.left.val;
                stack.push(cur.left);
            }
        }
        return false;
    }

    public boolean hasPathSumRecur(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return getPathSum(root, 0, sum);
    }

    /**
     * 0ms 100% 37.4MB 100%
     * 递归dfs
     *
     * @param root
     * @param currentSum
     * @param sum
     * @return
     */
    public boolean getPathSum(TreeNode root, int currentSum, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && currentSum + root.val == sum) {
            return true;
        }
        currentSum += root.val;
        return getPathSum(root.left, currentSum, sum) || getPathSum(root.right, currentSum, sum);
    }

}

