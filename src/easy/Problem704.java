package easy;

public class Problem704 {

    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }
        int start = 0, end = nums.length - 1;
        int current = (start + end) / 2;
        while (start <= end) {
            if (nums[current] < target) {
                start = current + 1;
                current = (start + end) / 2;
            } else if (nums[current] > target) {
                end = current - 1;
                current = (start + end) / 2;
            } else {
                return current;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2,5};
        System.out.println(new Problem704().search(nums, 5));
    }
}
