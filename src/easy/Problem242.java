package easy;

/**
 * Valid Anagram(相同字母异序词)
 * 输入字符串s和t 判断t可不可以从s通过换位生成
 * 字符串全是小写字母
 */
public class Problem242 {

    /**
     * 4ms 78.64%
     * 用两个int[]存储s和t出现的字母 只要每个字母出现的次数相同 就是相同字母异序词
     * 这个方法只能用在ascii码中 对于unicode无法使用 要处理unicode的话需要把int[]换成hashtable
     * 也可以对两个char[]排序 然后比较两个string是否相等
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramUseArray(String s, String t) {
        int[] sChar = new int[26];
        int[] tChar = new int[26];
        char[] sData = s.toCharArray(); // 把这个写在循环里就慢了2ms 奇妙
        char[] tData = t.toCharArray();
        for (char c : sData) {
            sChar[c-'a'] += 1;
        }
        for (char c : tData) {
            tChar[c-'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (sChar[i] != tChar[i]) {
                return false;
            }
        }
        return true;
    }
}
