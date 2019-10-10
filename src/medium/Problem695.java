package medium;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Max Area of Island
 * 找出二维矩阵中相邻的1最多的个数
 */
public class Problem695 {

    /**
     * 广度优先搜索 3ms 52.99%
     * 在Number of Islands的基础上求出每个岛的面积
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        int max = 0;
        Queue<Integer> queue = new LinkedList<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    int count = 0;
                    queue.offer(i * n + j);
                    while (!queue.isEmpty()) {
                        int tmp = queue.poll();
                        count += 1;
                        int x = tmp / n, y = tmp % n;
                        if (isValid(x + 1, y, m, n, grid)) {
                            queue.offer((x + 1) * n + y);
                        }
                        if (isValid(x - 1 , y, m, n, grid)) {
                            queue.offer((x - 1) * n + y);
                        }
                        if (isValid(x, y + 1, m, n, grid)) {
                            queue.offer(x * n + y + 1);
                        }
                        if (isValid(x, y - 1, m, n, grid)) {
                            queue.offer(x * n + y - 1);
                        }
                    }
                    if (count > max) {
                        max = count;
                    }
                }
            }
        }

        return max;
    }

    /**
     * 2ms 100% 深度优先搜索
     * @param grid
     * @return
     */
    public int maxAreaOfIslandDFS(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int currentArea = dfs(i, j, m, n, grid, 0);
                    if (currentArea > max) {
                        max = currentArea;
                    }
                }
            }
        }

        return max;
    }

    public int dfs(int x, int y, int m, int n, int[][] grid, int preVal) {
        if (!isValid(x, y, m, n, grid)) {
            return preVal;
        }
        grid[x][y] = 0;
        int currentVal = preVal + 1;
        currentVal += dfs(x + 1, y, m, n, grid, 0);
        currentVal += dfs(x - 1, y, m, n, grid, 0);
        currentVal += dfs(x, y - 1, m, n, grid, 0);
        currentVal += dfs(x, y + 1, m, n, grid, 0);
        return currentVal;
    }

    public boolean isValid(int x, int y, int m, int n, int[][] grid) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
            return false;
        }
        grid[x][y] = 0;
        return true;
    }

    public static void main(String[] args) {
        Problem695 problem695 = new Problem695();
        int[][] grid = new int[4][5];
        grid[0] = new int[] {1, 1, 0, 0, 0};
        grid[1] = new int[] {1, 1, 0, 0, 0};
        grid[2] = new int[] {0, 0, 0, 1, 1};
        grid[3] = new int[] {0, 0, 0, 1, 1};
        int ans = problem695.maxAreaOfIslandDFS(grid);
        System.out.println(ans);
    }
}
