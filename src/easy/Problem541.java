package easy;


/**
 * Reverse String II
 *  输入一个字符串和数字k 每2k个字符旋转前k个字符 如果最后剩下n<k个字符 全部旋转
 *  字符串只含有小写英文字母
 *  字符串的长度和k的大小在[1,10000]
 */
public class Problem541 {

    /**
     * 1ms 68.3%
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] data = s.toCharArray();
        int thresh1 = s.length() > k ? k - 1 : s.length() - 1;
        // 如果k比整个字符串长度大 缩小k到字符串长度
        k = Math.min(s.length(), k);
        int index = 0;
        while (index < data.length) {
            int swapCount = 0;
            // 交换前k/2个数 因为保证了thresh1-swapCount的最大值是字符串的末尾
            // 所以swap的时候不需要特别判断
            while (index < data.length && swapCount < (k / 2)) {
                swap(data, index, thresh1 - swapCount);
                swapCount += 1;
                index += 1;
            }
            // 这里考虑k为奇和偶的情况
            // 为奇的情况比为偶的情况少一次index+=1 所以奇数情况多跳一个
            index += k % 2 == 0 ? k / 2 : k / 2 + 1;
            // 跳过k-2k的数
            int notSwapCount = 0;
            while (index < data.length && notSwapCount < k) {
                index += 1;
                notSwapCount += 1;
            }
            // 更新门限值 如果k比整个字符串长度大 缩小k到字符串长度
            thresh1 = thresh1 + 2 * k < data.length - 1 ? thresh1 + 2 * k : data.length - 1;
            // 限制k的大小
            // 如果目前坐标到thresh的长度l比k小 则用l取代k
            k = Math.min(k, thresh1 - index + 1);
        }
        return new String(data);
    }

    public void swap(char[] data, int start, int rev) {
        char tmp = data[rev];
        data[rev] = data[start];
        data[start] = tmp;
    }

    /**
     * 1ms 68.63%
     * 上面的简单写法 跳过了k-2k的部分
     * @param s
     * @param k
     * @return
     */
    public String reverseStrSimple(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            // 交换
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }

    public static void main(String[] args) {
        Problem541 problem541 = new Problem541();
        String result = problem541.reverseStr("hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl", 39);
        System.out.println(result);
    }
}
