package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 输入一个mxn的矩阵 如果有一个值为0 把该行该列全部置0
 * in-place算法
 */
public class Problem73 {

    /**
     * 2ms 43.7% 暴力法
     * 第一次遍历用两个set分别存储有0的列号和行号
     * 第二次遍历的时候如果该行该列有0 置0
     * @param matrix
     */
    public void setZeroesBF(int[][] matrix) {
        Set<Integer> zeroRow = new HashSet<>();
        Set<Integer> zeroCol = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRow.add(i);
                    zeroCol.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (zeroRow.contains(i)) {
                for (int j = 0; j < matrix[0].length; j++) {
                   matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (zeroCol.contains(j)) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 1ms 100%
     * 将每一行(列)的第一个元素作为标记点
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        boolean fr = false,fc = false;
        // fr和fc是处理第0行和第0列只有一个共同结点的特殊变量
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) fr = true;
                    if(j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 如果该行(列)的第一个结点被标记为0 该行该列均为0
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 处理第0行
        if(fr) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        // 处理第0列
        if(fc) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

    }

    public static void main(String[] args) {
        Problem73 problem73 = new Problem73();
        int[][] matrix = new int[][] {new int[]{0, 1, 2, 0}, new int[] {3, 4, 5, 2}, new int[] {1, 3, 1, 5}};
        problem73.setZeroesBF(matrix);
    }
}
