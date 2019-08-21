package easy;

/**
 * Arranging Coins
 * 用n个硬币 摆一个台阶 第k个台阶必须要有k个硬币
 * 问n个硬币最多能摆多少硬币
 */
public class Problem441 {

    /**
     * 直接迭代
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        int stairs = 0;
        for (int i = 1; i <= n;) {
            n -= i;
            stairs += 1;
            i += 1;
        }
        return stairs;
    }

    /**
     * 1ms 100%
     * 等差数列求和 需要考虑溢出问题
     * @param n
     * @return
     */
    public int arrangeCoinsMath(int n) {
        return (int)(Math.sqrt(1. / 4 + (2 * (long)n)) - 1./ 2);
    }

    public static void main(String[] args) {
        Problem441 problem441 = new Problem441();
        int result = problem441.arrangeCoinsMath(1301320423);
        System.out.println(result);
//        System.out.println(Math.sqrt((long)8 * 1301320423 - 1) / 2.);
//        System.out.println(Integer.MAX_VALUE);
    }
}
