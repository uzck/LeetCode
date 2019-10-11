package medium;

import basicstruct.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Next Greater Node In Linked List
 * 找出链表中每个数的大于该数的最近的值
 * 输入:[2, 1, 5]
 * 输出:[5, 5, 0]
 */
public class Problem1019 {

    /**
     * 数组模拟栈
     * 思路与栈的思路一致
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        int size = 0;
        ListNode pointer = head;

        while(pointer != null) {
            ++size;
            pointer = pointer.next;
        }

        int[] res = new int[size];

        int[] indexSt = new int[size];
        int[] valSt = new int[size];
        int vindx = -1;
        int indx = 0;

        while(head != null) {
            while(vindx >= 0 && valSt[vindx] < head.val) {
                res[indexSt[vindx--]] = head.val;
            }
            valSt[++vindx] = head.val;
            indexSt[vindx] = indx;
            head = head.next;
            ++indx;
        }
        return res;
    }

    /**
     * 35ms
     * @param head
     * @return
     */
    public int[] nextLargerNodesStack(ListNode head) {
        ListNode tmp = head;
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> data = new ArrayList<>();
        // 把数据转存到列表中
        while (tmp != null) {
            data.add(tmp.val);
            tmp = tmp.next;
        }

        // 保存结果
        int[] ans = new int[data.size()];
        // 逆序处理
        for (int i = data.size() - 1; i >= 0; i--) {
            // 如果当前数比栈顶数大(等于) 弹出栈顶元素 说明该栈顶元素不能作为该数的最近的大于的数
            while (!stack.isEmpty() && data.get(i) >= stack.peek()) {
                stack.pop();
            }
            // 如果栈为空了 说明该数后面的数都比它小
            if (stack.isEmpty()) {
                ans[i] = 0;
            } else {
                // 当前栈顶元素为所求
                ans[i] = stack.peek();
            }
            stack.push(data.get(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(7);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next = new ListNode(1);
        Problem1019 problem1019 = new Problem1019();
        problem1019.nextLargerNodesStack(head);
    }
}
