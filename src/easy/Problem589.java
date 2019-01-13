package easy;

import basicstruct.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem589 {

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Node> children;
        List<Integer> result = new ArrayList<>();
        Stack<Node> storeStack = new Stack<>();
        storeStack.push(root);
        while (!storeStack.empty()) {
            root = storeStack.pop();
            result.add(root.val);
            if (root.children != null) {
                children = root.children;
                for (int i = children.size() - 1; i >= 0; i--) {
                    storeStack.add(children.get(i));
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        Node root = new Node();
        root.val = 1;
        root.children = new ArrayList<>();
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, null));
        root.children.add(new Node(4, null));
        root.children.get(0).children.add(new Node(5, null));
        root.children.get(0).children.add(new Node(6, null));
        Problem589 problem589 = new Problem589();
        List<Integer> result = problem589.preorder(root);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
