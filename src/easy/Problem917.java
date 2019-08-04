package easy;

import java.util.Stack;

/**
 * 只翻转字符串中的英文字母 其他字符保持不变
 * Test1ng-Leet=code-Q!
 * Qedo1ct-eeLg=ntse-T!
 */
public class Problem917 {

    public boolean isLetter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }

    /**
     * 用栈来做翻转
     * @param S
     * @return
     */
    public String reverseOnlyLettersUseStack(String S) {
        Stack<Character> letters = new Stack();
        // 存入栈中
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        // 从头遍历字符串
        // 如果是字母 从栈中弹出栈顶元素(实现交换)
        // 否则直接添加到末尾
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }


    public String reverseOnlyLetters(String S) {
        char[] sChar = S.toCharArray();
        int start = 0;
        int end = sChar.length - 1;
        while (start < sChar.length && end >= 0 && start < end) {
            // 从左边找到属于字母的字符
            while (start < sChar.length && !isLetter(sChar[start])) {
                start += 1;
            }
            // 从右边找到属于字母的字符
            while (end >= 0 && !isLetter(sChar[end])) {
                end -= 1;
            }
            // 交换
            if (start < end) {
                char tmp = sChar[end];
                sChar[end] = sChar[start];
                sChar[start] = tmp;
            }
            start += 1;
            end -= 1;
        }
        return new String(sChar);
    }

    public static void main(String[] args) {
        Problem917 problem917 = new Problem917();
        System.out.println(problem917.reverseOnlyLetters("7_28]"));
    }
}
