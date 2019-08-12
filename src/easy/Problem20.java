package easy;

import java.util.Stack;

/**
 * Valid Parentheses
 * 检查括号使用是否合法 包括() [] {} ..开始还以为要考虑{[()]}的顺序
 */
public class Problem20 {

    /**
     * 2ms 61.4%
     * 用栈来存储 如果符合就弹出 看最后栈里面是否为空
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if (s == null || s.equals("")) {
            return true;
        }
        char[] array = s.toCharArray();
        stack.push(array[0]);
        for (int i = 1; i < array.length; i++) {
            if (!stack.isEmpty()) {
                char peek = stack.peek();
                char current = array[i];
                if ((current == ']' && peek == '[')
                        || (current == ')' && peek == '(')
                        || (current == '}' && peek == '{')) {
                    stack.pop();
                    continue;
                }
                if (current == '{' || current == '[' || current == '(') {
                    stack.push(current);
                    continue;
                }
                return false;
            } else {
                stack.push(array[i]);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 用数组模拟栈
     * @param s
     * @return
     */
    public boolean isValidUseArray(String s) {
        final int len = s.length();
        if (len % 2 == 1) return false;
        final int n = len / 2;

        char[] stack = new char[n];
        int last = -1;

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if ((ch == '(') || (ch == '{') || (ch == '[')) {
                last++;
                if (last >= n) return false;
                stack[last] = ch;
            } else if (last < 0) {
                return false;
            } else if (ch == ')') {
                if (stack[last] != '(') return false;
                last--;
            } else if (ch == ']') {
                if (stack[last] != '[') return false;
                last--;
            } else if (ch == '}') {
                if (stack[last] != '{') return false;
                last--;
            }
        }
        return last == -1;
    }

    public static void main(String[] args) {
        Problem20 problem20 = new Problem20();
        problem20.isValid("([[]])");

    }
}
