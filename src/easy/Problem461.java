package easy;

/**
 * Hamming Distance
 */
public class Problem461 {

    /**
     * 问题是求数字二进制中数字1的个数
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int xor = x ^ y; // 异或
        int result = 0;
        while (xor > 0) {
            if ((xor & 1) == 1) {
               result += 1;
            }
            xor = xor >>> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int x = 4;
        int y = 1;
        Problem461 problem461 = new Problem461();
        System.out.println(problem461.hammingDistance(x, y));
    }
}
