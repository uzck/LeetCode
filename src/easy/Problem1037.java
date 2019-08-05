package easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Valid Boomerang
 * boomerang 的定义是三个不相同且不在一条直线上的三个点
 */
public class Problem1037 {

    public boolean pointSameCheck(int[] point1, int[] point2) {
        if (point1[0] == point2[0] && point1[1] == point2[1]) {
            return true;
        }
        return false;
    }

    /**
     * 判断斜率
     * @param points
     * @return
     */
    public boolean isBoomerangLow(int[][] points) {
        if ((points[0][0] == points[1][0] && points[1][0] == points[2][0])
                || (points[0][1] == points[1][1] && points[1][1] == points[2][1])) {
            return false;
        }
        // 处理存在相同的点的情况
        if (pointSameCheck(points[0], points[1]) || pointSameCheck(points[1], points[2]) || pointSameCheck(points[0], points[2])) {
            return false;
        }
        int[] p1 = points[0];
        int[] p2 = points[1];
        int[] p3 = points[2];
        // 如果x轴差值为0 说明这两点是沿着y轴方向的 斜率为无穷
        double k1 = ((p2[0] - p1[0]) == 0) ? Integer.MAX_VALUE : (p2[1] - p1[1]) / 1. / (p2[0] - p1[0]);
        double k2 = ((p3[0] - p2[0]) == 0) ? Integer.MAX_VALUE : (p3[1] - p2[1]) /  1. / (p3[0] - p2[0]);
        if (Math.abs((k1 - k2)) < 1E-9) {
            return false;
        }
        return true;
    }

    public boolean isBoomerang(int[][] points) {
        int x1=points[0][0];
        int y1=points[0][1];
        int x2=points[1][0];
        int y2=points[1][1];
        int x3=points[2][0];
        int y3=points[2][1];
        // 斜率的另一种等价写法 可以不用判断是否是垂直的
        if((y3-y2)*(x2-x1)==(y2-y1)*(x3-x2))
            return false;
        else
            return true;
    }

    public static void main(String[] args) {
        Problem1037 problem1037 = new Problem1037();
        boolean result = problem1037.isBoomerang(new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{1, 2}});
        System.out.println(result);

    }
}
