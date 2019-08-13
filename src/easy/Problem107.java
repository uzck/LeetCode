package easy;

import basicstruct.TreeNode;

import java.util.*;

/**
 * Binary Tree Level Order Traversal II
 * 自底向上层次遍历
 */
public class Problem107 {

    /**
     * 最简单的思路是自顶向下层次遍历后把结果翻转(或者用栈存 再输出)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int nextSize = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> data = new ArrayList<>();
            int nextSizeCopy = 0; // 存储下一层不为null结点的个数
            while (nextSize-- > 0) {
                TreeNode poll = queue.poll();
                data.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                    nextSizeCopy += 1;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    nextSizeCopy += 1;
                }
            }
            nextSize = nextSizeCopy;
            stack.push(data);
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
}
