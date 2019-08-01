package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出一组string中共同的字符(包含重复的部分)
 * input: ["bella", "label", "roller"]
 * result: ["e", "l", "l"]
 */
public class Problem1002 {

    public List<String> commonChars(String[] A) {
        int[][] stringCharCounter = new int[A.length][26];
        for (int i = 0; i < A.length; i++) {
            for (char c : A[i].toCharArray()) {
                stringCharCounter[i][c - 'a'] += 1;
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            int minSame = Integer.MAX_VALUE;
            for (int j = 0; j < A.length; j++) {
                if (stringCharCounter[j][i] < minSame) {
                    minSame = stringCharCounter[j][i];
                }
            }
            for (int j = 0; j < minSame; j++) {
                result.add(String.valueOf((char) (i + 'a')));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem1002 problem1002 = new Problem1002();
        List<String> result = problem1002.commonChars(new String[]{"bella", "label", "roller"});
        if (result != null) {
            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}
