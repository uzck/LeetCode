package easy;

/**
 * Partition Array Into Three Parts With Equal Sum
 * 如果可以把A分成三个和相等的部分 这三个部分是连续的 返回true 否则false
 */
public class Problem1013 {

    /**
     * 2ms 59% 先递增求出每个和 因为要分成三份 所以一定要存在一个值等于总和的1/3和一个值等于总和的2/3
     * 但是因为数值上会存在重复 加上约束条件first < second 且first和second初始值设置为-1 如果没找到就说明不能分成三份
     * 注意最后的second != A.length - 1 不加这个条件[0,1,-1]这个例子就会失败
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        int[] aSum = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            aSum[i] = sum;
        }

        // 如果sum不能分成三份 说明A不能分成三份
        if (sum % 3 != 0) {
            return false;
        }
        int first = -1;
        int second = -1;
        int sumLength = aSum.length - 1;
        for (int i = 0; i < aSum.length; i++) {
            if (aSum[i] == (aSum[sumLength] / 3) && first == -1) {
                first = i;
                continue;
            }
            if (aSum[i] == (aSum[sumLength] / 3 * 2) && second == -1 && first != -1) {
                second = i;
                continue;
            }
        }
        if (first >= 0 && second > 0 && first < second && second != sumLength) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Problem1013 problem1013 = new Problem1013();
        boolean result = problem1013.canThreePartsEqualSum(new int[] {2, 1, -7, 7, 3, 3});
        System.out.println(result);
    }
}
