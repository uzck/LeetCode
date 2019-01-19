package easy;

/**
 * 反转数字二进制
 */
public class Problem476 {

    /**
     * 思路：先考虑直接取反，会因为前置0反转为1失败，所以只需要统计出该数字实际占得位数
     * 再通过相同位数的1...1掩码和num的反做与运算就行了
     * @param num
     * @return
     */
    public int findComplement(int num) {
        int bitsLength = 0, numCopy = num;
        while (num > 0) {
            num = num >>> 1;
            bitsLength += 1;
        }
        int tmp = 1;
        int index = 2;
        for (int i = 1; i < bitsLength; i++) {
            tmp += index;
            index *= 2;
        }
        return ~numCopy & tmp;
    }

    public static void main(String[] args) {
        Problem476 problem476 = new Problem476();
        System.out.println(problem476.findComplement(0));
    }
}
