package easy;

import basicstruct.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Problem590 {

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                result.addAll(postorder(root.children.get(i)));
            }
        }
        result.add(root.val);
        return result;
    }

    public List<Integer> postOrderIteration(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast(); // 移除栈顶元素
            output.addFirst(node.val); // 添加到链表头
            if (node.children != null) {
                for (Node item : node.children) {
                    if (item != null) {
                        stack.add(item);
                    }
                }
            }

        }
        return output;
    }

    public static void main(String[] args) {
        Node root = new Node();
        root.val = 1;
        root.children = new ArrayList<>();
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        root.children.get(0).children.add(new Node(5, null));
        root.children.get(0).children.add(new Node(6, null));
        root.children.get(1).children.add(new Node(7, null));
        root.children.get(1).children.add(new Node(8, null));
        root.children.get(2).children.add(new Node(9, null));
        root.children.get(2).children.add(new Node(10, null));
        Problem590 problem590 = new Problem590();
        List<Integer> result = problem590.postOrderIteration(root);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
