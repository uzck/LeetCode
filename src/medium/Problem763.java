package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Partition Labels
 * 输入字符串S 把S分为若干部分 每个字母只能出现在一个部分中 尽可能多的分段并输出这些子字符串的长度
 * 字符串长度在[1,500] 只含有小写字母
 */
public class Problem763 {

    /**
     * 3ms 87.7%
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        // 用一个二维数组存储每个字母出现的开始和最后位置
        int[][] alpha = new int[26][2];
        for (int i = 0; i < alpha.length; i++) {
            // 默认不存在的情况alpha[i][0] = -1
            alpha[i][0] = -1;
            alpha[i][1] = 501;
        }
        // 返回结果
        List<Integer> ans = new ArrayList<>();
        char[] data = S.toCharArray();
        for (int i = 0; i < data.length; i++) {
            // 如果字母还没出现过 在alpha[i][0]的位置赋值
            if (alpha[data[i] - 'a'][0] == -1) {
                alpha[data[i] - 'a'][0] = i;
            } else {
                alpha[data[i] - 'a'][1] = i;
            }
        }
        for (int i = 0; i < alpha.length; i++) {
            // 如果字母只出现了一次 把最初的位置赋值给最后位置
            if (alpha[i][0] != -1 && alpha[i][1] == 501) {
                alpha[i][1] = alpha[i][0];
            }
        }
        // 从字符串的第一个数据开始判断
        int start = data[0] - 'a';
        // 剩余未处理的字符串长度
        int remain = S.length();
        // 当前段的最小下标和最大下标
        int currentMin = 501, currentMax = -2;
        // 循环结束条件 没有未处理的字符串
        while (remain > 0 && start < 26) {
            currentMin = 501;
            currentMax = -2;
            // 总是成立 因为循环刚开始时处理的都是每一段的第一个数 currentMin和currentMax都处于默认值
            currentMin = alpha[start][0];
            currentMax = alpha[start][1];

            // 如果是只出现一次的单个字母
            if (currentMax == currentMin) {
                ans.add(1);
                remain -= 1;
                // 在data中下一段的首字母 考虑currentMax是否到达最后
                start = currentMax + 1 < S.length() ? data[currentMax + 1] - 'a' : data[currentMax] - 'a' ;
                continue;
            }
            // 如果是出现多次的字母 接着遍历整个alpha 找是否存在其他的字母范围和该字母的范围存在重叠
            // 如果有重叠 扩充范围
            boolean changed = false;
            while (true) {
                // 结束条件为 遍历一遍alpha后范围不变
                changed = false;
                for (int i = 0; i < alpha.length; i++) {
                    // 如果alpha[i][0] == -1说明该字母没有出现在字符串中
                    if (alpha[i][0] == -1) {
                        continue;
                    }
                    // 判断重叠 设两段分别为a=[l1,r1] b=[l2, r2] 直接分析的话是有四种情况
                    // 1. 左边部分重叠 2. 右边部分重叠 3.a包含b 4.b包含a
                    // 如果l1 < l2 && r1 > l2或者 l1 > l2 && l1 < r1 就可以包含上述四种情况
                    if ((alpha[i][0] < currentMin && alpha[i][1] > currentMin) || (alpha[i][0] > currentMin && alpha[i][0] < currentMax)) {
                        if (alpha[i][0] < currentMin) {
                            // 如果更小 更新最小范围 ? 这个理论上应该不会更新
                            currentMin = alpha[i][0];
                            changed = true;
                        }
                        if (alpha[i][1] > currentMax) {
                            // 如果最大范围变了 更新最大范围
                            currentMax = alpha[i][1];
                            changed = true;
                        }
                        // 标记为已处理
                        alpha[i][0] = -1;

                    }
                }
                // 如果最大范围没变 证明该段的范围确定了 退出循环
                if (changed == false) {
                    break;
                }
            }

            ans.add(currentMax - currentMin + 1);
            remain -= (currentMax - currentMin + 1);

            start = currentMax + 1 < S.length() ? (data[currentMax + 1] - 'a') : (data[currentMax] - 'a');
        }
        return ans;
    }

    /**
     * 贪心
     * 因为从每一段的开头遍历 所以不需要存储每个字母出现的最小位置
     * @param S
     * @return
     */
    public List<Integer> partitionLabelsGreedy(String S) {
        int[] last = new int[26];
        // 存储每个字母最后出现的位置
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        // j记录该段的最大下标 anchor记录该段最小下标
        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            // 如果i等于j说明该段已经遍历到最后了
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem763 problem763 = new Problem763();
        String s = "qiejxqfnqceocmy";
        List<Integer> ans = problem763.partitionLabels(s);
        for (int i : ans) {
            System.out.println(i);
        }
    }
}
