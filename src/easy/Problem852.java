package easy;

/**
 * 题目描述：满足先递增再递减(length >= 3)的数组称为mountain array
 * 要求：找到最大值
 */
public class Problem852 {

    public int peakIndexInMountainArray(int[] A) {
//        for (int i = 0; i < A.length - 1; i++) {
//            if (A[i] > A[i + 1]) {
//                return i;
//            }
//        }
//        return -1;
        return binarySearch(A);
    }

    /**
     * 黄金分割法(优选法)
     * @param A
     * @return
     */
    public int goldenSelectionSearch(int[] A) {
       // TODO 实现
       return -1;
    }

    /**
     * 二分搜索
     * @param A
     * @return
     */
    private int binarySearch(int[] A) {
        int start = 0, end = A.length - 1;
        int middle = (end + start) / 2;
        while (start < end) {
            if (A[middle] > A[middle - 1] && A[middle] > A[middle + 1]) {
                return A[middle];
            }
            if (A[middle] > A[middle - 1] && A[middle] < A[middle + 1]) {
                start = middle;
            }
            if (A[middle] > A[middle + 1] && A[middle] < A[middle - 1]) {
                end = middle;
            }
            middle = (start + end) / 2;
        }
        return A[middle];
    }


    public static void main(String[] args) {
        Problem852 problem852 = new Problem852();
        int[] A = new int[] {0, 2, 3, 4, 7, 10, 9, 5, 1, 0};
        System.out.println(problem852.peakIndexInMountainArray(A));
    }
}
