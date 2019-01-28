package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem888 {

    public int[] fairCandySwapSimple(int[] A, int[] B) {
        int sa = 0, sb = 0;  // sum of A, B respectively
        for (int x: A) sa += x;
        for (int x: B) sb += x;
        int delta = (sb - sa) / 2;
        // If Alice gives x, she expects to receive x + delta

        Set<Integer> setB = new HashSet();
        for (int x: B) setB.add(x);

        for (int x: A)
            if (setB.contains(x + delta))
                return new int[]{x, x + delta};

        throw null;
    }

    public int[] fairCandySwap(int[] A, int[] B) {
        int[] result = new int[2];
        // 保证有序
        Arrays.sort(A);
        Arrays.sort(B);

        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < A.length; i++) {
            sum1 += A[i];
        }
        for (int i = 0; i < B.length; i++) {
            sum2 += B[i];
        }

        int offset = 0;
        if (sum1 > sum2) {
            offset = sum1 - sum2;
            for (int i = A.length - 1; i >= 0; i--) {
                for (int j = 0; j < B.length; j++) {
                    if ((A[i] - B[j]) * 2 < offset) {
                        break;
                    }
                    if ((A[i] - B[j]) * 2 == offset) {
                        result[0] = A[i];
                        result[1] = B[j];
                        return result;
                    }
                }
            }
        } else if (sum1 < sum2) {
            offset = sum2 - sum1;
            for (int i = B.length - 1; i >= 0; i--) {
                for (int j = 0; j < A.length; j++) {
                    if ((B[i] - A[j]) * 2 == offset) {
                        result[0] = A[j];
                        result[1] = B[i];
                        return result;
                    }
                    if ((B[i] - A[j]) * 2 < offset) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] A = new int[] {32, 38, 8, 1, 9};
        int[] B = new int[] {68, 92};
        Problem888 problem888 = new Problem888();
        int[] result = problem888.fairCandySwap(A, B);
        for (int i : result) {
            System.out.println(i);
        }

    }
}
