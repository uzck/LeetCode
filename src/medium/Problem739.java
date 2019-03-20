package medium;

import java.util.ArrayList;
import java.util.Stack;

public class Problem739 {

    /**
     * 输入为一个整数数组
     * 输出结果为当前数到下一个比这数大的值的下标数
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {

        int[] result = new int[T.length];
        int[] tempData = new int[71];
        int[] resultCopy = new int[71];
        for (int i = 0; i < tempData.length; i++) {
            tempData[i] = -1;
            resultCopy[i] = -1;
        }

        for (int i = 0; i < T.length; i++) {
            if (tempData[T[i] - 30] <  i)  {
                tempData[T[i] - 30] = i;
            }
            if (resultCopy[T[i] - 30] == 0) {
                result[i] = 0;
                continue;
            }
            if (resultCopy[T[i]- 30] > i) {
//                int endIndex = resultCopy[T[i] - 30];
                result[i] = resultCopy[T[i] - 30] - i;
                continue;
            }
            int getBigNumIndex = getBiggerNum(tempData, T[i], i, tempData.length - 1);
            if (getBigNumIndex != -1) {
                result[i] = getBigNumIndex - i;
                resultCopy[T[i] - 30] = getBigNumIndex;
            } else {
                boolean findBig = false;
                for (int j = i + 1; j < T.length; j++) {
                    tempData[T[j] - 30] = j;
                    if (T[j] > T[i]) {
                        result[i] = j - i;
                        resultCopy[T[i] - 30] = j;
                        findBig = true;
                        break;
                    }
                }
                if (!findBig) {
                    result[i] = 0;
                    resultCopy[T[i] - 30] = 0;
                }
            }
        }

        return result;
    }

    /**
     *
     * @param source
     * @param target
     * @param start
     * @param end
     * @return
     */
    public int getBiggerNum(int[] source, int target, int start, int end) {
        int minMax = -1;
        for (int i = target - 30; i <= end; i++) {
            if (source[i] > start) {
                if (minMax > source[i]) {
                    minMax = source[i];
                }
            }
        }
        return minMax;
    }

    public int[] dailyTemperaturesUsingStackArray(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top = -1;
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                ret[idx] = i - idx;
            }
            stack[++top] = i;
        }
        return ret;
    }

    /**
     * 用栈来解决
     * 没找到大于栈顶的数时一直存在栈里
     * @param temperatures
     * @return
     */
    public int[] dailyTemperaturesUsingStack(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        Problem739 problem739 = new Problem739();
        int[] result = problem739.dailyTemperaturesUsingStack(new int[] {77,41,41,41,41,77,41,77,77,77});
        for (int i : result) {
            System.out.println(i);
        }
    }
}
