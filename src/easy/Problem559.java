package easy;

import basicstruct.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * N叉树的层数
 */
public class Problem559 {

    public int maxDepthRecursion(Node root) {
        int max = Integer.MIN_VALUE;
        if (root == null) {
            return 0;
        }
        // 没有子树的时候children并不为null
        if (root.children == null || root.children.size() == 0) {
            return 1;
        }
        List<Integer> childNodeDepth = new ArrayList<>();
        for (Node item : root.children) {
            childNodeDepth.add(maxDepthRecursion(item));
        }

        for (int i = 0; i < childNodeDepth.size(); i++) {
            max = childNodeDepth.get(i) > max ? childNodeDepth.get(i) : max;
        }
        return max + 1;
    }

    public int maxDepth(Node root) {
        int result = 0, countPushToQueue = 0; // 本次入队的个数
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        if (root.children.size() == 0) {
            return 1;
        }
        queue.add(root);
        Node tmp;
        while (!queue.isEmpty()) {
            countPushToQueue = queue.size();
            while (countPushToQueue-- > 0) {
                tmp = queue.poll();
                for (int i = 0; i < tmp.children.size(); i++) {
                    queue.add(tmp.children.get(i));
                }
            }
            result += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Node root = new Node();
        root.val = 1;
        root.children = new ArrayList<>();
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        root.children.get(0).children.add(new Node(5, new ArrayList<>()));
        root.children.get(0).children.add(new Node(6, new ArrayList<>()));

        Problem559 problem559 = new Problem559();
        System.out.println(problem559.maxDepthRecursion(root));
    }
}
