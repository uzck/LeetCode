package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Majority Element
 * 输入为n长度的数组 找出出现次数>(n/2)(下取整)的元素
 */
public class Problem169 {

    /**
     * 用map存储每个元素出现的次数
     * 这题没规定数字的范围 不然用数组存也行
     * @param nums
     * @return
     */
    public int majorityElementUseMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) > (int)(nums.length / 2.)) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 因为一个数占超过一半的元素 所以排序后取中间就是该数
     * @param nums
     * @return
     */
    public int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 因为要求的数比其他数总量要多 所以在结束之前要么会因为count==0使得ret=x
     * 或者count在循环结束前一直大于0
     * @param nums
     * @return
     */
    public int majorityElementVote(int[] nums) {
        int count=0, ret = 0;
        for (int num: nums) {
            if (count==0)
                ret = num;
            if (num!=ret)
                count--;
            else
                count++;
        }
        return ret;
    }
}
