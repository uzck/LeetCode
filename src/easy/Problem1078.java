package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Occurrences After Bigram
 * 从text中找出first second并且输出紧接在second后面的词
 */
public class Problem1078 {

    public String[] findOccurrences(String text, String first, String second) {
        String tmpl = first + " " + second;
        int startIndex = 0;
        int endIndex = 0;
        List<String> result = new ArrayList<>();
        StringBuffer buffer;
        while ((endIndex = text.indexOf(tmpl, startIndex)) != -1) {
            endIndex += tmpl.length() + 1;
            startIndex = endIndex;
            buffer = new StringBuffer();
            for (int i = endIndex; i < text.length(); i++) {
                if (text.charAt(i) == ' ') {
                    break;
                }
                buffer.append(text.charAt(i));
            }
            // buffer是否添加了内容的判断 否则模板在text末尾时会添加""
            if (!buffer.toString().equals("")) {
                result.add(buffer.toString());
            }
        }
        String[] resultArray = new String[0];
        return result.toArray(resultArray);
    }

    public static void main(String[] args) {
        Problem1078 problem1078 = new Problem1078();
        String[] result = problem1078.findOccurrences(
                "ypkk lnlqhmaohv lnlqhmaohv lnlqhmaohv ypkk ypkk ypkk ypkk ypkk ypkk lnlqhmaohv lnlqhmaohv lnlqhmaohv lnlqhmaohv ypkk ypkk ypkk lnlqhmaohv lnlqhmaohv ypkk",
                "lnlqhmaohv",
                "ypkk");
        for (String s : result) {
            System.out.println(s);
        }
    }
}
