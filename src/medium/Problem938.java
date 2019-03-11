package medium;

import basicstruct.TreeNode;

import java.util.Stack;

/**
 * Range Sum of BST
 */
public class Problem938 {

    /**
     * 用栈来遍历树
     * 然而比递归还慢(我好菜啊
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        TreeNode temp;
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        if (root.val < L) {
            stack.push(root.right);
        } else if (root.val > R) {
            stack.push(root.left);
        } else if (root.val >= L && root.val <= R) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            temp = stack.pop();
            if (temp.left != null) {
                stack.push(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
            sum += (L <= temp.val) && (temp.val <= R) ? temp.val : 0;
        }

        return sum;
    }

    /**
     * 递归版本 分情况讨论
     * root的值位于L和R中间
     * root.val < L 在右子树找位于L和R之间的值
     * root.val > R 在左子树找位于L和R之间的值
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int recursiveVersion(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }
        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        Problem938 problem938 = new Problem938();
        problem938.rangeSumBST(root, 3, 18);
    }
}
