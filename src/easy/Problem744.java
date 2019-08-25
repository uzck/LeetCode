package easy;

/**
 * Find Smallest Letter Greater Than Target
 * 输入排序后的只含有小写字母的字符串 找到比target大的最小的字符
 * 字符表环状: target为z的时候 如果字符串里存在a的话a就是符合条件的最小值
 * letters的长度[2,10000]
 *
 */
public class Problem744 {

    /**
     * 0ms 100%
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        // 因为已经排好序了 所以如果最后一个字母大于等于target 返回第一个
        if (letters[n-1] <= target) {
            return letters[0];
        }
        for (char c : letters) {
            if (c > target) {
                return c;
            }
        }
        // 无意义
        return 'z';
    }

    /**
     * 二分查找
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetterBS(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (letters[mi] <= target) lo = mi + 1;
            else hi = mi;
        }
        return letters[lo % letters.length];
    }
}
