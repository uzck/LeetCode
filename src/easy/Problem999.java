package easy;

import java.util.ArrayList;

/**
 * 8x8的棋盘上有车(城堡)(rook),空格(.),主教(bishop),兵(pawn) 分别用'R', '.', 'B', 'p'表示 大写表示白色 小写表示黑色
 * rook的走法是可以沿着上下左右 可以吃异色的兵 但是不能移动到己方主教的格子上
 * 返回rook在一次移动内可以吃几个兵 这里一次移动有歧义,实际是指往四个方向都可以尝试移动一次 每个方向移动完之后还原棋盘
 */
public class Problem999 {

    /**
     * 找到rook之后往上下左右遍历 如果先碰到主教 该方向就只能吃0个兵 如果只遇到了兵 该方向能吃1个
     * @param board
     * @return
     */
    public int numRookCaptures(char[][] board) {

        int RX = -1, RY = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    RX = i;
                    RY = j;
                }
            }
        }
        // 往前遍历该行
        int leftRow = 0;
        for (int i = RY - 1; i >= 0; i--) {
            if (board[RX][i] == 'B') {
                break;
            }
            if (board[RX][i] == 'p') {
                leftRow = 1;
                break;
            }
        }
        int rightRow = 0;
        for (int i = RY + 1; i < board[RX].length; i++) {
            if (board[RX][i] == 'B') {
                break;
            }
            if (board[RX][i] == 'p') {
                rightRow = 1;
                break;
            }
        }

        int bottomCol = 0;
        for (int i = RX + 1; i < board.length; i++) {
            if (board[i][RY] == 'B') {
                break;
            }
            if (board[i][RY] == 'p') {
                bottomCol = 1;
                break;
            }
        }
        int topCol = 0;
        for (int i = RX - 1; i >= 0; i--) {
            if (board[i][RY] == 'B') {
                break;
            }
            if (board[i][RY] == 'p') {
                topCol = 1;
                break;
            }
        }
        return leftRow + rightRow + topCol + bottomCol;

    }
}
