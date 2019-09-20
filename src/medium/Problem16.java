package medium;

import java.util.Arrays;

public class Problem16 {

    /**
     * @param nums 输入
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minAbs = Integer.MAX_VALUE, val = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length-1;
            while (j < k) {
                int abs = Math.abs(target - nums[i] - nums[j] - nums[k]);
                if (abs < minAbs) {
                    minAbs = abs;
                    val = nums[i] + nums[j] + nums[k];
                }
                if (nums[j] + nums[k] < target - nums[i]) {
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    j++;

                } else if (nums[j] + nums[k] > target - nums[i]) {
                    while (k > j && nums[k] == nums[k-1]) {
                        k--;
                    }
                    k--;

                } else {
                    return target;
                }
            }
        }
        return val;
    }
}
