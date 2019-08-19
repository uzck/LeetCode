package easy;

/**
 *  Min Cost Climbing Stairs
 *  爬第i个台阶的cost的是cost[i]
 *  每次可以移动一个或两个台阶
 *  找到从底到顶的最小费用
 *  可以从index 0或者index 1开始
 */
public class Problem746 {

    /**
     * 1ms 99.84%
     * 动态规划
     * 递推公式 dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2])
     * 第i个位置可以由i-1跳一步和i-2跳两步两种组合构成 因此求出到达每一个台阶的最小cost 递推到达到顶部的cost
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        // 顶没有cost
        int[] dp = new int[cost.length + 1];
        // 0和1的位置开始可以随便选 所以dp[0]=dp[1]=0
        dp[0] = 0;
        dp[1] = 0;
        // 2可以从1跳一步或者2跳一步到达
        dp[2] = Math.min(cost[0], cost[1]);
        for (int i = 3; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[cost.length];
    }

    /**
     * discuss里另一种解法
     * 本质差不多 但是节省了一个int[]数组的空间
     * @param cost
     * @return
     */
    public int minCostClimbingStairsSimple(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i-1], cost[i-2]);
        }
        return Math.min(cost[cost.length-1], cost[cost.length-2]);
    }

    public static void main(String[] args) {
        Problem746 problem746 = new Problem746();
        int result = problem746.minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
        System.out.println(result);
    }
}
