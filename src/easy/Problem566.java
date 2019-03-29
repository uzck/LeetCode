package easy;

public class Problem566 {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rows = nums.length;
        int cols = nums[0].length;
        if (rows * cols != r * c) {
            // 没办法reshape
            return nums;
        }
        int originX = 0, originY = 0;
        int[][] result = new int[r][c];
        for (int i = 0; i < r; i++) {
           for (int j = 0; j < c; j++) {
               result[i][j] = nums[originX][originY];
               if (originY + 1 == cols) {
                   originX += 1;
                   originY = 0;
               } else {
                   originY += 1;
               }
           }
        }
        return result;
    }

    public int[][] matrixReshapeSimple(int[][] nums, int r, int c) {
        int x = nums.length;
        int y = nums[0].length;
        if ( x * y != r * c) return nums;
        int[][] res = new int[r][c];
        for (int i =0; i < r * c; i++)
            res[i / c] [ i % c] = nums[i /y ][i %y];

        return res;
    }
}
