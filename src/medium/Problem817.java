package medium;

import basicstruct.ListNode;

import java.util.*;

/**
 * Linked List Components
 * 输入一个链表(不含重复数值) head 和一个数字数组G
 * 找出G中的数字在head中相邻的部分个数
 */
public class Problem817 {

    /**
     * 8ms 27.82%
     * 思路: 题目要求的是找G中的连续片段,分界点为前面的值在G中,当前值不在G中
     * 用set存储G里面的数据 双指针遍历链表
     * @param head
     * @param G
     * @return
     */
    public int numComponentsSlow(ListNode head, int[] G) {
        if (head == null) {
            return 0;
        }
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        for (int i : G) {
            set.add(i);
        }
        int prev = head.val;
        while (head != null) {
            // 只需要考虑 prev在G中, head.val不在G中 这种情况
            if (set.contains(prev) && !set.contains(head.val)) {
                sum += 1;
            }
            prev = head.val;
            head = head.next;
        }
        // 如果链表的最后一个数在G中 因为上面循环没考虑到最后一个结点的情况 需要额外加一
        if (set.contains(prev)) {
            sum += 1;
        }
        return sum;
    }

    /**
     * 2ms 98.82%
     * 比起上面优化了判断链表中的值是否存在G中的过程 直接用数组下标来取值比contains快
     * @param head
     * @param G
     * @return
     */
    public int numComponents(ListNode head, int[] G) {
        if (head == null) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        // 找出G中的最大值和最小值
        for (int i : G) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        boolean[] visited = new boolean[max - min + 1]; // G中的元素是否已经被访问了 如果G中数值差距很大的话这个数组会很大
        // i-min作为下标存储
        for (int i : G) {
            visited[i - min] = true;
        }
        int prev = head.val;
        while (head != null) {
            // 判断条件 当前结点的值不在G里面 上个结点的值在G里面
            // 因为上面visited的长度只是list的部分 所以链表上的数值不一定在visited的范围内
            // prev <= max && prev >= min 确保prev的值在G的最小到最大的范围内
            // visited[prev - min]是用来判断prev在G内
            // 判断head.val不在G内的条件有三个
            // 1. 超过G的最大值
            // 2. 小于G的最小值
            // 3. visited[head.val-min] == false
            if (prev <= max && prev >= min && (head.val > max || head.val < min || !visited[head.val-min]) && visited[prev-min]) {
                sum += 1;
            }
            prev = head.val;
            head = head.next;
        }
        // 和上面的一样 用于最后的处理 记得min <= prev <= max
        if (prev >= min && prev <= max && visited[prev - min]) {
            sum += 1;
        }

        return sum;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
//        head.next.next.next = new ListNode(3);
//        head.next.next.next.next = new ListNode(4);
//        head.next.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next.next = new ListNode(6);
//        head.next.next.next.next.next.next.next = new ListNode(7);
//        head.next.next.next.next.next.next.next.next = new ListNode(8);
        Problem817 problem817 = new Problem817();
        int res = problem817.numComponents(head, new int[] {0,2});
        System.out.println(res);
    }
}
