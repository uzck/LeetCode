package medium;

/**
 * 一个二维矩阵 只含有0和1
 * 每次选择一行或一列 1变为0 0变为1
 * 最后每一行作为一个二进制值 使相加和最大
 */
public class Problem861 {

    public int matrixScore(int[][] A) {

        int sumScore = 0;
        int tempScore = 0;
        int zeroCount = 0, oneCount = 0;
        int current;
        int temp = 1;
        int mask = 0;
        for (int i = 0; i < A[0].length; i++) {
            mask += temp;
            temp *= 2;
        }
        while (true) {
            // 先检查每一列 如果0的数量比1多就进行toggle
            for (int i = 0; i < A[0].length; i++) {
                zeroCount = 0;
                oneCount = 0;
                for (int j = 0; j < A.length; j++) {
                    if (A[j][i] == 0) {
                        zeroCount += 1;
                    } else {
                        oneCount += 1;
                    }
                }
                if (zeroCount > oneCount) {
                    for (int j = 0; j < A.length; j++) {
                        // 0 -> 1 且 1 -> 0
                        A[j][i] = Math.abs(A[j][i] - 1); // 1-1 = 0 abs(0 - 1) = 1
                    }
                }
            }

            // 检查每一行

            for (int i = 0; i < A.length; i++) {
                temp = 1;
                current = 0;
                for (int j = A[0].length - 1; j >= 0; j--) {
                    current += temp * A[i][j];
                    temp *= 2;
                }
                // 求反比现在大
                if ((~current & mask) > current) {
                    tempScore += ~current & mask;
                    for (int j = 0; j < A[0].length; j++) {
                        A[i][j] = Math.abs(A[i][j] - 1);
                    }
                } else {
                    tempScore += current;
                }
            }
            if (tempScore > sumScore) {
                sumScore = tempScore;
                tempScore = 0;
            } else {
                break;
            }
        }

        return sumScore;

    }


    // 1 1 1 1
    // 1 0 0 1
    // 1 1 1 1
    public static void main(String[] args) {
        Problem861 problem861 = new Problem861();
        int[][] nums = new int[3][4];
        nums[0] = new int[] {0, 0, 1, 1};
        nums[1] = new int[] {1, 0, 1, 0};
        nums[2] = new int[] {1, 1, 0, 0};
        System.out.println(problem861.matrixScore(nums));

    }
}
