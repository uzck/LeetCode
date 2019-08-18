package medium;

import basicstruct.ListNode;

/**
 * Odd Even Linked List
 * 输入一个单链表 把所有奇数位的结点放在偶数结前面 并保持其他相对位置不变(指的是位置 不是结点的值)
 * 要求O(n)时间复杂度 O(1)空间复杂度
 */
public class Problem328 {

    /**
     * 0ms 100% 双指针法
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        // 如果head == null 或者head只有一个和两个结点
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenOrigin = even;
        while (odd != null && even != null) {
            odd.next = even.next;
            // 如果even后面还有结点 把odd往后指 保证循环结束的时候odd停在最后一个奇数位上
            // 不然循环外的odd会为null
            if (odd.next != null) {
                odd = even.next;
            }
            if (odd != null) {
                // 因为偶数位于后面 最后要接null 所以不需要停在最后一个偶数上
                even.next = odd.next;
                even = odd.next;
            }
        }
        odd.next = evenOrigin;
        return head;
    }

    /**
     * 上面方法的简化
     * @param head
     * @return
     */
    public ListNode oddEvenListUseHead(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        // even.next ！= null 等价于上面的odd.next == null
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        Problem328 problem328 = new Problem328();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        problem328.oddEvenList(head);
    }
}
