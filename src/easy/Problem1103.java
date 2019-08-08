package easy;

/**
 * 有一堆糖分给n个人 第1次给第一个人1个 第2次给第二个人2个 第n+1次时给第一个人n+1个
 * 如果第k次糖不够k个 最后一个人拿走所有剩下的 结束循环
 */
public class Problem1103 {

    /**
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int index = 0;
        int round = 1;
        while (candies >= round) {
            candies -= round;
            result[index] += round;
            index = (index + 1) % num_people;
            round += 1;
        }
        if (candies > 0) {
            result[index] += candies;
        }
        return result;
    }

    /**
     * O(n)
     * 第i(假设i从1开始和数组下标不一样)个人第一次是拿i个 第二次是i + n个
     * 第i个人拿到的糖果数量是个公差为n的等差数列 ((i + x * n) + i) * x / n
     * 分发的糖果数量是公差为1的等差数列第x次结束时发的糖果数为x * n 约束不等式: (x * n + 1) * x * n / 2 <= candies
     * (xn)^2 + (xn) - 2 * candies = 0 求这个一元二次方程组的最大解并下取整 xn = (-1 + sqrt(1 + 8 * candies)) / 2 = -0.5 + sqrt(0.25 + 2 * candies)
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandiesSqrt(int candies, int num_people) {
        int n = num_people;
        // how many people received complete gifts
        int p = (int)(Math.sqrt(2 * candies + 0.25) - 0.5);
        int remaining = (int)(candies - (p + 1) * p * 0.5);
        int rows = p / n, cols = p % n; // rows表示完整分给所有人的次数 cols表示最后一轮在哪个人停止

        int[] d = new int[n];
        for(int i = 0; i < n; ++i) {
            // (i+1) * rows是指如果每次第i个人都拿到i+1个的数量
            // 后面部分表示 n 2n 3n 4n ... (rows - 1) * n的求和 n * (1 + rows - 1) * (rows - 1) / 2.
            d[i] = (int) ((i + 1) * rows + (rows * (rows - 1) * 0.5) * n);
            // cols in the last row
            if (i < cols) {
                d[i] += i + 1 + rows * n;
            }
        }
        // remaining candies
        d[cols] += remaining;
        return d;
    }
}
