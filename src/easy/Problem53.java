package easy;

/**
 * Maxium Subarray
 * 找出数组中和最大的连续子串 计算最大的和
 */
public class Problem53 {

    /**
     * 需要特殊处理只有负数的数组
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int count = 0;
        int res = 0;
        int maxVal = Integer.MIN_VALUE; //
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
            if (count + nums[i] < 0) {
                count = 0;
                continue;
            } else {
                count += nums[i];
                res = Math.max(res, count);
            }
        }
        return maxVal < 0 ? maxVal : res;
    }

    /**
     * 类似的做法
     * 用的不同方法处理全为负数的数组
     * @param nums
     * @return
     */
    public int maxSubArraySimple(int[] nums) {
        int len = nums.length;
        if(len==0)
            return 0;
        if(len==1)
            return nums[0];
        int start=0;

        int currentMax=nums[start];
        int sumTemp=currentMax;
        for(int i=1;i<len;i++){
            sumTemp+=nums[i];

            // sumTemp < nums[i]成立的条件是sumTemp也是负数
            if(sumTemp<nums[i]){
                sumTemp=nums[i];
            }
            if(currentMax<sumTemp){
                currentMax=sumTemp;
            }
        }
        return currentMax;
    }

    /**
     * 这个问题的dp方法 https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts
     * 递推式: sum(0,i) = a[i] + (sum(0, i-1) > 0 ? 0 : sum(0, i-1))
     * sum(0, i)表示a[0]到a[i]的子数组和 如果子数组之和小于0就不需要加到当前数上
     * @param nums
     * @return
     */
    public int maxSubArrayDP(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Problem53 problem53 = new Problem53();
        problem53.maxSubArrayDP(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }
}
