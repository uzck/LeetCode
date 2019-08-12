package easy;

/**
 * Implement strStr()
 * 找子串是否存在 如果存在返回第一个字符出现的位置 否则返回-1
 */
public class Problem28 {

    int minIndex;

    /**
     * 2ms 52.8% 以haystack里面每一和needle[0]相同的字母作为开头 往后查看是否匹配
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String  needle) {
        char[] stackArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();
        // 考虑如果needle为""的情况下 该返回什么结果
        if (needle == null || needle.equals("")) {
            return 0;
        }
        minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < stackArray.length; i++) {
            if (stackArray[i] == needleArray[0]) {
                minIndex = Math.min(minIndex, Integer.MAX_VALUE);
                findStr(stackArray, i, needleArray, 0, needleArray.length);
            }
        }
        return minIndex == Integer.MAX_VALUE ? -1 : minIndex;
    }

    public void findStr(char[] source, int sourceStart, char[] template, int tmplStart, int remain) {
        if (source[sourceStart] == template[tmplStart]) {
            remain -= 1;
        } else {
            return;
        }
        if (remain > 0) {
            // 如果后面空间不足
            if (sourceStart + remain >= source.length) {
                return;
            }
            findStr(source, sourceStart+1, template, tmplStart + 1, remain);
        } else if (source[sourceStart] == template[tmplStart] && remain == 0){
            minIndex = minIndex == Integer.MAX_VALUE ? sourceStart - template.length + 1 : Math.min(sourceStart - template.length + 1, minIndex);
        }
    }

    /**
     * 上述方法的简化版
     * @param haystack
     * @param needle
     * @return
     */
    public int strStrSimple(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                // 考虑到了needle为""的情况
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    public static void main(String[] args) {
        Problem28 problem28 = new Problem28();
        int result = problem28.strStr("mississippi", "issipi");
        System.out.println(result);
    }
}
