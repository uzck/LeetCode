package easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 假设外星人也用英文字母但是不同的顺序
 * 输入一堆外星人用的词语和他们的字母表顺序 如果词语按那字母表顺序排序正确了 返回true 否则false
 */
public class Problem953 {

    /**
     * 3ms 8.35%.....
     * 思想是存储新的存储规则
     * 拷贝一个新数组对该数组指定排序规则 排序后和原数组对比
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSortedSlow(String[] words, String order) {
        int[] newWords = new int[26];
        char[] orderChar = order.toCharArray();
        for (int i = 0; i < orderChar.length; i++) {
            newWords[orderChar[i] - 'a'] = i;
        }
        String[] wordsCopy = Arrays.copyOf(words, words.length);
        Arrays.sort(wordsCopy, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int o1Length = o1.length();
                int o2Length = o2.length();
                char[] o1Char = o1.toCharArray();
                char[] o2Char = o2.toCharArray();
                for (int i = 0; i < Math.min(o1Length, o2Length); i++) {
                    if (newWords[o1Char[i]-'a'] != newWords[o2Char[i]-'a']) {
                        return newWords[o1Char[i]-'a'] - newWords[o2Char[i]-'a'];
                    } else {
                        // 两者相等
                        continue;
                    }
                }
                // 两者在一定范围内都相等
                return o1Length - o2Length;
            }
        });
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals(wordsCopy[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean orderStr(String s1, String s2, int[] newWords) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        char[] s1Char = s1.toCharArray();
        char[] s2Char = s2.toCharArray();
        for (int i = 0; i < Math.min(s1Length, s2Length); i++) {
            // 如果newWords[x1] > newWords[x2] 顺序错了
            // newWords[x1] < newWords[x2] 正确
            if (newWords[s1Char[i]-'a'] > newWords[s2Char[i]-'a']) {
                return false;
            } else if (newWords[s1Char[i]-'a'] < newWords[s2Char[i]-'a']) {
                return true;
            }
        }
        // 两个字符串长度不等且较短的那个是较长的子串
        return s1Length <= s2Length;
    }

    /**
     * 依次比较前后两个字符串是否有序即可
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] newWords = new int[26];
        char[] orderChar = order.toCharArray();
        for (int i = 0; i < orderChar.length; i++) {
            newWords[orderChar[i] - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!orderStr(words[i], words[i+1], newWords)) {
                return false;
            }
        }
        return true;
    }
}
