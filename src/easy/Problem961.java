package easy;

import java.util.HashMap;

/**
 * 题目描述：长度为2N的数组里有N+1个唯一的数，其中有个数重复了N遍，找出那个数
 */
public class Problem961 {

    public int repeatedNTimes(int[] A) {
        return findNum(A);
    }

    /**
     *
     * @param A
     * @return
     */
    private int findNum(int[] A) {
        // 如果最后一个数重复多次
        if (A.length >= 2 && A[A.length- 1] == A[A.length - 2]) {
            return A[A.length - 1];
        }
        HashMap<Integer, Integer> storeCount = new HashMap<>(); // 用来存储数字出现的次数
        for (int i = 0; i < A.length; i++) {
            if (storeCount.get(A[i]) == null) {
                storeCount.put(A[i], 1);
            } else {
                return A[i];
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Problem961 problem961 = new Problem961();
        int[] A = new int[] {2, 1, 2, 5, 3, 2};
        System.out.println(problem961.repeatedNTimes(A));
    }
}
