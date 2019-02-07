package easy;

public class Problem13 {

    // 对I V X L C D M分别处理
    public int romanToInt(String s) {

        char pre = ' ';
        char[] array = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                pre = array[i - 1];
            }
            switch (array[i]) {
                case 'I':
                    sum += 1;
                    break;
                case 'V':
                    if (pre == 'I') {
                        sum -= 2;
                    }
                    sum += 5;
                    break;
                case 'X':
                    if (pre == 'I') {
                        sum -= 2;
                    }
                    sum += 10;
                    break;
                case 'L':
                    if (pre == 'X') {
                        sum -= 20;
                    }
                    sum += 50;
                    break;
                case 'C':
                    if (pre == 'X') {
                        sum -= 20;
                    }
                    sum += 100;
                    break;
                case 'D':
                    if (pre == 'C') {
                        sum -= 200;
                    }
                    sum += 500;
                    break;
                case 'M':
                    if (pre == 'C') {
                        sum -= 200;
                    }
                    sum += 1000;
                    break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        S
    }
}
