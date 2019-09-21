package medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Friend Circles
 * 班里有N个学生 其中的部分是朋友
 * 假设朋友具有传递性 A是B的直接朋友 B是C的直接朋友 那么A是C的非直接朋友
 * friend circle的定义是一群是朋友的学生
 * 输入NxN的矩阵M表示学生之间的联系
 * 如果M[i][j]=1说明i和j是直接朋友
 *
 */
public class Problem547 {

    /**
     * 2ms 51.55%
     * 深度优先遍历
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        // 存储结点的访问情况
        boolean[] visited = new boolean[M.length];
        int col = M[0].length;
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < M.length; i++) {
            // 如果碰到一个未访问的结点 说明是一个新的friend circle
            if (!visited[i]) {
                count += 1;
                visited[i] = true;
                // 插入队列
                queue.offer(i);
            }
            while (!queue.isEmpty()) {
                int k = queue.poll();
                // 找出k的所有朋友
                for (int j = 0; j < col; j++) {
                    if (M[k][j] == 1  && !visited[j]) {
                        // 标记为已访问
                        visited[j] = true;
                        // 添加到当前队列
                        queue.offer(j);
                    }
                }
            }
        }
        return count;
    }

    /**
     * 1ms 100%
     * 递归dfs
     * @param M
     * @return
     */
    public int findCircleNumRecur(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, 0);
            }
        }
        return count;
    }

    /**
     * 递归dfs
     * @param M
     * @param visited
     * @param i
     */
    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (visited[j] == 0 && M[i][j] == 1) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    public static void main(String[] args) {
        Problem547 problem547 = new Problem547();
        int[][] M = new int[4][4];
        M[0] = new int[] {1, 0, 0, 1};
        M[1] = new int[] {0, 1, 1, 0};
        M[2] = new int[] {0, 1, 1, 1};
        M[3] = new int[] {1, 0, 1, 1};
        int res = problem547.findCircleNum(M);
        System.out.println(res);
    }
}
