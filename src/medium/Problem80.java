package medium;

/**
 * Remove Duplicates from sorted Array II
 * 输入一组排序好的数组 要求就地移除多余的元素让剩余的数组里每个数字最多只出现两次
 * 不允许分配额外的数组 O(1)的额外内存
 */
public class Problem80 {

    /**
     * 1ms 75.14%... 看了discuss的解 又是傻逼一样浪费了一小时
     * 思路是用一个变量prev作为插入的下标 按不同的连续数字移动
     * @param nums
     * @return
     */
    public int removeDuplicatesStupid(int[] nums) {
        int prev = -1;
        int index = 0;
        int count = 0;
        int needRemove = 0; // 需要删除的数
        while (index < nums.length) {
            count = 0;
            int firstCopy = index; // 当前连续数字最前面的坐标
            // 找到连续相同的数
            while (index + 1 < nums.length && nums[index] == nums[index+1]) {
                if (prev != -1 && count < 2) {
                    nums[prev++] = nums[index];
                }
                count += 1;
                if (count == 3) {
                    // 如果prev == -1说明之前都没出现过重复出现两次以上的数
                    if (prev == -1) {
                        // 因为要保留两个数
                        prev = firstCopy + 2;
                    }
                }
                index += 1;
            }
            // 因为判断条件是num[index]==num[index+1] 循环结束时停在最后一个连续相等的数上
            // 所以count += 1
            count += 1;
            if (count == 3) {
                if (prev == -1) {
                    prev = firstCopy + 2;
                }
            }
            // 为了移动出现一次或两次的数
            if (count <= 2 && prev != -1) {
                nums[prev++] = nums[firstCopy];
            }
            index += 1;
            needRemove += count - 2 > 0 ? count - 2 : 0;
        }
        return nums.length - needRemove;
    }

    /**
     * 论坛里的解... 真的牛皮
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            // i < 2是因为前两个数肯定不会被覆盖
            // i-2是因为题目要求最多保留两个相同的数
            // 理论上如果要求保留k 只需要把下面的2改为k
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,1,1,1,2,3,3,3,3,5,5,6};
        Problem80 problem80 = new Problem80();
        int result = problem80.removeDuplicates(nums);
        System.out.println(result);
    }
}
