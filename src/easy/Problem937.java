package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Reorder Log Files
 * 输入是一些log String[] log的第一个单词表示这个log的类型：
 * 1. 后面全是数字 digit-log
 * 2. 后面全是小写字母 letter-log
 * 对string[]重排序让letter-log出现在digit-log之前
 * letter-log按除了标志位之后的数据的字典序排序 digit-log保持原来的相对顺序
 */
public class Problem937 {

    public String[] reorderLogFiles(String[] logs) {
        ArrayList<String> letterLogList = new ArrayList<>();
        ArrayList<String> digitLogList = new ArrayList<>();
        String firstData;
        for (int i = 0; i < logs.length; i++) {
            String[] split = logs[i].split(" ");
            firstData = split[1];
            // 如果为letter-log
            if (Character.isLetter(firstData.charAt(0))) {
                letterLogList.add(logs[i]);
            } else if (Character.isDigit(firstData.charAt(0))) {
                // 为digit-log
                digitLogList.add(logs[i]);
            }
        }
        letterLogList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int firstBlankIndex1 = o1.indexOf(' ');
                int firstBlankIndex2 = o2.indexOf(' ');
                String identifier1 = o1.substring(0, firstBlankIndex1);
                String identifier2 = o2.substring(0, firstBlankIndex2);
                String data1 = o1.substring(firstBlankIndex1 + 1, o1.length());
                String data2 = o2.substring(firstBlankIndex2 + 1, o2.length());
                if (data1.compareTo(data2) != 0) {
                    return data1.compareTo(data2);
                } else {
                    return identifier1.compareTo(identifier2);
                }
            }
        });
        String[] result = new String[logs.length];
        int index = 0;
        for (String data : letterLogList) {
            result[index++] = data;
        }
        for (String data : digitLogList) {
            result[index++] = data;
        }
        return result;
    }

    public String[] reorderLogFilesNotUseList(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
}
