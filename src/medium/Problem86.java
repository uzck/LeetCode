package medium;

import basicstruct.ListNode;

/**
 * Partition List
 * 输入一个链表和一个数x
 * 输出要求所有小于x的结点要出现在大于x的结点前面 且保持原来的相对顺序
 */
public class Problem86 {

    /**
     * 0ms 100%
     * 双指针法
     * 先用指针less和指针greater分别指向第一个小于x和第一个大于等于x的结点(注意这里可能会有其中一个为null)
     * 从头遍历链表 如果小于x 添加到less后 如果大于等于x添加到greater后
     * 最后合并
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode less = head;
        ListNode greater = head;
        ListNode lessHead = null;
        ListNode greaterHead = null;
        while (less != null) {
            if (less.val < x) {
                break;
            }
            less = less.next;
        }
        lessHead = less;
        less = lessHead;
        while (greater != null) {
            if (greater.val >= x) {
                break;
            }
            greater = greater.next;
        }
        greaterHead = greater;
        greater = greaterHead;
        // less和greater用来保留初始结点的位置
        // lessHead和greaterHead用来添加新结点
        ListNode index = head;
        while (index != null) {
            // 如果index.val < x且不为当前的less的末尾结点
            if (index.val < x && index != lessHead) {
                lessHead.next = index;
                lessHead = lessHead.next;
                // 如果index.val >= x且不为当前的greater的末尾结点
            } else if (index.val >= x && index != greaterHead) {
                greaterHead.next = index;
                greaterHead = greaterHead.next;
            }
            index = index.next;

        }
        // 把less和greater链表串联起来
        if (lessHead != null) {
            lessHead.next = greater;
        }
        // greater结点的最后要记得.next = null 不然会出现循环 内存溢出
        if (greaterHead != null) {
            greaterHead.next = null;
        }
        // 如果有小于x的结点 返回less 否则返回greater
        return less != null ? less : greater;
    }

    /**
     * 简化版本
     * 用的是哨兵结点
     * @param head
     * @param x
     * @return
     */
    public ListNode partitionSimple(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head != null) {
            if (head.val < x) {
                curr1.next = head;
                curr1 = head;
            } else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }

    public static void main(String[] args) {
        Problem86 problem86 = new Problem86();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(2);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(2);
        problem86.partition(head, 0);

    }
}
