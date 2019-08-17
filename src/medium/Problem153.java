package medium;

/**
 * Find Minimum in Rotated Sorted Array
 * 输入是一个升序数组绕着某个点旋转后的数组(有可能绕着起点旋转 也就是不旋转)
 * 找出数组里最小的数字 数组里没有重复数字
 */
public class Problem153 {

    /**
     * 0ms 100%
     * - -... 没看出来这题能用二分
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int end = nums.length - 1;
        while (end > 0) {
            if (nums[end] < nums[end-1]) {
                return nums[end];
            } else {
                end--;
            }
        }
        if (end == 0) {
            return nums[0];
        }
        return -1;
    }

    /**
     * 二分查找
     * 假设旋转后的输入为[4,5,6,7,1,2,3]
     * start = 0 end = nums.lenght-1 middle = (start + end) / 2 = 3
     * nums[start] = 4 nums[end] = 3 nums[middle]=7 > nums[start] 可以知道start到middle这一段是旋转过的
     * 所以接着start = middle + 1
     * 反过来end = middle - 1
     * 如果nums[middle] < nums[middle-1]的话 就是要找的值
     * @param nums
     * @return
     */
    public int findMinBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[start] <= nums[mid] && nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return nums[start];
    }

    public static void main(String[] args) {
        Problem153 problem153 = new Problem153();
        problem153.findMin(new int[] {1, 2});
    }
}
