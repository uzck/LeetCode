package easy;

/**
 * Best Time to Buy and Sell Stock II
 * 输入是一只股票在第i天的价格 设计算法找出最大利润 可以交易任意次
 * 但是买和卖不能同时进行
 */
public class Problem122 {

    /**
     * 首先用贪心法 把会亏的交易全部排除
     * 比如[7,1,15,95,100,101,5]这样一组数据
     * 15-1+100-95+101-100 = 20 但是很明显可以看出来最大的利润应该是101-1=100也就是最大值-最小值(需要保证最小值先出现)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
       int sum = 0;
       int max = Integer.MIN_VALUE;
       int min = Integer.MAX_VALUE;
       int minIndex = -1;
       int maxIndex = -1;
       for (int i = 0; i < prices.length - 1; i++) {
           int diff = prices[i+1] - prices[i];
           if (diff > 0) {
               sum += diff;
           }
           if (max < Math.max(prices[i], prices[i+1])) {
               max = Math.max(prices[i], prices[i+1]);
               maxIndex = prices[i] > prices[i+1] ? i : i+1;
           }
           if (min > Math.min(prices[i], prices[i+1])) {
               min = Math.min(prices[i], prices[i+1]);
               minIndex = prices[i] < prices[i+1] ? i : i+1;
           }
       }
       return Math.max(sum, minIndex < maxIndex ? max-min : 0);
    }

    public static void main(String[] args) {
        Problem122 problem122 = new Problem122();
        int result = problem122.maxProfitPeakValley(new int[] {7, 1, 15, 7, 95, 93, 100, 4});
        System.out.println(result);
    }

    /**
     * 第i天买第i+1天卖亏了的点定义为valley 赚了的点定义为peak
     * 假如peak2>peak1 valley1<valley2 假设valley2和peak1是连续的 peak1-valley1=A peak2-valley2=B peak2-vally1=C C<A+B
     * 因为valley2必然要小于peak1 否则就不是valley了
     * 每个peak-valley的值相加为所求
     * @param prices
     * @return
     */
    public int maxProfitPeakValley(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            // 如果peak和valley数目不等 最后是valley的话上述代码让peak=valley
            // 最后一次maxprofit += 0
            maxprofit += peak - valley;
        }
        return maxprofit;
    }

    /**
     * 只要prices[i+1] > prices[i] 就是赚了 如果数据是递增的 这个逻辑很容易理解
     * 当数据为[7, 1, 15, 7, 95, 93, 100, 4] 中间出现亏损的情况
     *
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/
     * @param prices
     * @return
     */
    public int maxProfitOnePass(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
