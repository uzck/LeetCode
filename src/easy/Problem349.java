package easy;

import java.util.*;

/**
 * 找到两个int[]的交集
 */
public class Problem349 {

    /**
     * 3ms 39.91%
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionSlow(int[] nums1, int[] nums2) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        ArrayList<Integer> intersectionArray = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], false);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                map.put(nums2[i], true);
            }
        }
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                intersectionArray.add(entry.getKey());
            }
        }
        int[] res = new int[intersectionArray.size()];
        int index = 0;
        for (int i : intersectionArray) {
            res[index++] = i;
        }
        return res;
    }

    /**
     * 先对数据排序 然后双指针遍历 O(nlogn) + O(Math.max(N, M))
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        Set<Integer> result = new HashSet<>();
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                while (index1 < nums1.length) {
                    if (nums1[index1] < nums2[index2]) {
                        index1++;
                    } else {
                        break;
                    }
                }
                continue;
            }
            if (nums2[index2] < nums1[index1]) {
                while (index2 < nums2.length) {
                    if (nums2[index2] < nums1[index1]) {
                        index2++;
                    } else {
                        break;
                    }
                }
                continue;
            }
            if (nums1[index1] == nums2[index2]) {
                if (!result.contains(nums1[index1])) {
                    result.add(nums1[index1]);
                }
                int tmp1 = nums1[index1];
                int tmp2 = nums2[index2];
                while (index1 < nums1.length) {
                    if (nums1[index1] == tmp1) {
                        index1++;
                    } else {
                        break;
                    }
                }
                while (index2 < nums2.length) {
                    if (nums2[index2] == tmp2) {
                        index2++;
                    } else {
                        break;
                    }
                }
            }
        }
        int[] res = new int[result.size()];
        int tmpIndex = 0;
        for (int i : result) {
            res[tmpIndex++] = i;
        }
        return res;
    }

    /**
     * 上面方法的简化版
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionSortSimple(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }

    /**
     * 题解里1ms 100%的方法
     * 用数组存储, 减少了set的存储和读取开销
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionSortQuick(int[] nums1, int[] nums2) {
        int length = Math.min(nums1.length, nums2.length);
        int[] temp = new int[length];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (index == 0 || temp[index - 1] != nums1[i]) {
                    temp[index++] = nums1[i];
                }
                i++;
                j++;
            }
            else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] ans = new int[index];
        for (int m = 0; m < index; m++) {
            ans[m] = temp[m];
        }
        return ans;
    }

    public int[] intersectionTwoSets(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<Integer>();
        Set<Integer> result = new HashSet<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            nums1Set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums1Set.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }
        int[] finalResult = new int[result.size()];
        int index = 0;
        for (Integer num : result) {
            finalResult[index] = num;
            index++;
        }
        return finalResult;
    }
}
