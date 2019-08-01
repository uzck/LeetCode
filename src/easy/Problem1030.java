package easy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Matrix Cells in Distance Order
 * 计算矩阵中所有格子距离某个格子的曼哈顿距离
 */
public class Problem1030 {

    class DistList {
        private List<int[]> distCells;

        public DistList() {
            this.distCells = new LinkedList<>();
        }

        public void addCells(int[] cell) {
            this.distCells.add(cell);
        }


    }

    public int calcDist(int x, int y, int r0, int c0) {
        return Math.abs(x - r0) + Math.abs(y - c0);
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int maxRowDist = (R - 1) - r0 > r0 ? (R - 1) - r0 : r0;
        int maxColDist = (C - 1) - c0 > c0 ? (C - 1) - c0 : c0;
        int maxDist = maxRowDist + maxColDist;
        List<int[]>[] distArray = new List[maxDist + 1];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int dist = calcDist(i, j, r0, c0);
                System.out.println(dist);
                if (distArray[dist] == null) {
                    distArray[dist] = new LinkedList<>();
                    distArray[dist].add(new int[] {i, j});
                } else {
                    distArray[dist].add(new int[] {i, j});
                }
            }
        }
        int[][] result = new int[R * C][];
        int index = 0;
        for (int i = 0; i < distArray.length; i++) {
            if (distArray[i] != null) {
                for (int[] dist : distArray[i]) {
                    result[index++] = dist;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem1030 problem1030 = new Problem1030();
        int[][] result = problem1030.allCellsDistOrder(100, 100, 48, 93);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + ", " + result[i][1]);
        }
    }
}
