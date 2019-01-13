package easy;

/**
 * 求矩阵转置
 */
public class Problem867 {

    /**
     *
     * @param A n x m matrix
     * @return
     */
    public int[][] transpose(int[][] A) {
        if (A == null) {
            return null;
        }

        int n = A.length;
        int m = A[0].length;

        int[][] transpose = new int[m][n];
        int new_i = 0, new_j = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                transpose[new_i++][new_j] = A[i][j];
            }
            new_i = 0;
        }

        return transpose;
    }
}
