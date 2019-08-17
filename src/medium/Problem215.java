package medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Kth Largest Element in Array
 * 找到一个未排序数组里的第k大的数字
 * k永远是有效值1 <= k <= length
 */
public class Problem215 {

    /**
     * 2ms 89.89%
     * 直接排序后取第nums.length - k个数
     * 如果reverse后取第k-1个数
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestUseAPI(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 55ms 5.38%
     * 每次找出最大值放到数组的最前面 然后把搜索范围减小
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        int start = 0;
        int max;
        int kCopy = k;
        int maxIndex = 0;
        while (k-- > 0) {
            max = Integer.MIN_VALUE;
            for (int i = start; i < nums.length; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                    maxIndex = i;
                }
            }
            nums[maxIndex] = nums[start];
            nums[start] = max;
            start += 1;
        }
        return nums[kCopy-1];
    }

    /**
     * 1ms 99.52%
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestQuickSelect(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    /**
     * https://www.cnblogs.com/grandyang/p/4539757.html
     * https://segmentfault.com/a/1190000003704825
     * @param nums
     * @param start
     * @param end
     * @param k
     * @return
     */
    private int quickSelect(int[] nums, int start, int end, int k) {
        if(start >= end) {
            return nums[start];
        }
        int left = start;
        int right = end;
        int pivot = nums[(left + right) / 2];
        // 交换 pivot左边的所有数都大于右边
        while(left <= right) {
            while(left <= right && nums[left] > pivot) {
                left++;
            }
            while(left <= right && nums[right] < pivot) {
                right--;
            }
            if(left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        // 如果k属于[start,right]的范围里 说明第k大的数在pivot的左边
        if (start < right && k <= right) {
            return quickSelect(nums, start, right, k);
        }
        // 如果k属于[left,end]的范围里 说明第k大的数在pivot的右边
        if (left < end && k >= left) {
            return quickSelect(nums, left, end, k);
        }

        return nums[k];

    }

    public static void main(String[] args) {
        Problem215 problem215 = new Problem215();
        problem215.findKthLargestQuickSelect(new int[] {7, 8, 3, 4, 6, 9, 5}, 4);
    }
}
