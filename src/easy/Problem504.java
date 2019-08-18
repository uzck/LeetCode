package easy;

/**
 * Base 7
 * 10进制数用7进制表示
 * 输入的范围在[-1e7,1e7] 不用考虑越界问题
 */
public class Problem504 {

    /**
     * 1ms 94.12%
     * 短除法
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        StringBuffer buffer = new StringBuffer();
        while (num > 0) {
            buffer.append(num % 7);
            num /= 7;
        }
        return num > 0 ? buffer.reverse().toString() : "-" + buffer.reverse().toString();
    }

    public static void main(String[] args) {
    }
}
