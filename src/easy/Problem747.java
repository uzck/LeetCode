package easy;

/**
 * Largest Number At Least Twice of Others
 * 输入:一个int[]类型 里面有一个最大值 判断最大值是否至少是其他数的两倍以上 如果是 返回该数的下标 否则 返回-1
 */
public class Problem747 {

    /**
     * 0ms 100%
     * 普通迭代
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        for (int i : nums) {
            // 这里要考虑i > 0 避免除零
            if (i != max && i > 0 && max / i < 2) {
                return -1;
            }
        }
        return maxIndex;
    }

    /**
     * 比上面少了一个循环 找出第二大的数
     * @param nums
     * @return
     */
    public int dominantIndexSecond(int[] nums) {
        int largest;
        int second;
        if (nums.length == 1) return 0;
        if(nums[0] > nums[1]) {
            largest = 0;
            second= 1;
        }
        else {
            largest = 1;
            second = 0;
        }
        if (nums.length == 1) return 0;
        for(int i = 2; i < nums.length; i++) {
            if (nums[i] > nums[largest]) {
                second = largest;
                largest = i;
            } else if (nums[i] > nums[second]) {
                second = i;
            }
        }
        return nums[largest] >= nums[second] * 2 ? largest : -1;
    }
}
