package easy;

import basicstruct.TreeNode;
import sun.reflect.generics.tree.Tree;

import javax.print.attribute.standard.NumberUp;
import java.util.Stack;

/**
 * Same Tree
 * 判断两颗二叉树是否一致
 */
public class Problem100 {

    /**
     * 1ms 5.39% 好菜啊
     * 思路是用两个栈来依次存储结点(包括子结点为null的情况)
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        Stack<TreeNode> pStack = new Stack<>();
        Stack<TreeNode> qStack = new Stack<>();
        pStack.push(p);
        qStack.push(q);
        while (!pStack.isEmpty() && !qStack.isEmpty()) {
            TreeNode pPop = pStack.pop();
            TreeNode qPop = qStack.pop();
            if (pPop == null && qPop == null) {
                continue;
            }
            // 其中一个为null
            if ((pPop == null && qPop != null) || (pPop != null && qPop == null)) {
                return false;
            }
            // 都不为null的情况
            if (qPop.val != pPop.val) {
                return false;
            }
            pStack.push(pPop.left);
            pStack.push(pPop.right);
            qStack.push(qPop.left);
            qStack.push(qPop.right);
        }
        if (!pStack.isEmpty() || !qStack.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 两颗树相等的条件是该结点相等且左右子树也都各自相等
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) &&
                isSameTree(p.left, q.left);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
//        root1.right = new TreeNode(3);
        TreeNode root2 = new TreeNode(1);
//        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        Problem100 problem100 = new Problem100();
        problem100.isSameTree(root1, root2);
    }
}
