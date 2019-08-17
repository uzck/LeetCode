package medium;

import basicstruct.TreeNode;

import java.util.Stack;

/**
 * Sum Root to Leaf Numbers
 * 把每条根节点到叶子节点的路径看做一个数字 求所有数字的和
 */
public class Problem129 {

    int sum = 0;

    /**
     * 递归dfs
     * 因为函数返回类型是int所以可以不考虑溢出 不然还得考虑层数过多 相加溢出的问题
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        int currentSum = 0;
        dfsNum(root, currentSum);
        return sum;
    }

    /**
     * 1ms 32.08%
     * 用stringbuffer存储数字 回溯的时候移除末位数字
     *
     * @param root
     * @param buffer
     */
    public void dfs(TreeNode root, StringBuffer buffer) {
        if (root == null) {
            return;
        }
        buffer.append(root.val);
        if (root.left == null && root.right == null) {
            sum += Integer.parseInt(buffer.toString());
            return;
        }
        if (root.left != null) {
            dfs(root.left, buffer);
            buffer.deleteCharAt(buffer.length() - 1);
        }
        if (root.right != null) {
            dfs(root.right, buffer);
            buffer.deleteCharAt(buffer.length() - 1);
        }
    }

    /**
     * 0ms 100%
     * 用currentSum保存当时的数字和
     *
     * @param root
     * @param currentSum
     */
    public void dfsNum(TreeNode root, int currentSum) {
        if (root == null) {
            return;
        }
        currentSum = currentSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += currentSum;
            return;
        }
        if (root.left != null) {
            dfsNum(root.left, currentSum);
        }
        if (root.right != null) {
            dfsNum(root.right, currentSum);
        }
    }

    /**
     * 非递归dfs
     *
     * @param root
     * @return
     */
    public int sumNumbersUseStack(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        TreeNode curr;
        Stack<TreeNode> ws = new Stack<>();
        ws.push(root);

        while (!ws.empty()) {
            curr = ws.pop();

            // 把之前的和存储到结点上
            if (curr.right != null) {
                curr.right.val = curr.val * 10 + curr.right.val;
                ws.push(curr.right);
            }

            if (curr.left != null) {
                curr.left.val = curr.val * 10 + curr.left.val;
                ws.push(curr.left);
            }

            if (curr.left == null && curr.right == null) { // leaf node
                sum += curr.val;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Problem129 problem129 = new Problem129();
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(10);
        problem129.sumNumbers(root);
    }
}
