package medium;

import basicstruct.TreeNode;

import java.util.Stack;

/**
 * Maximum Difference Between Node and Ancestor
 * 输入一颗二叉树 找到两个不同结点的最大差值(绝对值) 且其中一个结点是另一个结点的ancestor
 */
public class Problem1026 {

    public int maxAncestorDiff(TreeNode root) {
       return dfs(root, root.val, root.val);
    }

    /**
     * 0ms 100% discuss里的解法 复杂度O(N)
     * 思路: 自顶向下
     * 1. 暴力法: 找出所有的可能路径 再去求解
     * 2. 因为不需要保留所有的结点 只需要最大与最小就能求出结果
     * @param root
     * @param min
     * @param max
     * @return
     */
    public int dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return max - min;
        }
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        return Math.max(dfs(root.left, min, max), dfs(root.right, min, max));
    }

    /**
     * BFS 复杂度O(N^2)
     * @param root
     * @return
     */
    public int maxAncestorDiffBFS(TreeNode root) {
        int max = 0;

        // iterate through every node as the 'A' node
        // (outer BFS)
        Stack<TreeNode> nodeA = new Stack<>();
        nodeA.push(root);

        while(!nodeA.isEmpty())
        {
            TreeNode currentA = nodeA.pop();
            if(currentA.left != null)
                nodeA.push(currentA.left);
            if(currentA.right != null)
                nodeA.push(currentA.right);


            // 另外用一个栈对一个结点下的所有结点做一次遍历 这里存在很多重复计算的结点
            Stack<TreeNode> stack = new Stack<>();
            stack.push(currentA);

            while(!stack.isEmpty())
            {
                TreeNode current = stack.pop();

                if(current.left != null)
                    stack.push(current.left);
                if(current.right != null)
                    stack.push(current.right);

                // look at a new potential pair, store difference if its greater
                int diff = Math.abs(currentA.val - current.val);

                if(diff > max)
                    max = diff;
            }

        }

        return max;
    }

    public static void main(String[] args) {
        Problem1026 problem1026 = new Problem1026();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(7);
        int res = problem1026.maxAncestorDiff(root);
        System.out.println(res);
    }
}
