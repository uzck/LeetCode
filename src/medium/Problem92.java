package medium;

import basicstruct.ListNode;

/**
 * Reverse Linked List II
 * 翻转链表的m到n部分 要求在一次循环里实现
 * 1 <= m <= n <= length
 * 输入:1->2->3->4->5
 * 输出:1->4->3->2->1
 */
public class Problem92 {

    /**
     * 0ms 100%
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int currentSize = 0;
        if (head == null || m == n) {
            return head;
        }
        ListNode tmp = m == 1 ? null : head; // 如果m == 1 tmp为null
        ListNode tail = null; // 一直为newList.next
        ListNode realTail = null; // 指向位置为m的结点保持不动 用于最后拼接链表
        ListNode newList = new ListNode(-1);
        ListNode tailPrev = null; // 存储位置m-1的结点 用于最后拼接链表
        ListNode next;
        while (currentSize < n && head != null) {
            currentSize += 1;
            next = head.next;
            if (currentSize == m - 1) {
                tailPrev = head;
            }
            if (currentSize >= m && currentSize < n) {
                // 找到位置m的结点
                if (currentSize == m) {
                    tail = head;
                    realTail = head;
                    newList.next = tail;
                    head = head.next;
                } else {
                    head.next = tail;
                    newList.next = head;
                    tail = head;
                    head = next;
                }
                continue;
            }
            if (currentSize == n) {
                head.next = tail;
                newList.next = head;
                tail = head;
                realTail.next = next;
                break;
            }
            head = next;
        }
        // 如果m - 1的结点存在
        // m-1.next 指向newList.next(翻转部分的初始结点)
        if (tailPrev != null) {
            tailPrev.next = newList.next;
        }
        return tmp == null ? newList.next : tmp;
    }

    /**
     * 通过改变m-1.next来实现 m的结点指向下一个结点
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetweenSimple(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 0; i<m-1; i++) pre = pre.next;

        ListNode start = pre.next;
        ListNode then = start.next;


        // 假如输入的数据是1  2  3  4  5 m = 2 n = 4
        // pre = 1 start = 2 then = 3
        // 经过第一次迭代后 pre.next = 3 3.next = 2 2.next = 4 then = 4
        // 第二次迭代后 pre.next = 4 4.next = 3 3.next = 2 2.next = 5 then = 5
        //
        for(int i=0; i<n-m; i++)
        {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }


        return dummy.next;

    }

    public static void main(String[] args) {
        Problem92 problem92 = new Problem92();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);
        problem92.reverseBetween(head, 3, 9);
    }
}
