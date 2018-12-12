package easy;


import basicstruct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Problem617 {
    /**
     * 递归合并两个二叉树
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTreesRecrusive(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        // 合并左子树
        t1.left = mergeTreesRecrusive(t1.left, t2.left);
        // 合并右子树
        t1.right = mergeTreesRecrusive(t1.right, t2.right);
        return t1;
    }

    /**
     * 非递归方法
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        Stack <TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1, t2});
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            if (t[0] == null || t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }
}
