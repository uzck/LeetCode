package easy;

/**
 * Factorial Trailing Zeros
 * 输入数字x 求x!尾部的0个数
 * 算法时间要求O(logn)
 */
public class Problem172 {

    /**
     * 阶乘后面的0来自与10 而数里面相乘等于10的只有2 * 5
     * 2在其他数里面很容易找到 因此问题变成找到数字里面含有5这个因数的数
     * 但还需要考虑像25有两个5 125有三个5
     * https://leetcode.com/problems/factorial-trailing-zeroes/discuss/52373/Simple-CC%2B%2B-Solution-(with-detailed-explaination)
     * 具体的解析见上面链接
     * 简要的思路:
     * 1. 4716 / 5   找出因数分解都包含了一个5的情况
     * 2. 4716 / 25  找出因数分解完有两个5的情况
     * 3. 4716 / 125 找出因数分解包含三个5的情况
     * ....
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int result = 0;
        // n太大的时候i也是会溢出的
        for(long i=5; n / i > 0; i*=5){
            result += (n/i);
        }
        return result;
    }

    public static void main(String[] args) {
        Problem172 problem172 = new Problem172();
        problem172.trailingZeroes(200);
    }
}
