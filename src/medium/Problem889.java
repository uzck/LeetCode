package medium;

import basicstruct.TreeNode;

/**
 * Construct Binary Tree From Preorder and Postorder Traversal
 * 根据先序遍历和后续遍历重建二叉树
 */
public class Problem889 {

    /**
     * 1ms 100%
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePostRecur(int[] pre, int[] post) {
        if (pre == null || post == null || pre.length < 1 || post.length < 1) {
            return null;
        }
        TreeNode root = findTreeRoot(pre, 0, pre.length-1, post, 0, post.length-1);

        return root;
    }

    /**
     * 思路:分段,递归找根节点
     * 根据先序遍历和后续遍历 可以确定每棵树(子树)的根节点的位置
     * 查找先序遍历中根节点的下一个结点的值在后续遍历中的位置 leftChildIndex
     * 如果post[leftChildIndex+1]的值等于根节点的值 说明根节点只有一个子节点
     * 题目说明了如果存在多种可能结果 任意一种都算通过 因此这里假设这种情况下都是左子树
     * 如果post[leftChildIndex+1]不等于根节点的值 说明还存在右子树
     * 查找后序遍历中倒数第二个值在先序遍历中的位置 rightChildIndex
     * @param pre 先序遍历结果
     * @param preStart
     * @param preEnd
     * @param post 后续遍历结果
     * @param posStart
     * @param posEnd
     */
    public TreeNode findTreeRoot(int[] pre, int preStart, int preEnd, int[] post, int posStart, int posEnd) {
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) {
            return root;
        }
        int leftChildIndex = -1;  // 后续遍历中左子树根节点位置
        int rightChildIndex = -1; // 先序遍历中右子树根节点位置
        for (int i = posStart; i <= posEnd; i++) {
            if (post[i] == pre[preStart+1]) {
                leftChildIndex = i;
                break;
            }
        }
        for (int i = preStart; i <= preEnd; i++) {
            if (pre[i] == post[posEnd-1]) {
                rightChildIndex = i;
                break;
            }
        }
        // 只有一个子树的情况 因为题目允许多种情况只输出一种情况即可 这里默认如果只有一个子树都算左子树
        if (post[leftChildIndex+1] == root.val) {
            // [preStart+1, preEnd] 左子树先序范围 [posStart, leftChildIndex] 左子树后序范围
            root.left = findTreeRoot(pre, preStart + 1, preEnd, post, posStart, leftChildIndex);
        } else {
            // 存在两棵子树
            // [preStart + 1, rightChildIndex-1] 左子树先序范围 [posStart, leftChildIndex] 左子树后序遍历范围
            root.left = findTreeRoot(pre, preStart + 1, rightChildIndex - 1, post, posStart, leftChildIndex);
            // [rightChildIndex, preEnd] 右子树先序范围 [leftChildIndex+1, posEnd-1] 右子树后序遍历范围
            root.right = findTreeRoot(pre, rightChildIndex, preEnd, post, leftChildIndex + 1, posEnd-1);
        }
        return root;
    }


    int[] pre, post;

    /**
     * LeetCode Solution解法
     * 相比另一个Solution的解法节省了空间
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return make(0, 0, pre.length);
    }

    /**
     *
     * @param i0 先序遍历起始位置
     * @param i1 后序遍历起始位置
     * @param N 剩余结点数量
     * @return
     */
    public TreeNode make(int i0, int i1, int N) {
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[i0]);
        if (N == 1) return root;

        int L = 1;
        for (; L < N; ++L)
            // 找出左子树根节点在后序遍历中的位置
            if (post[i1 + L-1] == pre[i0 + 1])
                break;

        // 左子树先序位置起始为i0+1, 后序位置i1 左子树的总结点数L
        root.left = make(i0+1, i1, L);
        // 右子树先序位置起始为i0+L+1 后序位置为i1+L 右子树的总结点数为N-1-L
        root.right = make(i0+L+1, i1+L, N-1-L);
        return root;
    }

    public static void main(String[] args) {
//        Problem889 problem889 = new Problem889();
//        int[] pre = new int[] {1, 2, 4, 5, 3, 6, 7};
//        int[] post = new int[] {4, 5, 2, 6, 7, 3, 1};
//        int[] pre = new int[] {1, 3, 5, 2, 4};
//        int[] post = new int[] {2, 4, 5, 3, 1};
//        problem889.constructFromPrePostRecur(pre, post);
    }
}
