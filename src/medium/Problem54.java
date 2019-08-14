package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Spiral Matrix
 * 输入m x n的矩阵
 * 螺旋输出
 */
public class Problem54 {

    /**
     * 0ms 100%
     * 模拟法
     * 思路: 找边界点 定义i的移动范围是[mStart,m-1] i的移动范围是[nStart, n-1]
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int i = 0, j = 0;
        int m = matrix.length;
        int mStart = 0;
        int nStart = 0;
        int n = matrix[0].length;
        int totalCount = m * n;
        int size = 0;
        // 0 1 2 3 分别表示 左 下 右 上四个方向
        int currentDirect = 0;
        while (size < totalCount) {
            switch (currentDirect) {
                // 向右移动
                case 0:
                    while (j < n) {
                        res.add(matrix[i][j]);
                        j++;
                        size += 1;
                    }
                    j--;
                    break;
                // 向下移动
                case 1:
                    while (i < m) {
                        res.add(matrix[i][j]);
                        i++;
                        size += 1;
                    }
                    i--;
                    break;
                // 向左移动
                case 2:
                    while (j >= nStart) {
                        res.add(matrix[i][j]);
                        j--;
                        size += 1;
                    }
                    j++;
                    break;
                // 向上移动
                case 3:
                    while (i >= mStart) {
                        res.add(matrix[i][j]);
                        i--;
                        size += 1;
                    }
                    i++;
                    break;
            }
            // 如果向右移动到了边界点 改方向为向下 i的起始值加一行
            if (j == n - 1 && i == mStart) {
                currentDirect = 1;
                mStart += 1;
                i = mStart;
                continue;
            }
            // 如果向下移动到了边界点 改方向为左
            if (j == n - 1 && i == m - 1) {
                currentDirect = 2;
                n -= 1;
                j = n - 1;
                continue;
            }
            // 向左移动到了边界点 改方向为上
            // 注意这个判断条件和下面判断条件的位置 最后一次向右移动的时候 因为只剩一行 所以mStart == m-1
            // 如果这个条件放到了下面 方向的顺序就错了 当然也可以再加个判断currentDirect == 2的条件 来指定换方向的前提
            if (j == nStart && i == mStart) {
                currentDirect = 0;
                nStart += 1;
                j = nStart;
                continue;
            }
            // 向上移动到了边界点 该方向为右
            if (j == nStart && i == m - 1) {
                currentDirect = 3;
                m -= 1;
                i = m - 1;
                continue;
            }
        }
        return res;
    }

    /**
     * 思路: 想象成每次切除一行(列)后输出外面的一行(列)
     * @param matrix
     * @return
     */
    public List < Integer > spiralOrderByLayer(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem54 problem54 = new Problem54();
//        int[][] data = new int[][] {new int[] {1, 2, 3, 4}, new int[] {5, 6, 7, 8}, new int[] {9, 10, 11, 12}};
        int[][] data = new int[][] {new int[] {1, 2}, new int[] {3, 4}};
        problem54.spiralOrder(data);
    }
}
