package medium;

import basicstruct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Path Sum II
 * 在Path Sum的基础上找出所有路径和为指定数字的路径
 * https://leetcode.com/problems/path-sum-ii/discuss/36829/Python-solutions-(Recursively-BFS%2Bqueue-DFS%2Bstack)
 * 遍历根到叶路径的方法: 递归, bfs+queue, dfs+stack
 */
public class Problem113 {

    List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        getPathSum(root, 0, new ArrayList<>(), sum);
        return res;
    }

    /**
     * 迭代法
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSumIter(TreeNode root, int sum) {
        res = new ArrayList<>(); // 存储最后的返回结果
        List<Integer> path = new ArrayList<>(); // 存储路径
        Stack<TreeNode> stack = new Stack<>(); // 存储结点
        int currentSum = 0;
        TreeNode cur = root;
        TreeNode pre = null; // 用于指向上一次访问的结点 在回溯的时候判断是否访问过
        while (cur != null || !stack.isEmpty()) {
            // 找到最左边的叶子结点
            // 最后cur = null 所以下面这个循环只在第一次和上个结点存在右子树的情况执行
            while (cur != null) {
                stack.push(cur);
                path.add(cur.val);
                currentSum += cur.val;
                cur = cur.left;
            }
            cur = stack.peek();
            // 如果当前结点有右结点并且右结点不是上一次访问的结点
            if (cur.right != null && cur.right != pre) {
                cur = cur.right;
                continue;
            }
            // 访问到了叶结点并且路径和为sum
            if (cur.left == null && cur.right == null && currentSum == sum)
                res.add(new ArrayList<>(path));

            pre = cur;
            stack.pop();
            path.remove(path.size() - 1);
            currentSum -= cur.val;
            cur = null;

        }
        return res;
    }

    /**
     * 1ms 100%
     * 递归dfs
     *
     * @param root
     * @param currentSum
     * @param currentList
     * @param sum
     */
    public void getPathSum(TreeNode root, int currentSum, List<Integer> currentList, int sum) {
        if (root == null) {
            return;
        }
        if (currentSum + root.val == sum && root.left == null && root.right == null) {
            currentList.add(root.val);
            res.add(new ArrayList<>(currentList));
            currentList.remove(currentList.size() - 1);
            return;
        }
        currentList.add(root.val);
        currentSum += root.val;
        getPathSum(root.left, currentSum, currentList, sum);
        getPathSum(root.right, currentSum, currentList, sum);
        currentList.remove(currentList.size() - 1);
    }

}
