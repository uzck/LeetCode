package medium;

import basicstruct.ListNode;

import java.util.Stack;

/**
 * Add Two Numbers II
 * 非负数字用链表表示 且没有前导0 求出两个链表相加的结果
 * 进阶: 如果不翻转链表 怎么实现
 */
public class Problem445 {

    /**
     * 6ms 50.7%
     * 思路: 用栈来翻转链表的简化版
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersReverseStackSimple(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);
        // 如果为空的话就不添加该值
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }

        return list.val == 0 ? list.next : list;
    }

    /**
     * 剩余部分的处理和上面是一样的
     *
     * @param stack
     * @param change
     * @return
     */
    public int processRemain(Stack<ListNode> stack, int change) {
        int changeCopy = -1;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            changeCopy = (node.val + change) / 10;
            node.val = (node.val + change) % 10;
            change = changeCopy;
        }
        return change;
    }

    public ListNode addTwoNumbersNoReverse(ListNode l1, ListNode l2) {
        ListNode l3 = l2;
        int l2Size = 0;
        int l1Size = 0;
        while (l3.next != null) {
            l2Size += 1;
            l3 = l3.next;
        }
        l2Size += 1;
        l3.next = l1;
        while (l3 != null) {
            l1Size += 1;
            l3 = l3.next;
        }
        return l1;
    }

    /**
     * 用栈来翻转链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersReverseStack(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode l1Copy = l1;
        ListNode l2Copy = l2;
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        ListNode newHead;
        // 判断最后需要返回的是哪个链表
        boolean useL1 = stack1.size() >= stack2.size();
        int change = 0; // 进位
        int changeCopy = -1; // 进位值的拷贝
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode l1Node = stack1.pop();
            ListNode l2Node = stack2.pop();
            // 计算下一次的进位
            changeCopy = (l1Node.val + l2Node.val + change) / 10;
            // 根据useL1更新不同的链表
            if (useL1) {
                l1Node.val = (l1Node.val + l2Node.val + change) % 10;
            } else {
                l2Node.val = (l1Node.val + l2Node.val + change) % 10;
            }
            change = changeCopy;
        }
        // 这时已经处理完了一个链表 但是可能还存在进位
        // 如果没有进位了 剩余的部分不需要修改
        if (change == 0) {
            if (useL1) {
                return l1Copy;
            } else {
                return l2Copy;
            }
        } else {
            if (useL1) {
                change = processRemain(stack1, change);
                // 如果最后还有一个进位 需要在最前面添加一个额外的结点
                if (change > 0) {
                    newHead = new ListNode(change);
                    newHead.next = l1Copy;
                    return newHead;
                } else {
                    return l1Copy;
                }
            } else {
                change = processRemain(stack2, change);
                if (change > 0) {
                    newHead = new ListNode(change);
                    newHead.next = l2Copy;
                    return newHead;
                } else {
                    return l2Copy;
                }
            }
        }
    }

    /**
     * 2ms 99.85%
     * 直接翻转链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersDirectReverse(ListNode l1, ListNode l2) {
        ListNode n1 = reverse(l1);
        ListNode n2 = reverse(l2);
        int carry = 0;
        ListNode temp = n1;
        ListNode pre = n1;
        while (n1 != null || n2 != null || carry != 0) {
            int v1 = n1 == null ? 0 : n1.val;
            int v2 = n2 == null ? 0 : n2.val;
            if (n1 == null) {
                n1 = new ListNode((v1 + v2 + carry) % 10);
                pre.next = n1;
            } else {
                n1.val = (v1 + v2 + carry) % 10;
            }
            carry = (v1 + v2 + carry) / 10;
            pre = n1;
            n1 = n1 == null ? null : n1.next;
            n2 = n2 == null ? null : n2.next;
        }
        return reverse(temp);
    }

    /**
     * 翻转链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            // 当前结点的下一个结点
            ListNode next = head.next;
            // 当前结点指向newHead
            head.next = newHead;
            // newHead指向head
            newHead = head;
            // head指向原来的head.next
            head = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        Problem445 problem445 = new Problem445();
        problem445.addTwoNumbersDirectReverse(l1, l2);

    }
}
