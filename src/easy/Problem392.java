package easy;

import java.util.*;

/**
 * Is Subsequence
 * 输入字符串s和t 判断s是不是t的子串
 * s和t中只有小写字母 t可能非常长(长度约等于500000) s是个较短的字符串
 * 这里subsequence的定义: 可以通过删除t中的部分字符使t == s
 * Follow UP:
 * 如果输入不止一个S 而是一堆字符串判断是不是t的子串 不用逐个判断的方法该怎么写
 */
public class Problem392 {

    /**
     * 10ms 80.69%
     * O(n)没啥难点
     * 思路: 遍历一遍t 如果能依次找到s中的字符 返回true
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.equals("")) {
            return true;
        }
        char[] sData = s.toCharArray();
        char[] tData = t.toCharArray();
        int index = 0;
        for (int i = 0; i < tData.length && index < s.length(); i++) {
            // 如果t中出现了s中的字符 index从0开始保证了字符出现有序
            if (tData[i] == sData[index]) {
                index++;
            }
        }
        return s.length() == index;
    }

    /**
     * 当输入的S较多的情况下用的算法
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequenceBinarySearch(String s, String t) {

        if (s == null || t == null) {
            return false;
        }

        Map<Character, List<Integer>> map = new HashMap<>();

        // 依次存储t的各个字符 每个字符对应一个list(出现的下标)
        for (int i = 0; i < t.length(); i++) {
            char curr = t.charAt(i);
            if (!map.containsKey(curr)) {
                map.put(curr, new ArrayList<>());
            }
            map.get(curr).add(i);
        }

        int prev = - 1; // 上一个字符位置
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果t中不含有s的字符 返回false
            if (map.get(c) == null) {
                return false;
            } else {
                List<Integer> list = map.get(c);
                // 当前字符出现的位置要大于上一个字符出现的位置
                prev = binarySearch(prev, list, 0, list.size() - 1);
                if (prev == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 二分查找
     * @param index
     * @param list
     * @param start
     * @param end
     * @return
     */
    private int binarySearch(int index, List<Integer> list, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // 如果mid的值比index小 往后搜索
            if (list.get(mid) < index) {
                start = mid + 1;
            } else {
                // 否则往前搜索
                end = mid - 1;
            }
        }

        // 如果没找到大于index的结果 返回-1 否则返回list.get(start)
        return start == list.size() ? -1 : list.get(start);
    }
}
