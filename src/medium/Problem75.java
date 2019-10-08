package medium;

/**
 * Sort Colors
 * 输入n个不同颜色(红 白 蓝)的物体,就地(不使用新数组)排序让颜色相同的相邻,颜色顺序为红,白,蓝
 * 用0,1,2分别代表红 白 蓝
 * 常规方法遍历两边
 * 要求一次遍历 使用常数空间
 *
 */
public class Problem75 {

    /**
     * 常规两次遍历方法
     * @param nums
     */
    public void sortColorsTwoPass(int[] nums) {
        int[] color = new int[3];
        for (int i : nums) {
            color[i] += 1;
        }
        int index = 0;
        for (int i = 0; i < color.length; i++) {
            while (color[i] != 0) {
                nums[index++] = i;
                color[i] -= 1;
            }
        }
    }

    /**
     * one pass solution
     * 思路 把所有0放在最左边 2放在最右边
     * @param nums
     */
    public void sortColorOnePass(int[] nums) {
        // left和right用来标记左右下标
        int left = 0, right = nums.length-1, count = 0;
        while (count < nums.length) {
            // 如果为0且当前下标大于左边
            if (nums[count] == 0 && count > left) {
                // 把当前位置和left的值先进行交换(把1换到中间来)
                nums[count] = nums[left];
                nums[left] = 0;
                left++;
            }
            else if (nums[count] == 2 && count < right) {
                // 把1换到中间来
                nums[count] = nums[right];
                nums[right] = 2;
                right--;
            }
            else {
                // 遇到1直接跳过
                count ++;
            }
        }
    }
}
