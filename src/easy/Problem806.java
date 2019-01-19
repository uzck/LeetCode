package easy;

public class Problem806 {

    public int[] numberOfLines(int[] widths, String S) {
        int[] result = new int[2];
        int line = 1;
        int currentWidth = 0;
        for (char c : S.toCharArray()) {
            if ((currentWidth + widths[c - 'a']) > 100) {
                line += 1;
                currentWidth = widths[c - 'a'];
            } else {
                currentWidth += widths[c - 'a'];
            }
        }
        result[0] = line;
        result[1] = currentWidth;
        return result;
    }

    public static void main(String[] args) {
        Problem806 problem806 = new Problem806();
        int[] widths = new int[] {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String s = "abcdefghijklmnopqrstuvwxyz";
        problem806.numberOfLines(widths, s);
    }
}
