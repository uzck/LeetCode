package easy;

public class Problem896 {

    /**
     * 用flag表示前后两个数的正负
     * A[i]-A[i-1])*flag > 0表示这两次相同
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {
        int flag=0;
        for(int i=1;i<A.length;i++){
            if((A[i]-A[i-1])*flag<0){
                return false;
            }else if(flag==0){
                flag=A[i]-A[i-1];
            }
        }
        return true;
    }

    /**
     * 先获取前几个数的增减情况
     * 再把之后每次和这次比对
     * @param A
     * @return
     */
    public boolean isMonotonicAnother(int[] A) {
        if (A.length < 2) {
            return true;
        }
        boolean hasChange = false;
        int direction = -2;
        for (int i = 0; i < A.length - 1; i++) {
            if ((A[i] - A[i+1]) != 0) {
                direction = (A[i] - A[i+1]) > 0 ? 1 : -1;
                hasChange = true;
                break;
            }
        }
        if (!hasChange) {
            direction = 0;
        }

        for (int i = 0; i < A.length - 1; i++) {
            if ((A[i] - A[i+1]) == 0) {
                continue;
            }
            int currentDirection = (A[i] - A[i+1]) > 0 ? 1 : -1;
            if (currentDirection != direction) {
                return false;
            }
        }
        return true;
    }
}
