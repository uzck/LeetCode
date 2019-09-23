package medium;

/**
 * Search in Rotated Sorted Array
 * 输入为将有序数组绕着某个点旋转过的数组 如[0, 1, 2, 3, 4, 5, 6, 7] 可能变为[4, 5, 6, 7, 0, 1, 2]
 * 在这样的数组里找是否存在目标数字 存在的话返回下标 否则返回-1
 */
public class Problem33 {

    /**
     * 0ms 100% 时间复杂度: O(N)
     * 还以为这样会超时的 魔幻...
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // 如果第一个数比target大 反过来找
        if (nums[0] > target) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] >= target) {
                if (nums[j] == target) {
                    return j;
                }
                j--;
            }
            return -1;
        } else if (nums[0] == target) {
            return 0;
        } else {
            // 如果第一个数比target小 正向找
            int j = 0;
            while (j <= nums.length - 1 && nums[j] <= target) {
                if (nums[j] == target) {
                    return j;
                }
                j++;
            }
            return -1;
        }
    }

    /**
     * 0ms 100% discuss的解
     * 虽然是局部有序 仍然可以用改动的二分查找
     * @param nums
     * @param target
     * @return
     */
    public int searchBinarySearch(int[] nums, int target) {
        // 按题目描述 一个有序数组在某个结点处旋转 可以分为有序的两段
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            // mid处于前半部分
            if (nums[start] <= nums[mid]){
                // 如果target属于这个部分 在这个部分里做二分查找
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            // mid处于后半部分
            if (nums[mid] <= nums[end]){
                // 如果target属于后半部分
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
