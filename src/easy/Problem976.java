package easy;

import java.util.*;

/**
 * 从输入里选择三个值生成三角形 输出可能的最大面积
 * 如果不能生成三角形 输出0
 */
public class Problem976 {


    public boolean canBuildTriangle(int a, int b, int c) {
        if ((a + b > c) && (a + c > b) && (b + c > a)) {
            return true;
        }
        return false;
    }

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i - 2 >= 0; i--) {
            if (canBuildTriangle(A[i], A[i-1], A[i-2])) {
                return A[i] + A[i-1] + A[i-2];
            }
        }
        return 0;
    }

    /**
     * 暴力法超时
     * @param A
     * @return
     */
    public int largestPerimeterTLE(int[] A) {
        int maxPerimeter = 0;
        int tmpPerimeiter = 0;
        Arrays.sort(A);
        Map<Integer, Integer> map = new HashMap<>();
        int tmp = A[0];
        int splitIndex = 0;
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != tmp) {
                map.put(splitIndex, count);
                count = 0;
                splitIndex = i;
                tmp = A[i];
            }
            count += 1;
            if (i == A.length - 1) {
                map.put(splitIndex, count);
                break;
            }
        }
        int[] splitIndexArray = new int[map.size()];
        int[] splitCountArray = new int[map.size()];
        int tmpIndex = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            splitIndexArray[tmpIndex] = entry.getKey();
            splitCountArray[tmpIndex] = entry.getValue();
            tmpIndex += 1;
        }
        for (int i = 0; i < splitIndexArray.length; i++) {
            splitCountArray[i] -= 1;
            for (int j = 0; j < splitIndexArray.length; j++) {
                if (splitCountArray[j] <= 0) {
                    continue;
                }
                splitCountArray[j] -= 1;
                for (int k = 0; k < splitIndexArray.length; k++) {
                    if (splitCountArray[k] > 0) {
                        if (canBuildTriangle(A[splitIndexArray[i]], A[splitIndexArray[j]], A[splitIndexArray[k]])) {
                            tmpPerimeiter =  A[splitIndexArray[i]] +  A[splitIndexArray[j]] +  A[splitIndexArray[k]];
                            maxPerimeter = tmpPerimeiter > maxPerimeter ? tmpPerimeiter : maxPerimeter;
                        }
                    }
                }
                splitCountArray[j] += 1;
            }
            splitCountArray[i] += 1;
        }
        return Math.max(maxPerimeter, 0);
    }

    public static void main(String[] args) {
        Problem976 problem976 = new Problem976();
        int perimeter = problem976.largestPerimeter(new int[]{1, 1, 2});
        System.out.println(perimeter);
    }
}
