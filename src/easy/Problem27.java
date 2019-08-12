package easy;

/**
 * Remove Element
 * 输入一个数字数组和一个数字x 移除数组中的所有x 返回新的长度
 * 不能分配新的数组
 */
public class Problem27 {

    /**
     * 0ms 100%
     * 双指针做交换
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int end = nums.length - 1;
        int start = 0;
        int shouldRemove = 0; // 需要移除的位数
        while (start <= end) {
            // 找到最后一个不等于x的数
            while (end >= 0 && nums[end] == val) {
                // 如果有一个val shouldRemove + 1
                shouldRemove += 1;
                end -= 1;
            }
            // 找到第一个x所在的位置
            while (start < nums.length && nums[start] != val) {
                start += 1;
            }
            // 如果start < end 对两个数字进行交换 把val交换到数组的末尾
            if (start < end && start < nums.length && end >= 0) {
                nums[start] = nums[end];
                nums[end] = val;
                shouldRemove += 1;
                start++;
                end--;
            }
        }
        return nums.length - shouldRemove;
    }

    /**
     * 和Problem20的思路差不多 用一个指针表示下一个可以赋值的位置
     * 然后把不等于val的数赋值到该位置
     * @param nums
     * @param val
     * @return
     */
    public int removeElementSimple(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Problem27 problem27 = new Problem27();
        problem27.removeElement(new int[] {0, 1, 2, 2, 3, 0, 4, 2}, 2);
    }
}
