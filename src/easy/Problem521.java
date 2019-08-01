package easy;

/**
 * 找出最长非公共子串
 * solution里的评论hhhhh
 */
public class Problem521 {

    public int findLuSLength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return a.length() > b.length() ? a.length() : b.length();
    }
}
