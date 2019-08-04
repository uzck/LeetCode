package easy;

import java.util.Stack;

/**
 * 字符串中所有字符距离指定字符的距离
 */
public class Problem821 {


    /**
     * 论坛里0ms的解法
     * 初始为last为-1
     * @param S
     * @param C
     * @return
     */
    public int[] shortestToCharFast(String S, char C) {
        int[] res = new int[S.length()];
        int last = -1;
        while (last != S.length() - 1) {
            // 从上一个C之后找下一个C的位置
            int index = S.indexOf(C, last + 1);
            // 如果后面还有C
            if (index != -1) {
                // last == 0时 也就是找到第一个C所在的位置
                // 更新C之前字符的距离
                for (int i = last + 1; i <= index; i++) {
                    if (last != -1) {
                        res[i] = Math.min(i - last, index - i);
                    } else {
                        // 初始执行的代码
                        res[i] = index - i;
                    }
                }
                last = index;
            } else {
                // 后面没有C了
                for (int i = last + 1; i < res.length; i++) {
                    res[i] = i - last;
                }
                last = res.length - 1;
            }
        }
        return res;
    }


    /**
     * 先从前往后扫描一遍 得到第一个结果
     * 再从后往前扫描，如果这次得到的距离比上次的近，则更新距离值
     * @param S
     * @param C
     * @return
     */
    public int[] shortestToCharScanTwice(String S, char C) {
        int N = S.length();
        int[] ans = new int[N];
        int prev = Integer.MIN_VALUE / 2;

        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == C) prev = i;
            ans[i] = i - prev;
        }

        prev = Integer.MAX_VALUE / 2;
        for (int i = N-1; i >= 0; --i) {
            if (S.charAt(i) == C) prev = i;
            ans[i] = Math.min(ans[i], prev - i);
        }

        return ans;
    }

    public int[] shortestToChar(String S, char C) {
        int[] dist = new int[S.length()];
        char[] array = S.toCharArray();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                dist[i] = 0;
                continue;
            }
            dist[i] = -1;
        }
        int left, right;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                continue;
            }
            // 双指针遍历找最近的字符C所在的位置
            left = i;
            right = i;
            while (left >= 0) {
                if (array[left] != C) {
                    left--;
                } else {
                    break;
                }
            }
            while (right < array.length) {
                if (array[right] != C) {
                    right++;
                } else {
                    break;
                }
            }
            if (left == -1 && right < array.length) {
                dist[i] = right - i;
            } else if (left >= 0 && right >= array.length) {
                dist[i] = i - left;
            } else if (left >= 0 && right < array.length) {
                dist[i] = Math.min(i - left, right - i);
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Problem821 problem821 = new Problem821();
        int[] result = problem821.shortestToChar("kdsfaeraldhsflajehasksdasdhfasljleiqlqjflasjdfa", 'e');
    }
}
