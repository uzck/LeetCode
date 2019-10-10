package medium;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Number of Islands
 * 找出二维矩阵中联通的'1'的区域个数
 */
public class Problem200 {

    /**
     * 1ms 100%
     * 深度优先搜索
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }
    public void dfs (char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs (grid, i + 1, j);
            dfs (grid, i - 1, j);
            dfs (grid, i, j + 1);
            dfs (grid, i, j - 1);
        }
    }

    /**
     * 2ms 45.25%
     * 深度优先遍历
     * @param grid
     * @return
     */
    public int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] visited = new int[m][n];
        int i = 0, j, res = 0;
        for(; i < m; i++) {
            j = 0;
            for ( ; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j] == 1) {
                    continue;
                }
                check(i, j, m, n, visited, grid);
                res += 1;
            }
        }
        return res;
    }

    public void check(int i, int j, int m, int n, int[][] visited, char[][] grid) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == 1 || grid[i][j] == '0') {
            return;
        }
        visited[i][j] = 1;
        check(i-1, j, m, n, visited, grid);
        check(i+1, j, m, n, visited, grid);
        check(i, j-1, m, n, visited, grid);
        check(i, j+1, m, n, visited, grid);
    }

    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] visited = new int[m][n];
        int res = 0;
        Queue<Integer[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j] == 1) {
                    continue;
                }
                queue.offer(new Integer[] {i, j});
                while (!queue.isEmpty()) {
                    Integer[] tmp = queue.poll();
                    int x = tmp[0], y = tmp[1];
                    visited[x][y] = 1;
                    if (isValid(x-1, y, m, n, visited, grid)) {
                        queue.offer(new Integer[] {x-1, y});
                    }
                    if (isValid(x+1, y, m, n, visited, grid)) {
                        queue.offer(new Integer[] {x+1, y});
                    }
                    if (isValid(x, y-1, m, n, visited, grid)) {
                        queue.offer(new Integer[] {x, y-1});
                    }
                    if (isValid(x, y+1, m, n, visited, grid)) {
                        queue.offer(new Integer[] {x, y+1});
                    }
                }
                res += 1;
            }
        }
        return res;
    }

    public boolean isValid(int x, int y, int m, int n, int[][] visited, char[][] grid) {
        if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y] == 1 || grid[x][y] == '0') {
            return false;
        }
        visited[x][y] = 1;
        return true;
    }

    public static void main(String[] args) {
        char[][] grid = new char[4][5];
        grid[0] = new char[] {'1', '1', '0', '0', '0'};
        grid[1] = new char[] {'1', '1', '0', '0', '0'};
        grid[2] = new char[] {'0', '0', '1', '0', '0'};
        grid[3] = new char[] {'0', '0', '0', '1', '1'};
//        char[][] grid = new char[1][2];
//        grid[0] = new char[]{'1', '1'};
        Problem200 problem200 = new Problem200();
        int ans = problem200.numIslandsBFS(grid);
        System.out.println(ans);
    }
}
