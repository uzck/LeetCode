package medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Letter Combination of a Phone Number
 * 九宫格输入法是用数字2-9代表英文字母a-z
 * 输入若干[2-9]的数字 输出所有可能的英文组合
 */
public class Problem17 {

    char[] map = new char[] {'\u0000', 'a', 'd', 'g', 'j', 'm', 'p', 't', 'w'};

    /**
     * 3ms 6.94%
     * 回溯法
     * 先用char[]映射每个数字对应的开始字母
     * @param digits
     * @return
     */
    public List<String> letterCombinationsBackTrace(String digits) {

        List<String> result = new ArrayList<>();
        char[] current = new char[digits.length()];
        insertLetter(0, result, digits.toCharArray(), current);
        return result;
    }

    public void insertLetter(int index, List<String> result, char[] source, char[] current) {
        if (index == source.length) {
            // 去掉\u0000
            result.add(new String(current).replaceAll("\u0000", ""));
            return;
        }
        char c = source[index];
        if (c == '1' || c == '0') {
            return;
        }
        char[] choice = new char[4];
        // 7和9对应的是4个字母
        if ((c - '0') == 7 || (c - '0') == 9) {
            char first = map[c - '0'];
            choice[0] = first;
            choice[1] = (char) (first + 1);
            choice[2] = (char) (first + 2);
            choice[3] = (char) (first + 3);
        } else {
            char first = map[c - '0'];
            choice[0] = first;
            choice[1] = (char) (first + 1);
            choice[2] = (char) (first + 2);
        }

        for (int i = 0; i < choice.length; i++) {
            if (choice[i] == '\u0000')
                continue;
            current[index] = choice[i];
            insertLetter(index + 1, result, source, current);
        }
    }

    /**
     * 0ms 100%
     * 每次往后追加字母
     * 比如"23" 第一批循环后list里面是["a","b","c"]
     * 第二批循环时分别取出"a","b","c"然后往这三个后面分别添加"d","e","f"再存回list中
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {

        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        // 结果长度和原始长度相等
        while(ans.peek().length()!=digits.length()){
            String remove = ans.remove();
            String map = mapping[digits.charAt(remove.length())-'0'];
            for(char c: map.toCharArray()){
                ans.addLast(remove+c);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem17 problem17 = new Problem17();
        problem17.letterCombinations("892858");
    }
}
