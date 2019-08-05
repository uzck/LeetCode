package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个字符串  字符串中的字母可以自由变换为大小写
 * 输出所有可能的情况
 */
public class Problem784 {

    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList();
        if(S == null || S.length() == 0) return result;
        char[] chars = S.toCharArray();
        helper(chars, 0, result);
        return result;
    }

    private void helper(char[] chars, int start, List<String> result) {
        // 遍历到字符串末尾了 添加结果并开始回溯
        if(start == chars.length) {
            result.add(new String(chars));
            return;
        }
        // 如果是数字的话不做处理 继续向后递归
        if(Character.isDigit(chars[start])) {
            helper(chars, start+1,result);
        } else {
            // 先递归原本的值
            helper(chars, start+1,result);
            // 做转换
            chars[start] = transform(chars[start]);
            // 递归转换后的值
            helper(chars, start+1,result);
        }
    }

    /**
     * 大写转小写 小写转大写
     * @param c
     * @return
     */
    private char transform(char c) {
        if(c >= 'a' && c <='z') {
            c += 'A' - 'a';
        }else if(c >= 'A' && c <='Z') {
            c += 'a' - 'A';
        }
        return c;
    }

    public static void main(String[] args) {
        Problem784 problem784 = new Problem784();
        problem784.letterCasePermutation("a1b2");
    }
}
