package easy;

import java.util.*;

/**
 * 有两个int数组(每个数组内没有重复数字) num1和num2 num1是num2的子集 找出对应num1的元素对应的next greater numbers
 * num1里x的next greater numbers的定义是在num2中找到x然后输出右边第一个大于x的数 如果没有返回-1
 * num1=[4,1,2] num2=[1,3,4,2]
 * num2里4的右边没有比它大的 返回-1
 * num2里1的右边第一个比1大的是3 返回3
 * num2里2的右边没有数字了 返回-1
 */
public class Problem496 {

    /**
     * 用来同时保存数值和原来的位置
     */
    class IndexVal {
        int val;
        int index;

        public IndexVal(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    int preIndex;

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        IndexVal[] indexVals = new IndexVal[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            indexVals[i] = new IndexVal(nums1[i], i);
        }

        // 从小到大排序
        Arrays.sort(indexVals, new Comparator<IndexVal>() {
            @Override
            public int compare(IndexVal o1, IndexVal o2) {
                return o1.val - o2.val;
            }
        });
        int[] res = new int[nums1.length];
        // 从大到小 到num2里面去找相应的位置
        for (int i = indexVals.length - 1; i >= 0; i--) {
            res[indexVals[i].index] = findRightGreater(indexVals[i].val, nums2);
        }
        return res;
    }

    /**
     * 具体的找next greater函数
     *
     * @param num
     * @param nums2
     * @return
     */
    public int findRightGreater(int num, int[] nums2) {
        int currentIndex = 0;
        // 找到当前数据所在的位置
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] == num) {
                currentIndex = i;
                break;
            }
        }
        // preIndex存储的上个数值所在的位置 因为先做了排序 所以nums2[preIndex]是大于nums2[currentIndex]的
        // 因此如果currentIndex < preIndex 遍历的上限就可以从nums2.length缩减到preIndex
        for (int i = currentIndex + 1; i < (preIndex > currentIndex ? preIndex + 1 : nums2.length); i++) {
            if (nums2[i] > num) {
                return nums2[i];
            }
        }
        return -1;
    }

    /**
     * 3ms 79.48%
     * 直接对nums2进行建表 用hashmap存储nums2中每个数字的next greater值
     * 这里用栈来保持降序 如果当前的栈顶元素小于要插入的元素 则弹出栈顶元素并把该插入元素作为弹出元素的next greater number直到栈顶元素大于插入元素,再插入该元素
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElementUseStack(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++)
            // 如果Map中没有该值作为key 说明该值没有next greater number
            nums1[i] = map.getOrDefault(nums1[i], -1);
        return nums1;
    }

    /**
     * 也是从nums2下手 先把num2的值和index保存在hashmap中
     * 再遍历nums1 这样遍历的起始index变大 整个遍历范围就变小了
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElementReduceRange(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 == 0) {
            return nums1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len2; i++) {
            map.put(nums2[i], i);
        }

        for (int i = 0; i < len1; i++) {
            int startIndex = map.get(nums1[i]);
            nums1[i] = -1;
            for (int j = startIndex + 1; j < len2; j++) {
                if (nums2[j] > nums2[startIndex]) {
                    nums1[i] = nums2[j];
                    break;
                }
            }
        }

        return nums1;
    }

    /**
     * 感觉就是暴力法遍历啊 - -为啥这也能100%
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElementBF(int[] nums1, int[] nums2) {


        int[] ar = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int a = findNum(nums1[i], nums2);
            if (a == -1) ar[i] = -1;
            else ar[i] = nums2[a];
        }
        return ar;
    }

    public static int findNum(int s, int[] ar) {
        int startIndex = findIndex(s, ar);
        if (startIndex == ar.length - 1) return -1;

        for (int j = startIndex + 1; j < ar.length; j++) {
            if (ar[j] > s) return j;
        }
        return -1;
    }

    private static int findIndex(int s, int ar[]) {
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == s) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Problem496 problem496 = new Problem496();
        int[] res = problem496.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        System.out.println(res);
    }
}
