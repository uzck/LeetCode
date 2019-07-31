package medium;

/**
 * 数字转罗马字 数字范围1-3999
 */
public class Problem12 {

    public String intToRoman(int num) {
        int[] split = new int[] {num / 1000, (num % 1000) / 100, (num % 100) / 10, num % 10};
        String[][] roman = new String[][] {new String[]{},new String[]{"C", "D", "M"} , new String[]{"X", "L", "C"}, new String[]{"I", "V", "X"}};
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i <= split[0]; i++) {
            buffer.append("M");
        }
        for (int i = 1; i < 4; i++) {
            if (split[i] == 0) {
                continue;
            }
            if (split[i] == 4 || split[i] == 9) {
                process4And9(split[i], buffer, roman[i]);
            } else {
                if (split[i] == 5) {
                    buffer.append(roman[i][1]);
                    continue;
                }
                if (split[i] > 5) {
                    buffer.append(roman[i][1]);
                    for (int j = 1; j <= split[i] - 5; j++) {
                        buffer.append(roman[i][0]);
                    }
                } else {
                    for (int j = 1; j <= split[i]; j++) {
                        buffer.append(roman[i][0]);
                    }
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 对4和9单独处理
     * @param count
     * @param buffer
     * @param roman
     */
    public void process4And9(int count, StringBuffer buffer, String[] roman) {
        if (count == 4) {
            buffer.append(roman[0] + roman[1]);
        } else {
            buffer.append(roman[0] + roman[2]);
        }
    }

    /**
     * 论坛比较好的解
     * @param num
     * @return
     */
    public String intToRomanGoodVer(int num) {
        String[] romans = new String[]{"M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV", "I"};
        int[] numbers = new int[]{1000, 900, 500, 400,
                100, 90, 50, 40,
                10, 9, 5, 4, 1};
        StringBuilder romanRes = new StringBuilder();
        for (int i = 0; i < romans.length; i++) {
            if (num == 0) break;
            int count = num/numbers[i];
            while (count > 0) {
                romanRes.append(romans[i]);
                count--;
            }
            num = num % numbers[i];
        }

        return romanRes.toString();
    }

    public static void main(String[] args) {
        Problem12 problem12 = new Problem12();
        String result = problem12.intToRoman(1994);
        System.out.println(result);
    }
}
