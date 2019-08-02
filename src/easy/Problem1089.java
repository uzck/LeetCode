package easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 对数组中出现的0往后延长一位 0后面的部分右移 超出的部分截断
 * [1, 0, 2, 3, 0, 4, 5, 0] -> [1, 0, 0, 2, 3, 0, 0, 4]
 * 不能返回任何值
 */
public class Problem1089 {

    /**
     * 不是题目要求的直接在原数组上修改 但也算是一种方法
     * 1. 找出0的个数
     * 2. 生成填充完的数组
     * 3. 截取原长度赋值给原数组
     *
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        int zeroCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeroCount += 1;
            }
        }
        int expandLength = arr.length + zeroCount;
        int tmpIndex = 0;
        int[] expandArr = new int[expandLength];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                expandArr[tmpIndex] = 0;
                if (tmpIndex + 1 < expandLength) {
                    tmpIndex += 1;
                    expandArr[tmpIndex++] = 0;
                    continue;
                }
            }
            expandArr[tmpIndex++] = arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = expandArr[i];
        }
    }

    /**
     * 论坛的解法 也是用空间换时间
     * 从末尾读入数据到栈中,如果数据为0则写入两个0
     * 从栈顶读出原长度的数据赋值给原数组
     *
     * @param arr
     */
    public void duplicateZerosUseStack(int[] arr) {
        Stack<Integer> stack1 = new Stack<>();
        for (int index = arr.length - 1; index >= 0; index--) stack1.push(arr[index]);
        List<Integer> list = new LinkedList<>();
        while (!stack1.isEmpty() && list.size() < arr.length) {
            int val = stack1.pop();
            if (val == 0) {
                list.add(0);
                list.add(0);
            } else {
                list.add(val);
            }
        }
        int index = 0;
        while (index < arr.length) {
            arr[index] = list.get(index);
            index++;
        }
    }

    /**
     * 直接右移
     *
     * @param arr
     */
    public void duplicateZerosViolence(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != 0) continue;
            for (int j = arr.length - 1; j > i + 1; --j) arr[j] = arr[j - 1];
            arr[i + 1] = 0;
            ++i;
        }
    }

    public static void main(String[] args) {
        Problem1089 problem1089 = new Problem1089();
        problem1089.duplicateZeros(new int[]{0, 0, 1, 0, 32, 23, 0, 0});
    }
}
