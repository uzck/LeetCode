package easy;

import java.util.ArrayList;

/**
 * Greatest Common Divisor of Strings
 * 输入S和T 如果S=T+...+T 那么说T divides S
 * 找出能divides str1和str2的子串 x 不存在的话返回""
 */
public class Problem1071 {

    /**
     * 2ms 43.75%
     * 思路: 先检查两个字符串的构成以及对应字母数量的比例是否一致 不一致直接返回"" (然后实际上判断比例是否相等这一步是不需要的.. 在后面找出找出最大公约数之后截取两个子串比较是否相等就行)
     * 因为两个字符串要被一个x除 所以如果两个字符串的长度为倍数关系 那么较短的字符串就是要求的x
     * 如果两个字符串不相等 求str1.length()和str2.length()的最大公约数 也就是要求的x的长度
     * 求得x的长度后在str1或str2里用substring(0, xLength)就是最后要求的x
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
        int[] letter1 = new int[26];
        int[] letter2 = new int[26];
        int firstLetterIndex1 = -1;
        int firstLetterIndex2 = -1;
        for (int i = 0; i < str1.length(); i++) {
            if (firstLetterIndex1 == -1) {
                firstLetterIndex1 = str1.charAt(i) - 'A';
            }
            letter1[str1.charAt(i)-'A'] += 1;
        }
        for (int i = 0; i < str2.length(); i++) {
            if (firstLetterIndex2 == -1) {
                firstLetterIndex2 = str2.charAt(i) - 'A';
            }
            letter2[str2.charAt(i)-'A'] += 1;
        }
        // 检查构成str1和str2的字母种类是否一致
        double scale = -1;
        for (int i = 0; i < 26; i++) {
            if ((letter1[i] == 0 && letter2[i] != 0) || (letter1[i] == 0 && letter2[i] != 0)) {
                return "";
            }
            if (letter1[i] != 0 && letter2[i] != 0) {
                if (scale == -1) {
                    scale = letter1[i] / 1. / letter2[i];
                } else {
                    // 字母的比例不一致
                    if ((Math.abs(letter1[i] / 1. / letter2[i]) - scale) > 0.001) {
                        return "";
                    }
                }
            }
        }
        // 两个字符串可以被x divides
        if (Math.max(str1.length(), str2.length()) % Math.min(str1.length(), str2.length()) == 0) {
            return str1.length() <= str2.length() ? str1 : str2;
        } else {
            int tmp = 0;
            int str1Length = str1.length();
            int str2Length = str2.length();
            for (int i = 1; i < (str1Length < str2Length ? str1Length : str2Length); i++) {
                if (str1Length % i == 0 && str2Length % i == 0) {
                    tmp = i;
                }
            }
            String sub1 = str1.substring(0, tmp);
            String sub2 = str2.substring(0, tmp);
            return sub1.equals(sub2) ? sub1 : "";
//            return str1.length() < str2.length() ?
//                    str1.substring(0, tmp) : str2.substring(0, tmp);
        }
    }

    /**
     *
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStringsRecursive(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int gcd_len = gcd(len1, len2);

        // 在str1里截取可能的x
        String t = str1.substring(0, gcd_len);

        int i = 0;
        // i表示添加n个子串后的长度
        while (i < len1 && str1.indexOf(t, i) != -1)
            i += gcd_len;

        // 检验str1是否全部由t构成
        if(i!=len1)
            return "";

        i = 0;
        while (i < len2 && str2.indexOf(t, i) != -1)
            i += gcd_len;

        // 如果i ！= len2 说明str2不能全由t构成
        if(i!=len2)
            return "";

        return t;
    }

    /**
     * 辗转相除法求两个数的最大公约数
     * 辗转相除法基于如下原理：两个整数的最大公约数等于其中较小的数和两数的差的最大公约数
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b){
        if(b==0)
            return a;
        return gcd(b,a%b);
    }

    public static void main(String[] args) {
        Problem1071 problem1071 = new Problem1071();
        String result = problem1071.gcdOfStrings("LEET", "CODE");
        System.out.println(result);
    }
}
