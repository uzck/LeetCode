package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Product of Array Except Self
 * 输入一个数字数组 nums
 * 返回一个数组 output[i]等于nums中除了nums[i]的其他数的乘积
 * 不能用除号 时间复杂度要为O(N)
 */
public class Problem238 {

    /**
     * 用了除法的方法
     * @param nums
     * @return
     */
    public int[] productExceptSelfUseDivide(int[] nums) {
        int product = 1;
        for (int i : nums) {
            product *= i;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = product / nums[i];
        }
        return nums;
    }

    /**
     * 和暴力法差不多
     * @param nums
     * @return
     */
    public int[] productExceptSelfTLE(int[] nums) {
        // 用一个List数组来存储所有乘积的结果
        List<Integer>[] list = new ArrayList[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (List<Integer> tmp : list) {
                if (tmp == null) {
                    break;
                }
                tmp.add(tmp.get(tmp.size()-1) * nums[i]);
            }
            if (list[i] == null) {
                list[i] = new ArrayList<Integer>();
                list[i].add(nums[i]);
            }
        }
        for (int i = 1; i < nums.length - 1; i++) {
            nums[i] = list[i+1].get(list[i+1].size()-1) * list[0].get(i-1);
        }
        nums[0] = list[1].get(list[1].size()-1);
        nums[nums.length-1] = list[0].get(list[0].size() -2);
        return nums;
    }

    /**
     * 空间复杂度O(N) 时间复杂度O(N)
     * 思路和下面空间复杂度O(1)的一致
     * @param nums
     * @return
     */
    public int[] productExceptSelfSpace(int[] nums) {
        int length = nums.length;

        // L存储左边部分乘积
        int[] L = new int[length];
        // R存储右边部分乘积
        int[] R = new int[length];

        // 最后结果
        int[] answer = new int[length];

        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }

        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }

        for (int i = 0; i < length; i++) {
            answer[i] = L[i] * R[i];
        }

        return answer;
    }

    /**
     * Discuss里的解法 1ms 100%
     * 时间复杂度O(N) 空间复杂O(N) 如果返回值不占空间的话就是O(1)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // res[i]存储nums[0]到nums[i-1]的乘积 左边乘积部分
        res[0] = 1; // res[0] = 1 最开始的数左边没有数 按1算
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1; // right存储右边乘积的部分 最后一个数右边没有数 按1算
        for (int i = n - 1; i >= 0; i--) {
            // 左边乘积 * 右边乘积
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Problem238 problem238 = new Problem238();
        int[] data = new int[] {1, 2, 3, 4};
        problem238.productExceptSelf(data);
    }
}
