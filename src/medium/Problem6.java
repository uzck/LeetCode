package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Zigzag Conversion
 * 较快的方法都是要找数学规律
 * 输入字符串以及行数 以zigzag的方式输出
 * 输入: PAYPALISHIRING
 * 输出
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 */
public class Problem6 {

    /**
     * 8ms 42.23% 看了solution之后... orz 为啥我要写这么sb的代码
     * 思路是先求出存储zigzag表示的内容需要几列 两个完整列之间的列数是numRows-2
     * @param s
     * @param numRows
     * @return
     */
    public String convertStupid(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int colSize = 0; // 计算列数
        int sLength = s.length();
        // 计算列数
        while (sLength > 0) {
            sLength -= numRows;
            colSize += 1;
            int tmp = numRows - 2;
            while (sLength > 0 && tmp > 0) {
                sLength -= 1;
                tmp -= 1;
                colSize += 1;
            }
        }
        char[][] zigzag = new char[numRows][colSize];
        int currentSize = 0;
        int row = 0;
        int col = 0;
        while (currentSize < s.length()) {
            while (currentSize < s.length() && row < numRows) {
                zigzag[row][col] = s.charAt(currentSize);
                currentSize += 1;
                row += 1;
            }
            col += 1;
            row = numRows - 2;
            int tmp = numRows - 2;
            while (currentSize < s.length() && row >= 0 && tmp > 0) {
                zigzag[row][col] = s.charAt(currentSize);
                currentSize += 1;
                row -= 1;
                col += 1;
                tmp -= 1;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < colSize; j++) {
                if (zigzag[i][j] != '\u0000') {
                    builder.append(zigzag[i][j]);
                }
            }
        }
        return builder.toString();
    }

    /**
     * 因为row的变化是[0,numRows-1]然后再从[numRows-1,0]
     * 依次读数据
     * @param s
     * @param numRows
     * @return
     */
    public String convertSort(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false; // goingDown用来变向

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    /**
     * 3ms 95.97%
     * 每一行两列完整之间只有一个数字
     * 按行读
     * @param s
     * @param numRows
     * @return
     */
    public String convertVisitByRow(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2; // numRows + numRows - 2 第0行的相邻元素在原字符串之间的距离

        // 第numRows-1行的相邻元素在原字符串之间的距离(2 * numRows - 2) + numRows - 1
        // 第i行 (2 * numRows - 2) + i和 (2 * numRows - 2) - i
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                // j + cycleLen - i 表示这一行下一个zigzag中间斜着的数在原字符串里的坐标
                // 比如第1行L的位置是S的左边1位 第2行A是H左边两位 从第0行开始
                // P    I    N
                // A  L S  I G
                // Y A  H R
                // p    I
                // j + cycleLen - i是下一个不是一整列的数坐在的位置
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        Problem6 problem6 = new Problem6();
        String result = problem6.convertVisitByRow("PAYPALISHIRING", 4);
        System.out.println(result);
    }
}
