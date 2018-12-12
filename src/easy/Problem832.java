package easy;

/**
 * Flipping an image
 */
public class Problem832 {

    /**
     * 核心点 数组逆序
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {
        if (A == null|| A.length == 0) {
            return A;
        }
        int size = A.length; // 图像的width
        int start, end;
        // 逐行处理
        for (int i = 0; i < size; i++) {
            start = 0;
            end = A[0].length - 1;
            while (start < end) {
                swap(A[i], start, end);
                start++;
                end--;
            }
            if (start == end) {
                A[i][start] = (A[i][start] + 1) % 2;
            }

        }
        return A;
    }

    public void swap(int[] A, int prior, int rear) {
        int temp = A[prior];
        A[prior] = (A[rear] + 1) % 2;
        A[rear] = (temp + 1) % 2;
    }

    public static void main(String[] args) {
        Problem832 problem832 = new Problem832();
        int[][] A = new int[3][3];
        A[0] = new int[] {1, 1, 0};
        A[1] = new int[] {1, 0, 1};
        A[2] = new int[] {0, 0, 0};
        A = problem832.flipAndInvertImage(A);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
