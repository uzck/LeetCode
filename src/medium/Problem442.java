package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find All Duplicate in an Array
 */
public class Problem442 {

    /**
     * 先排序后利用n^n=0
     * 也可以用set来存储每个数出现次数
     * 这两种方法都是不满足题目的O(n)和不使用额外空间的要求
     * @param nums
     * @return
     */
    public List<Integer> findDuplicatesUsingSort(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if ((nums[i] ^ nums[i + 1]) == 0) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    /**
     * 将访问过的元素对应位置设置为复数 如果有重复 该位置的值就是负的
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int absResult = Math.abs(nums[i]);
            if (nums[absResult - 1] < 0) {
                result.add(absResult);
            }
            nums[absResult-1] = -nums[absResult - 1];
        }
        return result;
    }

    public static void main(String[] args) {
        Problem442 problem442 = new Problem442();
        List<Integer> result = problem442.findDuplicates(new int[]{4,3,2,7,8,2,3,1});
        if (result != null) {
            for (Integer i : result) {
                System.out.println(i);
            }
        }
    }
}
