package easy;

/**
 * 输入两个整数 L,R 找到[L,R]范围内二进制表示的"1"的个数是素数的个数
 */
public class Problem762 {

    /**
     * 10^6 < 2^20 也就是1的个树最多也就是20
     * 先求20以内素数放在int[]中
     * @param L
     * @param R
     * @return
     */
    public int countPrimeSetBits(int L, int R) {
        int res = 0;
        int[] prime = new int[20];
        // 这个是自动求20以内的素数 也可以先算出来直接填
//        for (int i = 1; i <= 20; i++) {
//            if (isPrime(i)) {
//                prime[i-1] = 1;
//            }
//        }
        prime[1] = 1;
        prime[2] = 1;
        prime[4] = 1;
        prime[6] = 1;
        prime[10] = 1;
        prime[12] = 1;
        prime[16] = 1;
        prime[18] = 1;
        int oneCount;
        for (int i = L; i <= R; i++) {
            // 自己写的求bit位函数是导致性能很慢的原因
            // 用Integer.bitCount()就能100%;
            oneCount = oneCount(i);
            if (prime[oneCount - 1] == 1) {
                res += 1;
            }
        }
        return res;
    }

    public int oneCount(int num) {
        int oneCount = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                oneCount += 1;
            }
            num >>= 1;
        }
        return oneCount;
    }

    public boolean isPrime(int i) {
        if (i == 1) {
            return false;
        }
        for (int j = 2; j <= i / 2; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimeSetBitsMagic(int L, int R) {
        // 665772的二进制是0b10100010100010101100
        // 665772的二进制里1所在的位置就是20以内素数的值
        // 665772 >> bitCount后如果最低位是1 那么该数就是素数
        // 想出这方法的人是真滴牛批
        int count = 0;
        while (L <= R)
            count += 665772 >> Integer.bitCount(L++) & 1;
        return count;
    }

    /**
     * 另一种统计bit的方法 也很有意思
     * 核心思想: dp求解 i >> 1 = i / 2; i & 1 = i % 2
     * 把一个数分成两部分 比如10(1010)拆分为5(101)和0(0)
     * dp[i>>1]计算出5这部分的1的个数再加上dp[i & 1]计算出0这部分1的个数
     * 因为是迭代过来的dp[i >> 1]的部分在之前计算过了 用一个int[]存储起来给后面计算调用
     * 在这题里可以把[L,R]的bitCount用这种方式先全部算出来 再进行迭代判断是否是素数
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        if(num == 0)
            return new int[1];
        int[] dp = new int[num+1];

        dp[0] = 0;
        dp[1] = 1;

        for(int i=2;i<=num;i++){
            dp[i] = dp[i >> 1] + dp[i & 1]; //  i >> 1 is i / 2 and i & 1 is i % 2
        }
        return dp;
    }

    public static void main(String[] args) {
        Problem762 problem762 = new Problem762();
        problem762.countPrimeSetBits(6, 10);
    }

}
