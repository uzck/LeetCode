package easy;

/**
 * Perfect Number
 * 如果一个数字等于除了自身外所有因数的总和返回true 数值范围不会大于1e8
 */
public class Problem507 {

    /**
     * 1ms 96.38%
     * @param num
     * @return
     */
    public boolean checkPerfectNumberNormal(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                // 刚好等于的时候两个i是一样的 加了会重复
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        // 这里因为在考虑1的时候多加了一个num
        return sum - num == num;
    }

    public int pn(int p) {
        return (1 << (p - 1)) * ((1 << p) - 1);
    }

    /**
     * 数论 Euclid-Euler Theorem
     * 如果(2^p)-1是质数, p是质树, (2^(p-1))((2^p)-1)是一个even perfect number
     * p = 2, 21(22-1) = 6
     * p = 3, 22(23-1) = 28
     * p = 5, 24(25-1) = 496
     * p = 7, 26(27-1) = 8128
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        int[] primes=new int[]{2,3,5,7,13,17,19,31};
        for (int prime: primes) {
            if (pn(prime) == num)
                return true;
        }
        return false;
    }
}
