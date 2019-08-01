package easy;

/**
 * Reverse Words in a String III
 * input: "Let's take LeetCode contest"
 * output: "s'teL ekat edoCteeL tsetnoc"
 */
public class LeetCode557 {

    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            buffer.append(reverse(split[i]));
            if (i < split.length - 1) {
                buffer.append(" ");
            }
        }
        return buffer.toString();
    }

    public String reverse(String s) {
        char[] sCharArray = s.toCharArray();
        int cLength = sCharArray.length;
        if (cLength % 2 == 1) {
            for (int i = 0; i < cLength / 2; i++) {
                char tmp = sCharArray[i];
                sCharArray[i] = sCharArray[cLength - 1 - i];
                sCharArray[cLength - 1 - i] = tmp;
            }
        } else {
            for (int i = 0; i < cLength / 2; i++) {
                char tmp = sCharArray[i];
                sCharArray[i] = sCharArray[cLength - 1 - i];
                sCharArray[cLength - 1 - i] = tmp;
            }
        }
        return new String(sCharArray);
    }

    public static void main(String[] args) {
        LeetCode557 leetCode557 = new LeetCode557();
        String result = leetCode557.reverseWords("Let's take LeetCode contest");
        System.out.println(result);
    }
}
