package easy;

/**
 * Sort Array By Parity II
 * 输入是一个正数的数组，一半是奇数，一半是偶数
 * 要求输出的数组里 奇数下标放奇数 偶数下标放偶数
 */
public class Problem922 {

    public int[] sortArrayByParityII(int[] A) {

        int temp;
        int j;
        for (int i = 0; i < A.length - 1; i++) {
            if (i % 2 != A[i] % 2) {
                // 不满足输出条件
                j = i + 1;
                while (j < A.length) {
                    if ((A[j] % 2 != j % 2) && (A[j] % 2 != A[i] % 2)) {
                        break;
                    }
                    j++;
                }
                temp = A[j];
                A[j] = A[i];
                A[i] = temp;
            }

        }
        return A;
    }

    public int[] sortArrayByParityIITwoPoint(int[] A) {
        int odd = 1, even = 0;
        int temp;
        while (odd < A.length && even < A.length) {
            while (odd < A.length && A[odd] % 2 != 0) {
                odd += 2;
            }
            while (even < A.length && A[even] % 2 == 0) {
                even += 2;
            }
            if (odd < A.length && even < A.length) {
                temp = A[odd];
                A[odd] = A[even];
                A[even] = temp;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[] data = new int[] {4, 2, 5, 7};
        Problem922 problem922 = new Problem922();
        int[] result = problem922.sortArrayByParityII(data);

        for (int i : result) {
            System.out.println(i);
        }
    }
}
