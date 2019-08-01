package easy;

/**
 * Projection Area of 3D Shapes
 * input: 一个二维矩阵 矩阵里的值表示该坐标下的高度
 * output: 三视图面积的总数
 */
public class Problem883 {

    public int projectionArea(int[][] grid) {
        int xy = 0;
        int xz = 0;
        int yz = 0;
        for (int i = 0; i < grid.length; i++) {
            int maxValue = Integer.MIN_VALUE;
            for (int j = 0; j < grid[i].length; j++) {
                if (maxValue < grid[i][j]) {
                    maxValue = grid[i][j];
                }
                if (grid[i][j] > 0) {
                    xy += 1;
                }
            }
            xz += maxValue;
        }
        for (int j = 0; j < grid[0].length; j++) {
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < grid.length; i++) {
                if (maxValue < grid[i][j]) {
                    maxValue = grid[i][j];
                }
            }
            yz += maxValue;
        }
        return xy + xz + yz;
    }

    public int projectionAreaSimple(int[][] grid) {
        int size = grid[0].length;

        int sum=0, xSum = 0, ySum = 0;
        for(int i=0;i<size;i++){
            int xMax =0, yMax=0;
            for(int j=0;j<size;j++){
                if (grid[i][j] >0) sum++;
                if (grid[i][j] > xMax) xMax = grid[i][j];
                if (grid[j][i] > yMax) yMax = grid[j][i];
            }
            xSum += xMax;
            ySum += yMax;
        }

        return xSum + ySum + sum;
    }
}
