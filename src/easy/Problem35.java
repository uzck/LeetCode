package easy;

/**
 * Search Insert Position
 * 输入一个排序好的无重复数字数组和一个目标数字 如果找到这个数字返回下表 否则返回它应该插入的位置
 *
 */
public class Problem35 {

    /**
     * 二分查找 注意下lo <= hi
     * 如果lo = middle或者hi = middle的话算法不会退出 需要加上middle==lo的条件判断停止
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= nums[0]) {
            return 0;
        }
        int lo = 0;
        int hi = nums.length - 1;
        // 头尾可以单独判断下
        if (target == nums[nums.length - 1]) {
            return nums.length - 1;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        while (lo <= hi) {
            int middle = (lo + hi) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] < target) {
                lo = middle + 1;
            }
            if (nums[middle] > target) {
                hi = middle - 1;
            }
        }
        return lo;
    }
}
