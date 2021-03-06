package easy;

/**
 * Divisor Game
 * 开始有一个数字N 两个人依次在0 < x < N (N % x == 0)中选择一个x 把N替换为N-x 如果x不存在 判输
 * 假设两个人都选择最优解 判断先手是否能赢(只要存在赢的解法)
 */
public class Problem1025 {

    /**
     * 只要当前的N > 1游戏就还可以继续
     * 偶数必然会赢
     * 因为输的条件的当前数字为1 因此只要拿到的数字是偶数(大于2 2直接就赢了) 那么只要选择x=1 对手拿到的就是奇数 无论对手怎么选择x.下一轮你手上的数就不会是1
     * 只要再选择x使得N-x为奇数就可以继续游戏
     * 但如果拿到的是奇数 反过来不管这局怎么选择 对手都可以选择合适的x使得N-x为奇数把偶数的流程反过来
     * 证明过程 假设N时 Alice会输,那么N+1时 Alice必然会赢 因为输的条件是当前的数字为1则N+1时最后一局Alice的数字应该是2
     * 反过来如果N时 Alice会赢那么N+1时 Alice必然会输 因为N+1的时候N=2出现在对手的回合里
     * 递推  N=1时 Alice会输 所以N=2时 Alice会赢
     * 也就是 N为奇数时会输 N为偶数时会赢
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
       return N % 2 == 0;
    }
}
