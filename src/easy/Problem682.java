package easy;

import java.util.Stack;

/**
 * 棒球比赛计分
 */
public class Problem682 {

    public int calPoints(String[] ops) {
        int result = 0, data, remove, dNum, tmp = 0, origin = 0;
        Stack<Integer> stack = new Stack<>();
        for (String s : ops) {
            if (s.equals("+")) {
                if (stack.size() >= 2) {
                    tmp = stack.pop();
                    origin = stack.peek();
                    result += tmp + origin;
                }
                stack.push(tmp);
                stack.push(tmp + origin);
            } else if (s.equals("C")) {
                remove = stack.pop();
                result -= remove;
            } else if (s.equals("D")) {
                dNum = stack.peek();
                result += dNum * 2;
                stack.add(dNum * 2);
            } else {
                // 数字的情况
                data = Integer.parseInt(s);
                result += data;
                stack.push(data);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] data = new String[] {"5","-2","4","C","D","9","+","+"};
        Problem682 problem682 = new Problem682();
        System.out.println(problem682.calPoints(data));
    }
}
