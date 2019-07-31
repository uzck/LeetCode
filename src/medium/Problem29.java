package medium;

/**
 * 给一个除数一个被除数 不用乘、除、模运算求出商 数的范围[-2^31, 2^31-1]
 * Math.abs(Integer.MIN_VALUE)返回的结果会溢出
 */
public class Problem29 {

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        // dividend为-2^31次时 特殊处理
        // -2147483648 / -1 是唯一可能的越界情况
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 除数与被除数是否同号
        boolean isNegative = (dividend < 0 && divisor > 0) ||
                (dividend > 0 && divisor < 0);

        // 直接用Math.abs转换会出现问题
        int a = dividend < 0 ? dividend : - dividend;
        int b = divisor < 0 ? divisor : - divisor;


        int result = 0;
        while (a <= b) {
            // shift表示可以倍乘的位数
            int shift = 0;
            // b << shift 不能越界到正数 1 << 31 也是越界了
            while (a <= (b << shift) && (b << shift) < 0 && shift < 31) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative? -result: result;

    }

    /**
     * 用long解决越界问题
     * @param dividend
     * @param divisor
     * @return
     */
    public int divideUseLong(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isNegative = (dividend < 0 && divisor > 0) ||
                (dividend > 0 && divisor < 0);

        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);

        int result = 0;
        while (a >= b) {
            int shift = 0;
            while (a >= (b << shift)) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative? -result: result;
    }

    public static void main(String[] args) {
        Problem29 problem29 = new Problem29();
        int result = problem29.divide(100, 15);
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Integer.toBinaryString(-5 << 1));
        System.out.println(-5 << 1);
        System.out.println(result);
    }
}
