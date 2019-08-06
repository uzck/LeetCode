package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 找出输入中匹配的序列
 * [a, b]和[c, d]匹配的条件：(a == c and b == d) or (a == d and b == c)
 */
public class Problem1128 {

    boolean checkIsPair(int[] p1, int[] p2) {
        if ((p1[0] == p2[1] && p1[1] == p2[0])
                || (p1[0] == p2[0] && p1[1] == p2[1])) {
            return true;
        }
        return false;
    }

    /**
     * 暴力迭代 超时
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairsBF(int[][] dominoes) {
        int count = 0;
        for (int i = 0; i < dominoes.length; i++) {
            for (int j = i + 1; j < dominoes.length; j++) {
                if (checkIsPair(dominoes[i], dominoes[j])) {
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     * 思想是把一个pair里较小的数交换到前面
     * 然后根据较小的数进行排序
     * 每次遍历的时候遍历到pair[0]不一样时即可停止
     * 621ms 5.1% 。哭了
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairVerySlow(int[][] dominoes) {
        // 把一个pair里较小的数交换到前面
        for (int i = 0; i < dominoes.length; i++) {
            int tmp = dominoes[i][0];
            if (dominoes[i][0] > dominoes[i][1]) {
                dominoes[i][0] = dominoes[i][1];
                dominoes[i][1] = tmp;
            }
        }
        Arrays.sort(dominoes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int count = 0;
        for (int i = 0; i < dominoes.length; i++) {
            for (int j = i + 1; j < dominoes.length && dominoes[j][0] == dominoes[i][0]; j++) {
                if (checkIsPair(dominoes[i], dominoes[j])) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int getPairMinValue(int[] pair) {
        return Math.min(pair[0], pair[1]);
    }

    /**
     * 对上面做的一个优化 还是用了快排
     * 但是甚至更慢 900ms 。无话可说
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairEvenSlow(int[][] dominoes) {
        Arrays.sort(dominoes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return getPairMinValue(o1) - getPairMinValue(o2);
            }
        });
        int count = 0;
        for (int i = 0; i < dominoes.length; i++) {
            for (int j = i + 1; j < dominoes.length && getPairMinValue(dominoes[i]) == getPairMinValue(dominoes[j]); j++) {
                if (checkIsPair(dominoes[i], dominoes[j])) {
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     *
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairsV1(int[][] dominoes) {
        // 因为限定了dominoes[i][0]和dominoes[i][1]的范围在[0-9]之内
        int[][] n = new int[10][10];
        // 存储数字出现的次数 可能有多个重复值
        for (int[] d : dominoes)
            n[d[0]][d[1]]++;

        int ans = 0;
        for (int i = 1; i < 10; i++)
            for (int j = i; j < 10; j++) {
                // 对角线上的元素表示两个数字相同 所以只返回该位置的值
                // 不是对角线上的元素n[i][j]和n[j][i]是调换了前后位置的关系
                int sum = i == j ? n[i][j] : n[i][j] + n[j][i];
                // 大于1是至少两个数才是一个pair
                if (sum > 1)
                    // 差为1的等差数列求和公式
                    // 假设5个[2,1]和3个[1,2] sum = 8
                    // 每个[2,1]或者{1,2]都能配对除了自身外的其他点 因此7 * 8
                    // 然后每个pair都被计算了两遍再/ 2
                    ans += sum * (sum - 1) / 2;
            }
        return ans;
    }
}
