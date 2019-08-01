package easy;

/**
 * To Lower Case
 * 字符串小写
 */
public class Problem709 {

    public String toLowerCase(String str) {

        char[] sArray = str.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i] >= 'A' && sArray[i] <= 'Z') {
                sArray[i] += 32;
            }
        }
        return new String(sArray);
    }
}
