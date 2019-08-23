package medium;

import basicstruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Find Largest Value in Each Row
 * 找到二叉树每一行的最大值
 */
public class Problem515 {

    /**
     * BFS+Queue做层次遍历
     * 2ms 74%
     * @param root
     * @return
     */
    public List<Integer> largestValuesUserQueue(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                if (poll.val > max) {
                    max = poll.val;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            res.add(max);
        }
        return res;
    }


}
