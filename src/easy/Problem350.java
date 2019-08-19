package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Intersection of Two Arrays II
 * 找出连个数组中重复的数字 数组本身无序
 * Follow up:
 * 1. 如果数组排序的话 双指针比较
 * 2. 如果num1的长度远小于num2 排序法
 * 3. 如果num2额结果在磁盘里排序且限制内存,不嗯给你同时加载所有元素 hashmap存储num1的值和num2比较
 * 这三种条件下哪种算法更合适
 */
public class Problem350 {

    /**
     * 2ms 91.32%
     * hashmap存储其中一个数组的所有数字出现的结果
     * 再遍历第二个数组 如果map中存储着这个键并且值大于0 就是一个重复的值
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 && nums2.length == 0) {
            return new int[] {};
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[Math.min(len1, len2)];
        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int index = 0;
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                map.put(i, map.get(i) - 1);
                res[index++] = i;
            }
        }
        return Arrays.copyOf(res, index);
    }

    /**
     * 用排序法解决
     * 先对两个数组排序 然后迭代到最短的数组结束为止
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectSort(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 && nums2.length == 0) {
            return new int[] {};
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, index = 0;
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                res[index++] = nums1[i];
                i++;
                j++;
                continue;
            }

            if (nums1[i] < nums2[j]) {
                i++;
                continue;
            }
            if (nums1[i] > nums2[j]) {
                j++;
                continue;
            }
        }
        return Arrays.copyOf(res, index);
    }

    public static void main(String[] args) {
        Problem350 problem350 = new Problem350();
        problem350.intersectSort(new int[]{1,2,2,1}, new int[] {2,2});
    }
}
