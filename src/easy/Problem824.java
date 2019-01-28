package easy;

/**
 * Goat Latin：
 * 1. a e i o u开头 结尾加上"ma"
 * 2. 如果不是a e i o u开头，去掉第一个字母 添加到末尾 再加ma
 * 3. 每个词的结尾加'a',第一个词加一个，第二个词加两个
 */
public class Problem824 {

    public String toGoatLatin(String S) {
        if (S == null || S.equals("")) {
            return "";
        }
        String[] split = S.split(" ");
        String tmp;
        StringBuilder builder = new StringBuilder();
        int aCount = 1;
        for (String s : split) {
            if (s.startsWith("a") || s.startsWith("e") || s.startsWith("i")
                    || s.startsWith("o") || s.startsWith("u")
                    || s.startsWith("A") || s.startsWith("E") || s.startsWith("I")
                    || s.startsWith("O") || s.startsWith("U")) {
                builder.append(s + "ma");
                for (int i = 0; i < aCount; i++) {
                    builder.append("a");
                }
            } else {
                if (s.length() == 1) {
                    builder.append(s + "ma");
                } else {
                    tmp = s.substring(1, s.length());
                    builder.append(tmp + s.charAt(0) + "ma");
                }
                for (int i = 0; i < aCount; i++) {
                    builder.append("a");
                }
//                aCount += 1;
            }
            aCount += 1;
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
        Problem824 problem824 = new Problem824();
        System.out.println(problem824.toGoatLatin("I speak Goat Latin"));
    }
}
