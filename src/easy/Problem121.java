package easy;

/**
 * Best Time to buy and sell stock
 * 输入是一只股票在第i天的价格 设计算法找出最大利润 最多只能交易一次
 * 卖之前必须要买股票
 */
public class Problem121 {

    /**
     * 求累计的最大和
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // 当前的和
        int current = 0;
        // 最大值
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            // 如果迟一天卖 赚的钱
            int diff = prices[i+1] - prices[i];
            if (diff < 0) {
                // 如果迟一天卖本了 就放弃之前的操作
                current += diff;
                if (current < 0) {
                    current = 0;
                    continue;
                }
            } else {
                // 如果当天卖能赚 因为后面可能可以赚的更多 所以还得继续迭代
                current += diff;
                // 因为之后可能出现亏的情况 所以用最大值保存出现过的最大情况
                max = Math.max(max, current);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Problem121 problem121 = new Problem121();
        problem121.maxProfit(new int[] {7,1,5,3,6,4});
    }
}
