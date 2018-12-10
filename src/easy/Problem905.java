package easy;

/**
 * 将数组中偶数部分挪到奇数前
 */
public class Problem905 {

    public int[] sortArrayByParity(int[] A) {
        if (A.length == 1 || A == null) {
            return A;
        }
        // 找到第一个奇数和第一个偶数
        int odd = findNextOdd(A, -1);
        // A中不存在奇数，不需要调换位置
        if (odd == -1) {
            return A;
        }
        // 在第一个奇数后面找第一个偶数
        int even = findNextEven(A, odd);
        int tempEven = even;
        while (true) {
            // 交换奇偶数
            if (odd < tempEven) {
                swap(A, odd, tempEven);
            }
            // 找下一个奇数
            odd = findNextOdd(A, odd);
            // 找下一个偶数
            tempEven = findNextEven(A, odd);
            if (tempEven == even) {
                break;
            }
            even = tempEven;
        }

        return A;
    }

    /**
     * 找下一个奇数
     * @param A
     * @param odd
     * @return
     */
    public int findNextOdd(int[] A, int odd) {
        for (int i = odd + 1; i < A.length; i++) {
            if (A[i] % 2 != 0) {
                return i;
            }
        }
        return odd;
    }

    /**
     * 找下一个偶数
     * @param A
     * @param even
     * @return
     */
    public int findNextEven(int[] A, int even) {
        for (int i = even + 1; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                return i;
            }
        }
        return even;
    }

    /**
     * 交换奇数和偶数
     * @param A
     * @param odd
     * @param even
     */
    public void swap(int[] A, int odd, int even) {
        int temp = A[odd];
        A[odd] = A[even];
        A[even] = temp;
    }

    public static void main(String[] args) {
//        int[] input = new int[] {3,23,10,10,2,2,0,1,7,9,1,2,4};
        int[] input = new int[] {1, 3};
        Problem905 problem905 = new Problem905();
        int[] output = problem905.sortArrayByParity(input);
        for (int i : output) {
            System.out.println(i);
        }

    }
}
