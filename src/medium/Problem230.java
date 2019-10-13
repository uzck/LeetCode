package medium;

import basicstruct.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Kth Smallest Element in a BST
 * 输入一个二叉查找树 找出其中第k小的值
 * Follow up: 如果BST经常更新(添加/删除),也需要经常查找第k小的数 该怎么优化?(还未解决)
 */
public class Problem230 {

    /**
     * 1ms 76.19%
     * 中序遍历的迭代实现
     * @param root
     * @param k
     * @return
     */
    public int KthSmallest(TreeNode root, int k) {
        // 总共存储k个值
        int[] big = new int[k];
        TreeNode tmp = root;
        Stack<TreeNode> stack = new Stack<>();
        // 首先找到最左边的结点 并把根节点到该叶子节点路径上的所有点压入栈中
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.left;
        }
        int index = 0;
        while (!stack.isEmpty()) {
            tmp = stack.peek();
            // 如果还有左子树的 添加左子树的值
            if (tmp.left != null && tmp.left.val != Integer.MAX_VALUE) {
                stack.push(tmp.left);
                continue;
            }
            tmp = stack.pop();
            if (index < k - 1) {
                big[index++] = tmp.val;
            } else {
                return tmp.val;
            }
            // 修改当前结点的值作为访问过了的标志
            tmp.val = Integer.MAX_VALUE;
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
        }
        return big[k-1];
    }

    int ans = Integer.MIN_VALUE;
    int currentK; // 存储当前k的值

    /**
     * 中序遍历的递归实现
     * 1ms 76.19%
     * @param root
     * @param k
     * @return
     */
    public int KthSmallestRecur(TreeNode root, int k) {
        currentK = k;
        findLeft(root);
        return ans;
    }

    public void findLeft(TreeNode root) {
        if (root.left != null) {
            findLeft(root.left);
        }
        currentK -= 1;
        if (currentK == 0 && ans == Integer.MIN_VALUE) {
            ans = root.val;
            return;
        }
        if (root.right != null) {
            findLeft(root.right);
        }
    }

    /**
     * 0ms 100%
     * 中序遍历迭代实现的优化
     * 没有必要存储前k个值,在k=0的时候返回就行
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }

    public static void main(String[] args) {
        Problem230 problem230 = new Problem230();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        int ans = problem230.KthSmallestRecur(root, 3);
        System.out.println(ans);
    }

}
