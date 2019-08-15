package medium;

/**
 * Jump Game
 * 输入一串非负数字 每个数字表示可以跳的最远的距离 判断是否能跳到终点, 起始时在第一个位置
 */
public class Problem55 {

    /**
     * 1ms 99.28%
     *只要中间不会停在0上 就一定能到达终点 因为每次最少也可以跳一步
     *所以只要找出0之前的数能否跳过0即可
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        // 最开始就是0
        if (nums == null || nums.length == 0 || nums[0] == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        // nums.length >= 2
        int current = 0;
        for (current = 0; current < nums.length - 1; current++) {
            if (nums[current] == 0) {
                // 如果当前数为0
                boolean canJumpZero = false;
                // 如果最后一位是0 返回true
                if (current == nums.length - 1) {
                    return true;
                } else {
                    // 往前找是否有i + nums[i] > current的点
                    // 存在的话说明可以跳过这个0
                    for (int i = current - 1; i >= 0; i--) {
                        if (i + nums[i] > current) {
                            canJumpZero = true;
                            break;
                        }
                    }
                    if (canJumpZero) {
                        continue;
                    } else {
                        // 如果跳不过这个0 返回false
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 贪心
     * 反过来把终点尽量向前移动 如果最后可以把终点移动到起点 返回true
     * @param nums
     * @return
     */
    public boolean canJumpGreedy(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    /**
     * 这个贪心理解起来更直观
     * 把i之前能到达的最远距离和i能到达的最远距离比较 就能求出当前能达到的最远距离
     * 如果max >= len-1 说明能到达终点
     * @param nums
     * @return
     */
    public boolean canJumpGreedyV2(int[] nums) {
            int len = nums.length;
            int max = 0;
            for(int i=0; i<=max; i++){
                max = Math.max(max, i+nums[i]);
                if(max >= len-1)  return true;
            }
            return false;
    }

    public static void main(String[] args) {
        Problem55 problem55 = new Problem55();
        problem55.canJumpGreedy(new int[]{2,3,1,1,4});
    }
}
