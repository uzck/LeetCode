package easy;

import basicstruct.TreeNode;

/**
 * 给定一棵二叉查找树 输入L, R作为边界, 对查找树剪枝, 使其上的所有节点都在[L,R]内
 */
public class Problem669 {

    /**
     * 递归剪枝
     * 如果当前结点小于L 减去左枝 把右子树根结点作为当前结点重新剪枝
     * 如果当前截取点大于R 剪去右枝 把左子树根结点作为当前结点重新剪枝
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {
            root.left = null;
            root = trimBST(root.right, L, R);
        } else if (root.val > R) {
            root.right = null;
            root = trimBST(root.left, L, R);
        }
        if (root != null) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
        }
        return root;
    }

    public static void main(String[] args) {
        Problem669 problem669 = new Problem669();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);
        problem669.trimBST(root, 1, 3);
        System.out.println(root.val);
    }
}
