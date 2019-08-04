package easy;

import java.util.*;

/**
 * N叉树的层次遍历
 */
public class Problem429 {

    class Node {
        public int val;
        public List<Node> children;

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(Arrays.asList(root.val));
        List<Node> currentNodeList = new ArrayList<>(root.children);
        List<Node> childrenNode = new ArrayList<>();
        while (currentNodeList.size() > 0) {
            List<Integer> data = new LinkedList<>();
            for (int i = 0; i < currentNodeList.size(); i++) {
                if (!(currentNodeList.get(i).children == null || currentNodeList.get(i).children.size() == 0)) {
                    childrenNode.addAll(currentNodeList.get(i).children);
                }
                data.add(currentNodeList.get(i).val);
            }
            result.add(new ArrayList<>(data));
            data.clear();
            currentNodeList.clear();
            currentNodeList.addAll(childrenNode);
            childrenNode.clear();
        }
        return result;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderRecursive(Node root) {
        List<List<Integer>> sol = new ArrayList<>();
        if (root != null) {
            int level = 0;
            traverse(root, sol, level);
        }
        return sol;
    }

    private void traverse(Node root, List<List<Integer>> sol, int level) {
        if (sol.size() <= level)
            sol.add(new ArrayList<Integer>());

        sol.get(level).add(root.val);

        for (Node child : root.children)
            traverse(child, sol, level + 1);
    }

    public static void main(String[] args) {

    }
}
