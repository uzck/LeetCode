package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 4Sum
 * 输入一个数字数组和目标值 找到所有满足a+b+c+d == target的不重复解
 * kSum的问题的时间复杂度是O(n^(k-1)) 最后细化为2Sum问题来解决
 */
public class Problem18 {

    /**
     * 23ms 57.8%
     * 在3Sum的基础上来做 复杂度O(n^3)
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSumSlow(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length <= 3) {
            return res;
        }
        Arrays.sort(nums);
        int i, j;
        for (i = 0; i < nums.length - 3;) {
            for (j = i + 1; j < nums.length - 2; ) {
                int lo = j + 1, hi = nums.length - 1;
                while (lo < hi) {
                    if (nums[i] + nums[j] + nums[lo] + nums[hi] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        // 跳过重复元素
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        // 跳过重复元素
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (nums[hi] + nums[lo] < target - nums[i] - nums[j]) {
                        // 总和偏小
                        lo++;
                    } else if (nums[hi] + nums[lo] > target - nums[i] - nums[j]) {
                        // 总和偏大
                        hi--;
                    }
                }
                // 跳过重复元素
                while (j < nums.length - 2 && nums[j] == nums[j + 1]) {
                    j++;
                }
                j += 1;
            }
            // 跳过重复元素
            while (i < nums.length - 3 && nums[i] == nums[i+1]) {
                i++;
            }
            i += 1;
        }
        return res;
    }

    /**
     * 3ms 100%
     * 思路和slow的方法基本是一致的
     * 多加了很多判断条件来减少循环次数
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSumFast(int[] nums, int target) {
        if(nums.length < 4) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for(int first = 0; first < nums.length-3;first++) {
            // 最小的四个数和比target大 后面的和肯定更大
            if(nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) break;
            // 找到答案里最小的可能的起始值
            if(nums[first] + nums[length-1] + nums[length - 2] + nums[length - 3] < target) continue;
            // 跳过重复值
            if(first > 0 && nums[first] == nums[first-1]) continue;
            for(int second = first+1; second < nums.length - 2; second++) {
                int third = second+1;
                int fourth = nums.length-1;

                // 目前的四个数之和比target大 后面的值肯定都不行
                if(nums[first] + nums[second] + nums[second+1] + nums[second+2] > target) break;
                // 求出可行的最小的second
                if(nums[first] + nums[second] + nums[length-1] + nums[length-2] < target) continue;
                // 跳过重复值
                if(second > first + 1 && nums[second] == nums[second-1]) continue;

                while(third < fourth) {
                    int sum = nums[first] + nums[second] + nums[third] + nums[fourth];

                    if(sum == target) {
                        result.add(Arrays.asList(nums[first],nums[second],nums[third],nums[fourth]));

                        while(third < fourth && nums[third] == nums[third+1]) third++;
                        while(third < fourth && nums[fourth] == nums[fourth-1]) fourth--;
                        third++;
                        fourth--;
                    } else if(sum < target) {
                        third++;
                    } else {
                        fourth--;
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> _fourSum(int[] nums, int target) {
        if(nums.length < 4) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for(int first = 0; first < nums.length-3; first++) {
            for(int second = first+1; second < nums.length-2; second++) {

                int third = second+1;
                int fourth = nums.length-1;

                while(third < fourth) {

                    int sum = nums[first]+nums[second]+nums[third]+nums[fourth];

                    if(sum == target) {
                        List<Integer> tempSum = new ArrayList(Arrays.asList(nums[first],nums[second],nums[third],nums[fourth]));
                        if(!result.contains(tempSum)) {
                            result.add(tempSum);
                        }
                        third++;
                        fourth--;

                    }
                    else if(sum < target) {
                        third++;
                    } else {
                        fourth--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem18 problem18 = new Problem18();
        problem18.fourSumSlow(new int[]{-1, -5, -5, -3, 2, 5, 0, 4}, -7);
    }
}
