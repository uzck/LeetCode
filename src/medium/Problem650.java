package medium;

/**
 * 一个notepad上有一个字母A 并且notepad只能执行复制和粘贴两个操作
 * 复制只能复制所有 粘贴只能粘贴上一次复制的内容
 * 要得到n个A的话 最少需要进行几次操作
 */
public class Problem650 {

    /**
     * Runtime: 18ms 33.48%
     * 内存占用: 32.9MB 5.26%
     * 生成1-n的所有可能 在多次查找的时候使用 dp解法
     * @param n
     * @return
     */
    public int minStepsTable(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 2;
        }
        int[] data = new int[n+1];
        data[1] = 0;
        data[2] = 2;
        for (int i = 3; i <= n; i++) {
            // 如果为奇数
            if (i % 2 == 1) {
                // 找到最大除去本身的因数x 在这个基础上只需要进行一次复制 n / x - 1次
                for (int j = i-1; j >= Math.sqrt(i); j--) {
                    if (i % j == 0) {
                        data[i] = data[j] + (i / j - 1) + 1; // dp公式
                        break;
                    }
                }
                // 质数的情况: 需要操作本身次数
                if (data[i] == 0) {
                    data[i] = i;
                }
            } else {
                // 如果为偶数 只需要把在i/2的基础上再复制粘贴 两次操作
                data[i] = data[i / 2] + 2;
            }
        }
        return data[n];
    }

    /**
     * 1ms 62.77%
     * 思路和上面求所有的一样 只是反过来推
     * 如果n / i == 0 那么n的最小操作次数是i的最小操作次数+ 1 + (n / i - 1)
     * @param n
     * @return
     */
    public int minStepsSlowOptimize(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 2;
        }
        int ans = 0;
        while (n > 1) {
            boolean find = false;
            // 找出能被整除的最大的数
            for (int i = n - 1; i >= Math.sqrt(n); i--) {
                if (n % i == 0) {
                    // 复制一次 粘贴(n/i-1)次 总共n/i次
                    ans += n / i;
                    // 更新n的值为i
                    n = i;
                    find = true;
                    break;
                }
            }
            // 如果迭代时没找到能整除的数 说明迭代到了一个质数 循环终止
            if (!find) {
                return n + ans;
            }
        }
        return -1;
    }

    /**
     * 贪心
     * 思路是找出n的最小的因数和
     * @param n
     * @return
     */
    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            // 下面C代表复制 P代表粘贴
            // 比如24 = 1 * 2 * 2 * 2 * 3 操作顺序为1 -> CP(2) -> CP(4) -> CP(8) -> CPP(24)
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem650 problem650 = new Problem650();
        int res = problem650.minSteps(15);
        System.out.println(res);
    }
}
