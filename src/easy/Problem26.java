package easy;

/**
 * Remove Duplicates from Sorted Array
 * 移除有序数组中的重复元素 并把不重复元素移动到数组的最前面 返回截断的长度
 * 不能新建数组来保存结果
 */
public class Problem26 {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int prev = Integer.MIN_VALUE;
        int idleIndex = 1;
        int res = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            // nums[i+1]是一个分界值
            if (nums[i] != nums[i+1]) {
                res += 1;
                nums[idleIndex] = nums[i+1];
                idleIndex += 1;
            } else {
                continue;
            }
        }
        return res;
    }
}
