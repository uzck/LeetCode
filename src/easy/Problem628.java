package easy;

import java.util.Arrays;

/**
 * Maximum Product of Three Numbers
 * 找出数组里最大的三个数 返回乘积
 * 数组的长度在[3,10^4]之间 数字范围在[-1000,1000] 乘积不会溢出
 */
public class Problem628 {

    /**
     * 17ms 13.22%
     * 排序完后总共有两种可能
     * 1. 两个负数乘一个正数
     * 2. 三个正数
     * @param nums
     * @return
     */
    public int maximumProductSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n-1], nums[n-1] * nums[n-2] * nums[n-3]);
    }

    /**
     * 2ms 99.7%
     * O(n)
     * 由排序法可以知道 实际上只需要最小的两个数和最大的三个数
     * @param nums
     * @return
     */
    public int maxiumProductIter(int[] nums) {
        // max1 > max2 > max3
        // min1 < min2
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        return Math.max(max1*max2*max3, max1*min1*min2);
    }

    public static void main(String[] args) {
        Problem628 problem628 = new Problem628();
        problem628.maximumProductSort(new int[] {722,634,-504,-379,163,-613,-842,-578,750,951,-158,30,-238,-392,-487,-797,-157,-374,999,-5,-521,-879,-858,382,626,803,-347,903,-205,57,-342,186,-736,17,83,726,-960,343,-984,937,-758,-122,577,-595,-544,-559,903,-183,192,825,368,-674,57,-959,884,29,-681,-339,582,969,-95,-455,-275,205,-548,79,258,35,233,203,20,-936,878,-868,-458,-882,867,-664,-892,-687,322,844,-745,447,-909,-586,69,-88,88,445,-553,-666,130,-640,-918,-7,-420,-368,250,-786});
    }
}
