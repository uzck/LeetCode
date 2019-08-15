package medium;

import basicstruct.ListNode;

/**
 * Rotate List
 * 输入一个链表和一个正数k
 * 输出向右旋转k次后的转链表
 * 1->2->3->4->5->NULL k=2
 * 5->1->2->3->4->NULL 第一步
 * 4->5->1->2->3->NULL 第二步
 */
public class Problem61 {

    /**
     * 0ms 100%
     * 时间复杂度O(n) 空间复杂度O(n)
     * 思路: 这是一个单向链表 如果每次都要获取尾结点 就需要遍历一遍整个链表
     * 右旋转的操作就像循环链表 但是需要反向移动
     * 所以用ListNode[]来存储 这样index来做反向移动就行
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightUseArray(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tmp = head;
        int size = 0;
        while (tmp != null) {
            size += 1;
            tmp = tmp.next;
        }
        tmp = head;
        ListNode[] store = new ListNode[size];
        int index = 0;
        while (tmp != null) {
            store[index++] = tmp;
            tmp = tmp.next;
        }
        int headIndex = 0, tailIndex = size - 1;
        int tailPrevIndex;
        // 因为每旋转size次 链表恢复原样 求出实际上需要旋转的次数
        k = k % size;
        while (k-- > 0) {
            // 尾结点的下个结点指向首结点
            store[tailIndex].next = store[headIndex];
            // 计算尾结点的父结点下标 因为前面计算了实际需要的旋转的次数且k < size 所以直接-1 不需要考虑数组越界问题
            tailPrevIndex = tailIndex - 1;
            // 尾结点的父结点指向null
            store[tailPrevIndex].next = null;
            // 首结点指向原来的尾结点
            headIndex = tailIndex;
            // 尾结点指向尾结点的父结点
            tailIndex = tailPrevIndex;
        }
        return store[headIndex];
    }


    /**
     * 因为实际上需要旋转的次数是小于链表长度的 所以
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        int i;
        // 获取链表总长度
        for (i = 0; fast.next != null; i++)
            fast = fast.next;

        // 获取第i - n % i个结点
        // i - n % i是因为旋转是右旋 头结点向右移动k-1次就是旋转后的尾结点
        // 这段代码从dummy开始 所以需要移动k次
        // slow最后会停在旋转后的尾结点上
        for (int j = i - k % i; j > 0; j--)
            slow = slow.next;

        // 旋转
        // 原链表的尾结点的下一个结点指向首结点
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Problem61 problem61 = new Problem61();
        problem61.rotateRight(head, 2);
    }
}
