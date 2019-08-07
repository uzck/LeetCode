package easy;

/**
 * 输入为全是小写字母的字符串 移除字符串中相邻且相等的两个字母
 * 每次移除一组会影响接下来的结果 "abbaca" 删掉"bb"后变成了"aaca"这里"aa"就相邻了
 */
public class Problem1047 {


    /**
     * 用栈(数组模拟栈)来解决
     * 第一个元素入栈 之后每次比较栈顶元素和要入栈的元素是否相同 如果相同 弹出当前栈顶元素
     * 用数组指针可以减少栈的pop操作
     * @param S
     * @return
     */
    public String removeDuplicatesStack(String S) {
        int i = 0, n = S.length();
        char[] stack = new char[n];
        for (int j = 0; j < n; ++j)
            if (i > 0 && stack[i - 1] == S.charAt(j))
                --i;
            else
                stack[i++] = S.charAt(j);
        return new String(stack, 0, i);
    }

    /**
     * 165ms 17.48% 太菜了
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        char[] inputChar = S.toCharArray();
        while (!noDuplicate(inputChar)) {

        }
        StringBuilder builder = new StringBuilder();
        for (char c : inputChar) {
            if (c != 0) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 把原字符串中删除掉的元素标记为0(因为输入限定了是小写字母),表示已经被删除
     * 每次找到不为0的前后两项依次比较
     * @param inputChar
     * @return
     */
    public boolean noDuplicate(char[] inputChar) {
        int current = 0;
        for (int i = 0; i < inputChar.length; i++) {
            if (inputChar[current] != 0) {
                current = i;
                break;
            }
        }
        if (current >= inputChar.length) {
            return true;
        }
        for (int i = current; i < inputChar.length; i++) {
            int j;
            for (j = i + 1; j < inputChar.length; j++) {
                if (inputChar[j] != 0) {
                    break;
                }
            }
            if (j == inputChar.length) {
                return true;
            }
            if (inputChar[i] == inputChar[j]) {
                inputChar[i] = 0;
                inputChar[j] = 0;
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Problem1047 problem1047 = new Problem1047();
        String result = problem1047.removeDuplicates("abbaca");
        System.out.println(result);
    }
}
