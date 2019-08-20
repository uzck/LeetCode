package easy;

/**
 * Repeated Substring Pattern
 * 输入一个字符串 判断能否由一个子串重复若干次组成
 * 字符串中只含有小写英文字母 长度不超过1000
 */
public class Problem459 {

    /**
     * 14ms 72.14%
     * 从长度为1的pattern开始直到pattern长度为s.length / 2
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        char[] data = s.toCharArray();
        int patternSize = 1;
        while (patternSize <= s.length() / 2) {
            // 如果不能整除 说明该长度的pattern无效
            if (s.length() % patternSize != 0) {
                patternSize += 1;
                continue;
            }
            boolean repeat = true;
            for (int i = 0; i < s.length() - patternSize; i++) {
                if (data[i] != data[i+patternSize]) {
                    repeat = false;
                    break;
                }
            }
            if (repeat) {
                return true;
            }
            patternSize += 1;
        }
        return false;
    }

    /**
     * 29ms 41.16%
     * https://leetcode.com/problems/repeated-substring-pattern/discuss/94344/Simple-Java-solution-2-lines
     * https://leetcode.com/problems/repeated-substring-pattern/discuss/94334/easy-python-solution-with-explaination
     * discuss里很牛逼的一个思路
     * 
     * @param s
     * @return
     */
    public boolean repeatedSubstringPatternOneLine(String s) {
        return (s+s).substring(1,2*s.length()-1).contains(s);
    }

    public static void main(String[] args) {
        Problem459 problem459 = new Problem459();
        problem459.repeatedSubstringPatternOneLine("aabaaba");
    }
}
