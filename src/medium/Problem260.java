package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一串数字 如果两个数仅出现一次并且其他数都出现两次 找出这两个只出现一次的数
 * 顺序不要紧 时间复杂度要求线性时间且只是用常数空间
 */
public class Problem260 {

    /**
     * 1ms 99.62%
     * 位操作
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int xor = nums[0];
        // 因为a ^ a = 0 所以遍历整个数组后剩下的值是两个只出现一次的数A^B
        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }

        // 两个不同的数异或之后的二进制表示中 肯定至少存在一个1 xor &= -xor来找到最后的一个1
        // 3(11) ^ 5(101) = 110
        // 负数的二进制表示里是先对正数的二进制求反之后再+1 也就是用补码表示
        // 110 & (10) = 010
        xor &= -xor;

        for (int i : nums) {
            // i && xor == 0 说明i的后面位和xor一样
            // 重复的数字因为成对出现 在做异或操作之后还是会变为0
            // 任何数 ^ 0 = 本身
            if ((i & xor) == 0) {
                res[0] ^= i;
            } else {
                res[1] ^= i;
            }
        }

        return res;
    }

    /**
     * 6ms 19.17% 遍历存到map里
     * @param nums
     * @return
     */
    public int[] singleNumberMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        int[] res = new int[2];
        int index = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                res[index++] = key;
            }
        }
        return res;
    }
}
