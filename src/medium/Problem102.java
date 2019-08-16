package medium;

import basicstruct.ListNode;
import basicstruct.TreeNode;

import java.util.*;

/**
 * Binary Tree Level Order Traversal
 * 二叉树层次遍历
 */
public class Problem102 {

    /**
     * 1ms 89.09%
     * 用队列实现层次遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
             return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> data = new ArrayList<>();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                data.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            res.add(new ArrayList<>(data));
        }
        return res;
    }

    /**
     * 0ms 100% 很奇怪为啥dfs比bfs快
     * dfs的思路是先把每一层的链表创建出来再依次往里面填值
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }

    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root.val);
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }
}
