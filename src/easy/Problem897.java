package easy;

import basicstruct.TreeNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 输入是一个二叉树 按照中序遍历的方式 重建为左叶节点为顶点的有序树
 * node数量 [1, 100]
 * 结点值范围[0, 1000]
 */
public class Problem897 {

    ArrayList<Integer> treeVal = new ArrayList<>();

    /**
     * 遍历一遍tree 对拿到的数据排序 建立一颗只有右子树的二叉查找树
     * 没通过[379,826]这个测试数据 这个也不是二叉查找树啊...orz
     * @param root
     * @return
     */
    public TreeNode increasingBSTWrong(TreeNode root) {
        traverse(root);
        if (treeVal.size() == 0) {
            return null;
        }
        TreeNode newRoot = new TreeNode(treeVal.get(0));
        TreeNode tmp = newRoot;
        for (int i = 1; i < treeVal.size(); i++) {
            tmp.right = new TreeNode(treeVal.get(i));
            tmp = tmp.right;
        }
        return newRoot;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        treeVal.add(root.val);
        Collections.sort(treeVal);
        traverse(root.left);
        traverse(root.right);
    }

    public TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }

    /**
     * 中序遍历 左 中 右
     * 先找到最左边的叶结点 作为新的根节点 newRoot
     * 因为二叉查找树的性质 newRoot的父节点parent一定比它大 因此newRoot.right = parent newRoot.left = null
     * 依次往上递归 如果parent有右子树的话 对右子树递归出一个新的二叉查找树根节点rightRoot 与parent的父节点grandparent合并作为newRoot的右子树
     * @param root
     * @param tail
     * @return
     */
    public TreeNode increasingBST(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        root.right = increasingBST(root.right, tail);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(18);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(14);
        root.right.right = new TreeNode(20);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(9);
        Problem897 problem897 = new Problem897();
        problem897.increasingBST(root);
    }
}
