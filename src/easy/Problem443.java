package easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * String Compression
 * 找到重复的字母 在第一个字母后面用数字表示
 * 不需要考虑"abab" = "ab2" 这种情况
 * 修改原数组 不能返回新的
 */
public class Problem443 {

    /**
     * 1ms 97.12%
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int prev = 1; // 写入新结果的指针
        int count = 1; // 记录每个字母的出现次数
        int size = 1; // 新结果的长度
        for (int i = 1; i < chars.length; i++) {
            // 如果这个字母和前面字母不等 表示前面的字母统计完了
            if (chars[i] != chars[i-1]) {
                // 如果count == 1不需要往后面添加1
                if (count > 1) {
                    // count转char[] 可以用整除法来做 字符串长度限定在1000 所以最多也只有4位
                    int unit = count % 10;
                    int ten = (count / 10) % 10;
                    int hundred = (count / 100) % 10;
                    int thousand = (count / 1000) % 10;
                    if (thousand != 0) {
                        chars[prev++] = (char)(thousand + '0');
                        size += 1;
                    }
                    if (hundred != 0) {
                        chars[prev++] = (char)(hundred + '0');
                        size += 1;
                    }
                    if (ten != 0) {
                        chars[prev++] = (char)(ten + '0');
                        size += 1;
                    }
                        chars[prev++] = (char)(unit + '0');
                        size += 1;
//                    for (char c : String.valueOf(count).toCharArray()) {
//                        chars[prev++] = c;
//                        size += 1;
//                    }
                    // 把这一位添加到结果里 作为新的起始字母
                    if (prev < chars.length) {
                        chars[prev++] = chars[i];
                        size += 1;
                        count = 1;
                    }
                } else {
                    size += 1;
                    chars[prev++] = chars[i];
                }
                continue;
            }
            count += 1;
        }
        if (count > 1) {
            for (char c : String.valueOf(count).toCharArray()) {
                chars[prev++] = c;
                size += 1;
            }
        }
        return size;
    }

    /**
     * 双指针的简化版本
     * @param chars
     * @return
     */
    public int compressSimple(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Problem443 problem443 = new Problem443();
        char[] data = new char[] {'a','b','b','b','b','b','b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        problem443.compress(data);
        for (char c : data) {
            System.out.println(c);
        }
    }
}
