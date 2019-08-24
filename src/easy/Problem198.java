package easy;

/**
 * House Robber
 * 假设是一个强盗要去抢一条街 但是如果抢了一家之后不能抢相邻的房子
 * 找出最佳的抢劫方案
 */
public class Problem198 {

    /**
     * 0ms 100%
     * 动态规划 递推公式 dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1])
     * dp[i]存储抢到第i家的最大收货
     * dp[i]的值有两种可能的组成:
     * 1. 抢了第i-1家 所以不能抢第i家 dp[i] = dp[i-1]
     * 2. 没有抢第i-1家 所以收益变为dp[i] = dp[i-2] + nums[i-1] 取i-2是因为要收益最大化肯定不能中间有空
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        dp[0] = 0;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Problem198 problem198 = new Problem198();
        problem198.rob(new int[] {2, 7, 9, 3, 1});
    }
}
