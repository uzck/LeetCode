package easy;

import org.omg.CORBA.INTERNAL;

/**
 * Complement of Base 10 Integer
 * 对输入的数字去掉先导0的部分取反
 */
public class Problem1009 {

    public int bitwiseComplement(int N) {
        int mask = getMask(N);
        return mask & (~N);
    }

    /**
     * 找出这个数的位长 ~N & mask即可 也可以mask ^ N 因为1 ^ 1 = 0 1 ^ 0 = 1也就起到了取反的作用
     * @param N
     * @return
     */
    public int getMask(int N) {
        int mask = 0;
        while (N > 0) {
            N >>= 1;
            mask = (mask << 1) + 1;
        }
        if (mask == 0) {
            return 1;
        }
        return mask;
    }

    /**
     * 输入和输出相加应该为11...111的二进制数用X表示 且X要大于我们的输入
     * 找到这个X再减去原来的数字就是需要的结果
     * @param N
     * @return
     */
    public int bitwiseComplementV2(int N) {
        int X = 1;
        while (N > X) X = X * 2 + 1;
        return N ^ X;
    }

    public static void main(String[] args) {
        Problem1009 problem1009 = new Problem1009();
        int res = problem1009.bitwiseComplement(10);
        System.out.println(res);
    }
}
