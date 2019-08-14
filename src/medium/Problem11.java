package medium;

/**
 * Container With Most Water
 * 输入n个非负整数, 每个表示该x轴下的高度
 * 这些线构成一个容器 计算这个容器最多可以放多少水 选择了两根线之后不需要考虑之间的线
 */
public class Problem11 {

    /**
     * 暴力法
     * 178ms 26.72%
     * @param height
     * @return
     */
    public int maxAreaBF(int[] height) {
        if (height.length == 0 || height.length == 1) {
            return 0;
        }
        int length = height.length;
        // dp[i][j]表示第i根和第j根线构成的容器的面积
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int area = area(i, j, height[i], height[j]);
                if (area > max) {
                    max = area;
                }
            }
        }
        return max;
    }

    public int area(int i, int j, int line1, int line2) {
        return Math.abs(i-j) * Math.min(line1, line2);
    }

    /**
     * 1ms 100%
     * 双指针法 https://leetcode.com/problems/container-with-most-water/discuss/200246/Proof-by-formula
     * 储水面积由i到j的距离和最短高度决定
     * 先求出最远的container 再向中间逼近
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int water = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            water = Math.max(water, (j - i) * h);
            // height[i] < height[j]的情况 且(j-i)一直变小
            // 因为容器的高度由最短的决定 所以再找到下两个个更大的高度之前 容器的面积只会越来越小
            // 这个是height[i] <= height[j]的情况
            while (height[i] <= h && i < j) i++;
            // 这个是height[j] <= height[i]的情况
            while (height[j] <= h && i < j) j--;
        }
        return water;

    }
}
