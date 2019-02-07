package medium;

/**
 * 在保证轮廓相同的情况下 扩充建筑高度
 */
public class Problem807 {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] top = new int[grid[0].length];
        int[] left = new int[grid.length];
        int tmp = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;

        // 遍历得到两个方向的轮廓
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            left[tmp++] = max;
            max = Integer.MIN_VALUE;
        }
        tmp = 0;
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] > max) {
                    max = grid[j][i];
                }
            }
            top[tmp++] = max;
            max = Integer.MIN_VALUE;
        }

        int min = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                min = Math.min(top[j], left[i]);
                sum += min - grid[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] grid = new int[4][4];
        grid[0] = new int[] {3,0,8,4};
        grid[1] = new int[] {2,4,5,7};
        grid[2] = new int[] {9,2,6,3};
        grid[3] = new int[] {0,3,1,0};
        Problem807 problem807 = new Problem807();
        problem807.maxIncreaseKeepingSkyline(grid);
    }
}
