package easy;

/**
 * 返回第n个Tribonacci数
 * Tribonacci序列 Tn定义 T0 = 0 T1 = 1 T2 = 1 Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0
 * 也就是要求Tn+3需要对前面的三个数求和
 */
public class Problem1137 {

    /**
     * 普通迭代
     * 如果需要多次查询的话可以通过建立表来避免每次都需要递归
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int tFirst = 0;
        int tSecond = 1;
        int tThird = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = tFirst + tSecond + tThird;
            tFirst = tSecond;
            tSecond = tThird;
            tThird = result;
        }
        return result;
    }



    public static void main(String[] args) {
        Problem1137 problem1137 = new Problem1137();
        int result = problem1137.tribonacci(3);
        System.out.println(result);
    }
}
