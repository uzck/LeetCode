package easy;

public class Problem657 {

    /**
     * 判断机器人运动完是否回到原点
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        int up = 0, left = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U':
                    up += 1;
                    break;
                case 'D':
                    up -= 1;
                    break;
                case 'L':
                    left += 1;
                    break;
                case 'R':
                    left -= 1;
                    break;
            }
        }
        return up == 0 && left == 0;
    }

    public static void main(String[] args) {
        Problem657 problem657 = new Problem657();
        System.out.println(problem657.judgeCircle("UD"));
    }
}
