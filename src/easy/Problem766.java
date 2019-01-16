package easy;

/**
 * Toeplitz矩阵：斜着每一行数是一样的
 * [1, 2, 3, 4]
 * [5, 1, 2, 3]
 * [9, 5, 1, 2]
 * 判断矩阵是不是Toeplitz矩阵 M x N
 */
public class Problem766 {

    // 看的参考答案
    // orz 连这个规律都没看出来
    public boolean isToeplitzMartix(int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (r > 0 && c > 0 && matrix[r - 1][c - 1] != matrix[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

}
