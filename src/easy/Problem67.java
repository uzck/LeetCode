package easy;

import java.util.Stack;

/**
 * Add Binary
 * 输入两个二进制表示的数字 返回它们的和, 也用二进制表示
 * 用string转long 相加再转string的方法需要处理溢出
 */
public class Problem67 {

    /**
     * 4ms 8.69%... 我应该是个弱智...
     * 用两个栈存储数据 再依次出栈 从最低位开始依次相加
     * @param a
     * @param b
     * @return
     */
    public String addBinaryUseStack(String a, String b) {
        Stack<Character> aStack = new Stack<>();
        Stack<Character> bStack = new Stack<>();
        for (char c : a.toCharArray()) {
            aStack.push(c);
        }
        for (char c : b.toCharArray()) {
            bStack.push(c);
        }
        StringBuilder builder = new StringBuilder();
        boolean carry = false;
        while (!aStack.isEmpty() && !bStack.isEmpty()) {
            char popA = aStack.pop();
            char popB = bStack.pop();
            // 如果a和b相同长度 需要考虑最后一位相加的时候可能会出现加1
            if (popA == '1' && popB == '1') {
                if (carry) {
                    builder.append('1');
                } else {
                    builder.append('0');
                }
                carry = true;
            } else if ((popA == '1' && popB == '0') || (popA == '0' && popB == '1')) {
                if (carry) {
                    builder.append('0');
                } else {
                    builder.append('1');
                    carry = false;
                }
            } else if (popA == '0' && popB == '0') {
                if (carry) {
                    builder.append('1');
                } else {
                    builder.append('0');
                }
                carry = false;
            }
            // 如果两个栈都为空说明长度一样 数据处理完毕
            // 考虑最后一个位置进位的情况
            if ((aStack.isEmpty() && bStack.isEmpty()) && carry) {
                builder.append('1');
                return builder.reverse().toString();
            }
        }
        // 把剩下的数据和进位信息倒序添加到输出中
        if (!aStack.isEmpty()) {
            insertData(builder, aStack, carry);
        } else if (!bStack.isEmpty()) {
            insertData(builder, bStack, carry);
        }
        return builder.reverse().toString();
    }

    public void insertData(StringBuilder builder, Stack<Character> data, boolean carry) {
        while (!data.isEmpty()) {
            char pop = data.pop();
            if (pop == '1') {
                if (carry) {
                    builder.append('0');
                } else {
                    builder.append('1');
                    carry = false;
                }
            } else {
                if (carry) {
                    builder.append('1');
                } else {
                    builder.append('0');
                }
                carry = false;
            }
            if (data.isEmpty() && carry) {
                builder.append('1');
            }
        }
    }

    /**
     * 1ms 100%
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        // 这个写法就可以不考虑哪个字符串更短
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2); // sum = a + b + carry
            carry = sum / 2; // carry为1或0
        }
        // 如果最后有进位 在最后面加1
        if (carry != 0) sb.append(carry);
        // 翻转输出
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Problem67 problem67 = new Problem67();
        problem67.addBinaryUseStack("1", "111");
    }
}
