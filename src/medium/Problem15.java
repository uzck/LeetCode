package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 从数组中找出所有三个数相加为0的组合
 * 结果中不能包含重复值
 */
public class Problem15 {

    /**
     * 排序后遍历出所有可能 超时
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumTLE(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        Integer[] data = new Integer[3];
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 因为不包含重复 所以每个开始相同的数字只考虑一次
            if (nums[i] != prev) {
                getThreeNumber(nums, i, 3, 0, res, data);
                prev = nums[i];
            } else {
                continue;
            }
        }
        return res;
    }

    /**
     * @param nums
     * @param start
     * @param remain
     * @param currentSum
     * @param result
     * @param data
     */
    public void getThreeNumber(int[] nums, int start, int remain, int currentSum, List<List<Integer>> result, Integer[] data) {
        currentSum += nums[start];
        remain -= 1;
        data[3 - remain - 1] = nums[start];
        if (currentSum == 0 && remain == 0) {
            result.add(new ArrayList<Integer>(Arrays.asList(data)));
        }
        if (remain > 0) {
            int prev = Integer.MIN_VALUE;
            for (int i = start + 1; i < nums.length; i++) {
                System.out.println(nums[start] + " " + nums[i]);
                if (prev != nums[i]) {
                    getThreeNumber(nums, i, remain, currentSum, result, data);
                    prev = nums[i];
                } else {
                    continue;
                }

            }
        }
    }

    /**
     * 29ms 88%
     * 排序后 对于每一个元素在之后做二分查找
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) {
                // 对于nums[i]来说 搜索范围是[i+1, nums.length-1]里找到和为-nums[i]的两个数
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                // 二分查找
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        // 因为存在多种可能 还需要跳过重复值继续搜索
                        // 跳过较小的重复值 停在了最后一个重复值上 所以还需要lo++
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        // 跳过较大的重复值 还需要hi--
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) lo++; // 如果两个数相加小于sum 说明nums[lo]小了
                    else hi--; // 否则说明nums[hi]大了
                }
            }
        }
        return res;
    }

    /**
     * 题解里最快的方法 11ms 100%
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumFast(int[] nums) {
        int len = nums.length;
        // 长度小于3 肯定不满足
        if (len < 3)
            return new ArrayList<List<Integer>>();

        // 先排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        // 找到数组里最大的数值 给接下来分配数组用
        int max = Math.max(nums[len - 1], Math.abs(nums[0]));

        // max * 2 + 1的数组空间用来存储每个数字出现的个数
        byte[] hash = new byte[(max << 1) + 1];
        for (int v : nums) { //hash and count appearing times of every num
            hash[v + max]++;
        }

        // 找0的位置 有0说明前面是负数 没找到的话返回-1
        int lastNeg = Arrays.binarySearch(nums, 0);
        // 第一个正数的位置
        int firstPos = lastNeg;
        // ????
        if (lastNeg < 0) {    //0 not found
            firstPos = ~lastNeg;
            lastNeg = -lastNeg - 2;//see the Java api
        } else {               //found
            // 移动到最左边0的左边
            while (lastNeg >= 0 && nums[lastNeg] == 0) //skip all 0
                --lastNeg;
            // 移动到最右边0的右边
            while (firstPos < len && nums[firstPos] == 0)
                ++firstPos;
            // 计算0的个数
            int zeroCount = firstPos - lastNeg - 1;
            // 如果0出现大于等于3次 添加一次结果
            if (zeroCount >= 3) {
                res.add(Arrays.asList(0, 0, 0));
            }
            if (zeroCount > 0) { // (0 appears 1 times at least)
                // 零的数量大于1 可以去正数和负数里找一对和为0的数
                for (int i = firstPos; i < len; ++i) {
                    if (i > firstPos && nums[i] == nums[i - 1]) //skip the same elements
                        continue;
                    // hash[-nums[i]+max] 表示的是nums[i]的相反数在hash中存储的个数
                    // hash范围从[-max,max]
                    if (hash[-nums[i] + max] > 0) {
                        res.add(Arrays.asList(0, nums[i], -nums[i]));
                    }
                }
            }
        }

        // 一个正数两个负数的情况
        for (int i = firstPos; i < len; ++i) {
            if (i > firstPos && nums[i] == nums[i - 1]) //skip the same elements
                continue;
            // 看不懂了..告辞
            int half;
            if (nums[i] % 2 != 0)
                half = -((nums[i] >> 1) + 1);
            else {
                half = -(nums[i] >> 1);
                if (hash[half + max] > 1)
                    res.add(Arrays.asList(nums[i], half, half));
            }
            for (int j = lastNeg; j >= 0 && nums[j] > half; --j) {
                if (j < lastNeg && nums[j] == nums[j + 1])
                    continue;
                if (hash[(-nums[i] - nums[j]) + max] > 0)
                    res.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
            }
        }

        // 一个负数两个正数的情况
        for (int i = lastNeg; i >= 0; --i) { //traverse all the negative numbers to find whether there are two positive numbers to make the 3 numbers added up to 0
            if (i < lastNeg && nums[i] == nums[i + 1])//skip the same elements
                continue;
            int half; //we can traverse only half of the negative numbers
            if (nums[i] % 2 != 0)
                half = -(nums[i] / 2 - 1);
            else {
                half = -(nums[i] >> 1);
                if (hash[half + max] > 1)
                    res.add(Arrays.asList(nums[i], half, half));
            }
            for (int j = firstPos; j < len && nums[j] < half; ++j) {
                if (j > firstPos && nums[j] == nums[j - 1])
                    continue;
                if (hash[(-nums[i] - nums[j]) + max] > 0)
                    res.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Problem15 problem15 = new Problem15();
        problem15.threeSum(new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0});
    }
}
