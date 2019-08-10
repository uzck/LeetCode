package easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum
 * 输入一个数字数组和一个目标数字 返回index表示数组中存在两个数相加为目标数字
 */
public class Problem1 {

    class IndexVal {
        int val;
        int index;

        public IndexVal(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    /**
     * 用类来绑定val和index 再对val排序
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        IndexVal[] tmp = new IndexVal[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = new IndexVal(nums[i], i);
        }
        Arrays.sort(tmp, new Comparator<IndexVal>() {
            @Override
            public int compare(IndexVal o1, IndexVal o2) {
                return o1.val - o2.val;
            }
        });
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            if (tmp[start].val + tmp[end].val < target) {
                start++;
                continue;
            }
            if (tmp[start].val + tmp[end].val > target) {
                end--;
                continue;
            }
            if (tmp[start].val + tmp[end].val == target) {
                return new int[] {tmp[start].index, tmp[end].index};
            }
        }
        return nums;
    }

    /**
     * 循环两次 HashTable
     * 使用map存储所有值
     * 然后查找map中是否有target-getVal
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumUseTwoPassTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumOnePassTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // 如果map中有剩余的数 直接输出结果
            // 没有的话添加到map中
            // 减少了一次遍历
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
