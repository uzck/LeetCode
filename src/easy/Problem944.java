package easy;

import java.util.ArrayList;

public class Problem944 {

    /**
     * 太复杂
     * @param A
     * @return
     */
    @Deprecated
    public int minDeletionSize(String[] A) {
        // 不需要删除元素
        if (A == null || A.length == 1 || A.length == 0) {
            return 0;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < A.length; i++) {
            builder.append(A[i]);
        }
        char[] combineChars = builder.toString().toCharArray();
        int before, after, sum = 0;
        // j表示字符串的第j个字符
        // index表示第index个字符串
        for (int j = 0; j < A[0].length(); j++) {
            for (int index = 0; index < A.length - 1; index++) {
                before = index * A[0].length() + j;
                after = (index + 1) * A[0].length() + j;
                if (combineChars[before] > combineChars[after]) {
                    sum += 1;
                    break;
                }
            }
        }

        return sum;
    }

    public int simpleMinDeletionSize(String[] A) {
        int ans = 0, m = A.length, n = A[0].length();
        for (int j = 0; j < n; j++){
            for (int i = 0; i < m - 1; i++)
                if (A[i].charAt(j) > A[i+1].charAt(j)) {
                    ans++;
                    break;
                }
        }
        return ans;
    }

    public static void main(String[] args) {

//        String[] inputs = new String[] {"cba", "daf", "ghi"};
//        String[] inputs = new String[] {"a", "b"};
//        String[] inputs = new String[] {"zyx", "wvu", "tsr"};
        String[] inputs = new String[] {"rrjk", "furt", "guzm"};

        Problem944 problem944 = new Problem944();
        System.out.println(problem944.simpleMinDeletionSize(inputs));
    }
}
