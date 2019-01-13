package easy;

import basicstruct.TreeNode;

/**
 * find value in Binary Search Tree
 */
public class Problem700 {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else {
            if (root.val > val) {
                searchBST(root.left, val);
            } else if (root.val < val) {
                searchBST(root.right, val);
            }
        }
        return null;
    }
}
