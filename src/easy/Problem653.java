package easy;

import basicstruct.TreeNode;

import java.util.*;

/**
 * Two Sum IV
 * 输入一颗二叉查找树和一个目标数 如果二叉树上有两个数之和等于目标数 返回true
 */
public class Problem653 {

    /**
     * 167ms 5.29%...
     * 用的和Problem1一样的用hashmap存储 没用到bst的性质
     * @param root
     * @param k
     * @return
     */
    public boolean findTargetHashMap(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            int complement = k - pop.val;
            if (map.containsValue(complement)) {
                return true;
            } else {
                map.put(pop.val, pop.val);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        return false;
    }


    public boolean findTargetUseSet(TreeNode root, int k) {
        Set<Integer> set = new HashSet();
        Queue < TreeNode > queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (set.contains(k - node.val))
                    return true;
                set.add(node.val);
                queue.add(node.right);
                queue.add(node.left);
            } else
                queue.remove();
        }
        return false;
    }

    /**
     * 3ms 87%
     * bfs
     * 用list存储所有结点 然后双指针遍历
     * 这里用到了左子树小于父结点 右子树大于父结点 得到的数据是有序的
     * @param root
     * @param k
     * @return
     */
    public boolean findTargetBFS(TreeNode root, int k) {
        List <Integer> list = new ArrayList();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k)
                return true;
            if (sum < k)
                l++;
            else
                r--;
        }
        return false;
    }

    /**
     * 中序遍历 在bst里这样添加的数据是有序
     * @param root
     * @param list
     */
    public void inorder(TreeNode root, List < Integer > list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * 1ms 100% 最快的方法
     * 对每个结点检查k-node.val是否存在bst中
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        return traverse(root, root, k);
    }

    public boolean traverse(TreeNode root, TreeNode node, int k) {
        if (root == null || node == null)
            return false;
        // 根结点能不能找到匹配的
        if (findNumber(root, k, node))
            return true;
        // 左子树和右子树能不能找到匹配的
        return traverse(root, node.left, k) || traverse(root, node.right, k);
    }

    public boolean findNumber(TreeNode root, int k, TreeNode from) {
        if (root == null)
            return false;
        // 结点不能重复
        if (root.val + from.val == k && root != from)
            return true;
        // 如果和小于k 说明有数字小了 往右子树搜索
        if (root.val + from.val < k)
            return findNumber(root.right, k, from);
        else
            // 和大于k 说明有数字大了 往左子树搜索
            return findNumber(root.left, k, from);
    }
}
