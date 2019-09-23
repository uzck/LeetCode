package medium;

import basicstruct.ListNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Add Two Numbers
 */
public class Problem2 {

    /**
     * 1ms 100%
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 新的链表头结点
        ListNode head = new ListNode(-1);
        head.next = l1;
        int off = 0, sum = 0; // off是进位标志
        ListNode prev = l1; // prev存储l1的位置 在l1.next之后 仍能获取l1的引用
        // 把所有值都存在l1中
        while (l1 != null && l2 != null) {
            prev = l1;
            sum = l1.val + l2.val + off;
            off = sum / 10;
            l1.val = sum % 10;
            // 如果l1的下一个结点为null 说明l1比l2短 接到l2上
            if (l1.next == null) {
                l1.next = l2.next;
                l1 = l1.next;
                break;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            sum = l1.val + off;
            off = sum / 10;
            l1.val = sum % 10;
            prev = l1;
            l1 = l1.next;
        }

        // 如果最后还有个进位 还需要加1
        if (off == 1 && prev != null) {
            prev.next = new ListNode(1);
        }

        return head.next;
    }

    /**
     * 创建新的链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersSpace(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        // 这里是两个只要有一个不为0就继续迭代
        while (p != null || q != null) {
            // 如果为null就按0算
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
