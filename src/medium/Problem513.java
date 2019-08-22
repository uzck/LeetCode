package medium;

import basicstruct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find Bottom Left Tree Value
 * 输入一个二叉树 找到最下面一行最左边的结点 (不是最左边的结点) 二叉树不为null
 */
public class Problem513 {

    /**
     * 1ms 70.46%
     * 找最下面一行也可以做层次遍历 每次记录该行的第一个点
     * 遍历到最后一行之后返回即可
     *
     * @param root
     * @return
     */
    public int findBottomLeftValueLevelTraval(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        int res = 0;
        boolean firstLine;
        while (!queue.isEmpty()) {
            size = queue.size();
            firstLine = true;
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                if (firstLine) {
                    firstLine = false;
                    res = poll.val;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return res;
    }

    /**
     * bfs做层次遍历
     * 因为要找的是最左边的结点, 从右到左做bfs 省去每次存储第一个结点
     *
     * @param root
     * @return
     */
    public int findLeftMostNodeBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null)
                queue.add(root.right);
            if (root.left != null)
                queue.add(root.left);
        }
        return root.val;
    }

    /**
     * 递归做层次遍历
     *
     * @param root
     * @return
     */
    public int findBottomLeftValueRecur(TreeNode root) {
        return findBottomLeftValue(root, 1, new int[]{0, 0});
    }

    public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
        if (res[1] < depth) {
            res[0] = root.val;
            res[1] = depth;
        }
        if (root.left != null) findBottomLeftValue(root.left, depth + 1, res);
        if (root.right != null) findBottomLeftValue(root.right, depth + 1, res);
        return res[0];
    }
}
