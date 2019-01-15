package easy;

public class Problem908 {

    public int smallestRangleI(int[] A, int k) {
//        Arrays.sort(A); // 没必要排序 遍历找最大值和最小值就行了

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : A) {
            min = i < min ? i : min;
            max = i > max ? i : max;
        }
        if (max - min >= 2 * k) {
            return max - k - (min + k);
        } else {
            return 0;
        }
    }
}
