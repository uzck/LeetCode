package easy;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 最大三角形区域
 */
public class Problem812 {

    public double calcDistance(int[] point1, int[] point2) {
        return Math.sqrt(Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2));
    }

    public boolean canBuildTriangle(double d1, double d2, double d3) {
        if (d1 + d2 > d3 && d1 + d3 > d2 && d2 + d3 > d1) {
            return true;
        }
        return false;
    }

    /**
     * 海伦公式
     * p = (a + b + c) / 2.
     * Math.sqrt(p * (p-a) * (p-b) * (p-c))
     * @param a
     * @param b
     * @param c
     * @return
     */
    public double calcAreaHeron(double a, double b, double c) {
        double p = (a + b + c ) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     * Shoelace formula 面积公式
     * https://en.wikipedia.org/wiki/Shoelace_formula#Statement
     * @param P
     * @param Q
     * @param R
     * @return
     */
    public double calcArea(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }

    public double largestTriangleArea(int[][] points) {
        double maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double d1 = calcDistance(points[i], points[j]);
                    double d2 = calcDistance(points[i], points[k]);
                    double d3 = calcDistance(points[k], points[j]);
                    if (canBuildTriangle(d1, d2, d3)) {
                        double area = calcAreaHeron(d1, d2, d3);
                        if (area > maxArea) {
                            maxArea = area;
                        }
                    }
                }
            }
        }
        return maxArea;
    }

    public double largestTriangleAreaUseShoelace(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                for (int k = j+1; k < N; ++k)
                    ans = Math.max(ans, calcArea(points[i], points[j], points[k]));
        return ans;
    }

    public static void main(String[] args) {
    }


}
