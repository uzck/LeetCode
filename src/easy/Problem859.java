package easy;

import java.util.*;

public class Problem859 {

    /**
     * 交换A中两个字母等于B
     * 先匹配最长公共子串
     * @param A
     * @param B
     * @return
     */
    public boolean buddyStrings(String A, String B) {
        if (A.equals("") || B.equals("") || A.length() != B.length()) {
            return false;
        }
        int swapCount = 0;
        int[] aData = new int[26], bData = new int[26];
        char[] aChar = A.toCharArray(), bChar = B.toCharArray();
        for (int i = 0; i < A.length(); i++) {
            aData[A.charAt(i) - 'a'] += 1;
            bData[B.charAt(i) - 'a'] += 1;
        }
        int differentCount = 0;
        for (int i = 0; i < aData.length; i++) {
            if (aData[i] != bData[i]) {
                differentCount += 1;
            }
        }
        if (differentCount > 0) {
            return false;
        }
        int startIndex = 0, endIndex = A.length() - 1;
        while (startIndex < endIndex) {
            while (startIndex < A.length() && aChar[startIndex] == bChar[startIndex]) {
                startIndex += 1;
            }
            if (startIndex >= A.length()) {
                break;
            }
            while (endIndex >= 0 && aChar[endIndex] == bChar[endIndex]) {
                endIndex -= 1;
            }
            if (endIndex < 0) {
                break;
            }
            if (startIndex < A.length() && endIndex >= 0 && aChar[startIndex] == bChar[endIndex]
                    && aChar[endIndex] == bChar[startIndex]) {
                char tempStart = aChar[startIndex];
                char tempEnd = aChar[endIndex];
                aChar[startIndex] = tempEnd;
                aChar[endIndex] = tempStart;
                swapCount += 1;
                if (swapCount > 1) {
                    break;
                }
                startIndex += 1;
                endIndex -= 1;
                continue;
            } else {
                return false;
            }
        }
        if (swapCount == 0) {
            for (int i = 0; i < aData.length; i++) {
                if (aData[i] >= 2) {
                    return true;
                }
            }
        }
        if (swapCount != 1) {
            return false;
        }

        return true;
    }

    public boolean buddyStringEasyMethod(String A, String B) {
        if (A.length() != B.length()) return false;
        // 判断"aa"和"aa" / "ab" "ab" 这样的情况
        if (A.equals(B)) {
            Set<Character> s = new HashSet<Character>();
            for (char c : A.toCharArray()) s.add(c);
            return s.size() < A.length();
        }
        List<Integer> dif = new ArrayList<>();
        for (int i = 0; i < A.length(); ++i) if (A.charAt(i) != B.charAt(i)) dif.add(i);
        return dif.size() == 2 && A.charAt(dif.get(0)) == B.charAt(dif.get(1)) && A.charAt(dif.get(1)) == B.charAt(dif.get(0));
    }

    public static void main(String[] args) {
        Problem859 problem859 = new Problem859();
        String A = "abab", B = "abab";
        System.out.println(problem859.buddyStrings(A, B));
    }
}
