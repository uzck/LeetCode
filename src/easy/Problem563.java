package easy;

import basicstruct.TreeNode;

/**
 * Binary Tree Tilt
 * tilt的定义:结点左子树之和和右子树之和的绝对值
 * 整棵树的tilt是所有结点的tilt
 * 求整个二叉树的tilt
 * 最后的和不会超过Integer.MAX_VALUE
 */
public class Problem563 {

    int sum;

    /**
     * 1ms 82.35%
     * 递归后序遍历
     * 用一个sum累计最后的总和
     * 从底部开始计算和
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        sum = 0;
        if (root == null) {
            return sum;
        }
        int left = getTiltValue(root.left);
        int right = getTiltValue(root.right);
        return sum + Math.abs(left - right);
    }

    /**
     * @param root
     * @return
     */
    public int getTiltValue(TreeNode root) {
        // null返回0
        if (root == null) {
            return 0;
        }
        // 叶子节点返回结点值
        // null和叶子节点对sum都没有贡献
        if (root.left == null && root.right == null) {
            return root.val;
        }
        // 左子树的和
        int leftSum = getTiltValue(root.left);
        // 右子树的和
        int rightSum = getTiltValue(root.right);
        // 左子树和右子树的差值
        sum += Math.abs(leftSum - rightSum);
        // 当前结点的总和 左子树的和+右子树的和+当前结点值
        return root.val + leftSum + rightSum;
    }

    public static void main(String[] args) {
        Problem563 problem563 = new Problem563();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        int res = problem563.findTilt(root);
        System.out.println(res);
    }

}
