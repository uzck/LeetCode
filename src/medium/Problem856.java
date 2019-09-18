package medium;

import java.util.Stack;

/**
 * Score of Parentheses
 * 输入字符串S S只由"("和")"构成 且()正常配对
 * ()算一分
 * AB=A+B
 * (A) = 2 * A
 * 统计字符串的分数
 */
public class Problem856 {


    /**
     * 看的solution 没做出来
     * @param S
     * @return
     */
    public int scoreOfParenthese(String S) {
        Stack<Integer> stack = new Stack<>();
        char[] data = S.toCharArray();
        for (int i = 0; i < data.length; i++) {
            // 如果data[i] == '(' 从当前层重新开始计算 新的score为0
            if (data[i] == '(') {
                stack.push(0);
            } else {
                int v = stack.pop();
                int w = stack.pop();
                // Math.max处理遇到"()"的情况
                stack.push(w + Math.max(2 * v, 1));
            }
        }
        return stack.pop();
    }

    public int scoreParentheseLessSpace(String S) {
        int ans = 0, bal = 0; // bal记录深度 ans记录最后结果
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                // 如果为( 加一层深度
                bal++;
            } else {
                bal--;
                // 如果)的前面是(说明是()的组合
                if (S.charAt(i-1) == '(')
                    ans += 1 << bal;
            }
        }

        return ans;
    }
}
