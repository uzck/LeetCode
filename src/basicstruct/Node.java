package basicstruct;

import java.util.List;

/**
 * 树的子结点用链表示
 */
public class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
