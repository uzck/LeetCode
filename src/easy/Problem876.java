package easy;

import basicstruct.ListNode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 找一个链表的中点
 * 给定了结构为ListNode
 */
public class Problem876 {

    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode tmpListNode = head;
        int index = 0, middle;
        while (tmpListNode != null) {
            index += 1;
            tmpListNode = tmpListNode.next;
        }
        if (index % 2 == 0) {
            middle = (int) Math.floor(index / 2);
        } else {
            middle = index / 2;
        }
        while (middle > 0) {
            head = head.next;
            middle--;
        }
        return head;
    }

    public ListNode middleNodeTwoPoint(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        // fast != null是用于[1,2]的情况
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        Problem876 problem876 = new Problem876();
        problem876.middleNodeTwoPoint(l1);
    }
}
