package easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Two City Scheduling
 * 一个公司2N个人准备去A和B两个城市 第i个人去城市A的成本是cost[i][0] 去城市B的成本是cost[i][1]
 * 分别有N个人去A市和B市 求最小成本
 *
 */
public class Problem1029 {

    /**
     * 2ms 80.13% O(nlogn) + O(n)
     * 计算出每个人去A和B的差值diff diff > 0 说明更应该去B diff < 0 说明更应该去A
     * 如果diff相等 按去A的费用较低的顺序排序
     * 再从1开始到第N个人就应该去A市 之后的应该去B市
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // cost[0] - cost[1]如果值越小(负数) 说明越应该去A市 否则应该去B市
                if ((o1[0] - o1[1]) - (o2[0] - o2[1]) != 0) {
                    return (o1[0] - o1[1]) - (o2[0] - o2[1]);
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        int sum = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            sum += costs[i][0];
        }
        for (int i = costs.length / 2; i < costs.length; i++) {
            sum += costs[i][1];
        }
        return sum;

    }

    /**
     * 用dp方法... 完全不懂dp啊
     * 也可以用dfs实现的样子 我好菜啊...
     * @param costs
     */
    public int twoCitySchedCostDP(int[][] costs) {
        int N = costs.length / 2;
        // dp[i][j]表示i个人去A市 j个人去B市的费用
        // N+1是为了留一行一列用来存计算值
        int[][] dp = new int[N + 1][N + 1];
        // 这里是从1开始的 dp[0][0]一直是0
        for (int i = 1; i <= N; i++) {
            // 表示i个人去了A市 0个人去B市的cost
            dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
        }
        for (int j = 1; j <= N; j++) {
            // 表示j个人去了B市 0个人去A市的cost
            dp[0][j] = dp[0][j - 1] + costs[j - 1][1];
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 对第(i+j)的一个人来说 有被分配到A和B两种可能
                // 假如分配到A: dp[i-1][j] + costs[i+j-1][0]
                // 假如分配到B: dp[i][j-1] + costs[i+j-1][1]
                // 所以dp[i][j]的值等于两者最小值时最优
                dp[i][j] = Math.min(dp[i - 1][j] + costs[i + j - 1][0], dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }
        // 因为要求A,B两边各N,N所以返回dp[N][N]
        return dp[N][N];
    }
}
