package easy;

/**
 * Palindrome Number
 * 回文数字
 */
public class Problem9 {

    /**
     * 转为字符串 双指针
     * @param x
     * @return
     */
    public boolean isPalindromeStr(int x) {
        String xStr = String.valueOf(x);
        char[] array = xStr.toCharArray();
        int i = 0, j = array.length - 1;
        while (i < j) {
            if (array[i] != array[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isPalindrome(int x) {
        // 负数因为有负号不是回文
        // 最后一个数字是0的 但是本身不等于0的数也肯定不是回文
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber) {
            // x % 10 最后的一位数字
            revertedNumber = revertedNumber * 10 + x % 10;
            // x /= 10等于x的字符串形式上向右移动一位
            x /= 10;
        }

        // 这里要分长度的奇偶考虑 如果为偶数 x==revertedNumber
        // 如果为奇数 revertedNumber比x多一位中间数 所以x == revertedNumber / 10
        return x == revertedNumber || x == revertedNumber/10;
    }

    public static void main(String[] args) {
        Problem9 problem9 = new Problem9();
        problem9.isPalindrome(10);
    }
}
