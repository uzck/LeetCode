package easy;

/**
 * 有一个int[]输入A,一个query[] 每个query的任务是在index=query[i][1],val=query[i][0]的条件下 执行A[index]=val 再返回A中偶数的和
 */
public class Problem985 {

    /**
     * 8ms 30.78%...
     *
     * @param A
     * @param queries
     * @return
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] result = new int[queries.length];
        int evenSum = 0;
        // 初始情况下A中的偶数和
        for (int i = 0; i < A.length; i++) {
            if (Math.abs(A[i]) % 2 == 0) {
                evenSum += A[i];
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][1];
            int val = queries[i][0];
            int absVal = Math.abs(val);
            // 这里需要注意给的val可能是负数  不能写val % 2 == 0 应该写Math.abs(val) % 2 == 0
            // 四种情况
            // 奇 + 奇 偶 + 偶 奇 + 偶 偶 + 奇 分类讨论
            if ((A[index] % 2 == 0) && (absVal % 2 == 0)) {
                result[i] = evenSum + val;
            } else if ((A[index] % 2 != 0) && (absVal % 2 != 0)) {
                result[i] = evenSum + val + A[index];
            } else if ((A[index] % 2 != 0) && (absVal % 2 == 0)) {
                result[i] = evenSum;
            } else if ((A[index] % 2 == 0) && (absVal % 2 != 0)) {
                result[i] = evenSum - A[index];
            }
            evenSum = result[i];
            A[index] += val;
        }
        return result;
    }

    public int[] sumEvenAfterQueriesSimple(int[] A, int[][] queries) {
        int sum = 0, i = 0;
        for (int a : A) {
            if (a % 2 == 0) sum += a;
        }
        int[] ans = new int[queries.length];
        for (int[] q : queries) {
            // 如果当前A[index]为偶数 先总和里减去该数 这样就不需要做单独的判断
            // 只需要考虑val + A[index]是否为偶数
            if (A[q[1]] % 2 == 0) {
                sum -= A[q[1]];
            }
            A[q[1]] += q[0];
            if (A[q[1]] % 2 == 0) {
                sum += A[q[1]];
            }
            ans[i++] = sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem985 problem985 = new Problem985();
        problem985.sumEvenAfterQueries(new int[]{1, 2, 3, 4}, null);
    }
}
