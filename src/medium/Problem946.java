package medium;

import java.util.Stack;

/**
 * Validate Stack Sequence
 */
public class Problem946 {

    /**
     * 2ms 90.22%
     * 思路: 用栈处理
     * 提交里有用数组来模拟栈的 思路一样 但是更快
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) {
            return false;
        }
        Stack<Integer> pushStack = new Stack<>();
        int popIndex = 0; // popped当前的数值
        for (int i = 0; i < pushed.length; i++) {
            // 如果要入栈的数据和pop的相同 不入栈
            if (pushed[i] == popped[popIndex]) {
                popIndex++;
                // 检查剩余的栈顶元素是否和pop的相同 相同的弹出
                while (!pushStack.isEmpty() && popIndex < popped.length) {
                    if (pushStack.peek() == popped[popIndex]) {
                        pushStack.pop();
                        popIndex++;
                    } else {
                        break;
                    }
                }
                continue;
            }
            // 和pop不同的元素入栈
            pushStack.add(pushed[i]);
        }
        for (;popIndex < popped.length; popIndex++) {
            if (pushStack.peek() == popped[popIndex]) {
                pushStack.pop();
            }
        }
        return pushStack.isEmpty();
    }

    public static void main(String[] args) {
        Problem946 problem946 = new Problem946();
        int[] a = new int[] {1, 2, 3, 4, 5};
        int[] b = new int[] {4, 5, 3, 2, 1};
        problem946.validateStackSequences(a, b);
    }
}
