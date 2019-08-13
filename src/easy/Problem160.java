package easy;

import basicstruct.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Intersection of Two Linked List
 * 找出两个单链表使用的公共链 如果没有公共部分返回null
 */
public class Problem160 {

    /**
     * 3ms 23.85%
     * 思路是如果公共部分存在 那么公共部分的结点必然是一样的
     * 用两个栈存储链表 再倒序输出 依次比较两个栈弹出的元素是否相等 上一个相等的位置就是公共部分的分界点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeUseStack(ListNode headA, ListNode headB) {
        // 其中一个为null
        if (headA == null || headB == null) {
            return null;
        }
        // 如果两者相等
        if (headA == headB) {
            return headA;
        }
        ListNode res = null;
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        while (headA != null) {
            stackA.push(headA);
            headA = headA.next;
        }
        while (headB != null) {
            stackB.push(headB);
            headB = headB.next;
        }
        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            ListNode popA = stackA.pop();
            ListNode popB = stackB.pop();
            if (popA != popB) {
                return res;
            } else {
                res = popA;
            }
        }
        return res;
    }

    /**
     * 8ms 14.33%
     * 思路是用hashtable 先存储其中一个链表 然后再扫描另一个链表 如果另一个了链表上的结点可以在hashtable里找到 第一个点这样的点就是返回值
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionHashTable(ListNode headA, ListNode headB) {
        // 其中一个为null
        if (headA == null || headB == null) {
            return null;
        }
        // 如果两者相等
        if (headA == headB) {
            return headA;
        }
        HashSet<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * 1ms 98.36%
     * 双指针法 应该是这题需要想到的解法
     * 思路: 两个指针分别遍历各自的链表 如果到了链表最后 则遍历另一个链表 if (pA == null) pA = headB
     * 因为A+B的长度相同 所以pA和pB一定是会遇到的
     * 如果没有公共部分 pA和pB最后都会变成null 退出循环
     * 如果有公共部分 pA和pB会停在相同的位置
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionTwoPoint(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
