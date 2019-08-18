package medium;

/**
 * Bulb Switcher
 * 有n个开始是关着的电灯泡 首先打开所有的电灯 然后关掉每个第二个灯 第三次 操作第三个灯泡 把关的灯泡打开 开的关掉
 * 第i轮操作第i个灯泡 第n轮只能操作最后一个灯泡
 * 输出在第n轮后有多少灯泡是亮着的
 */
public class Problem319 {

    /**
     * TLE 超时
     * 思路:
     * n=2的时候[on, off]
     * n=3的时候[on, off, off]
     * n=4的时候[on, off, off, on]
     * 可以观察到前面的部分是不变的 增加n的时候只需要考虑最后一位
     * 经过第1轮和第n轮的时候 灯是灭的 因此考虑在[2,n-1]轮里有哪几次能操作第n盏灯
     * 也就是在[2,n-1]里能整除n的数
     * @param n
     * @return
     */
    public int bulbSwitchTLE(int n) {
        if (n == 0) {
            return 0;
        }
        int[] res = new int[n+1];
        res[1] = 1;
        int sum;
        for (int i = 2; i <= n; i++) {
            sum = 0;
            for (int j = 2; j <= (i / 2); j++) {
                if (i % j == 0) {
                    sum += 1;
                }
            }
            res[i] = res[i-1] + sum % 2;
        }
        return res[n];
    }

    /**
     * https://leetcode.com/problems/bulb-switcher/discuss/77104/Math-solution..
     * 哭了... 菜鸡就是菜鸡
     * 第n个灯泡 开还是关 取决于n能够分解成几组数的乘积
     * 比如n=36 可以分解成[1,36] [2,18] [3,12] [4,9] [6,6]这样五组情况
     * 前面四组 每组里面的数字不一样 因此可以抵消
     * 但最后一组数字是相同的 也只有这一个数字会让灯变为亮的状态
     * 所以在得到一个输入n的时候
     * 考虑 R=sqrt(n)下取整 [1,R]范围的所有值x * x < n 并且该x能让x*x位置的灯亮起来
     * 比如n=36 R=6
     * x=1的时候 第一盏灯会亮 x=2 第4盏灯会亮 x=3第9盏灯会亮 x=4 第16盏灯会亮 x=5 第25盏灯会亮 x=6 第36盏灯会亮
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {

        return (int)Math.sqrt(n);
    }

    public static void main(String[] args) {
        Problem319 problem319 = new Problem319();
        int result = problem319.bulbSwitchTLE(2);
        System.out.println(result);
    }
}
