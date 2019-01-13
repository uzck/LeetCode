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

    /**
     * no recursion ver
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBSTByIteration(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        while (root != null) {
            if (root.val == val) {
                return root;
            }
            if (root.val > val) {
                root = root.left;
                continue;
            }
            if (root.val < val) {
                root = root.right;
                continue;
            }
        }
        return null;
    }
}
