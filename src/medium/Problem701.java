package medium;

import basicstruct.TreeNode;

/**
 * Insert into a binary search
 */
public class Problem701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode temp = root;
        while (root != null) {
            if (root.val < val) {
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                } else {
                    root = root.right;
                }
            }
            if (root.val > val) {
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                } else {
                    root = root.left;
                }
            }
        }

        return temp;
    }
}
