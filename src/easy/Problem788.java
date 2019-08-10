package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Rotated Digit
 * 定义了交换规则: 0,1,8和自身交换 2<->5 6<->9剩下的数字为invalid
 * 如果数字X经过上述规则的交换后与原数字不同的话 算一个good number
 */
public class Problem788 {

    /**
     * 10ms 31.01% 字符串里包含2,5,6,9的话字符串一定会发生变化 再排除掉valid的值3,4,7
     * 0,1,8因为和自身旋转所以有或没有不影响结果
     * @param N
     * @return
     */
    public int rotatedDigits(int N) {
        int valid = 0;
        String origin;
        for (int i = 1; i <= N; i++) {
            origin = String.valueOf(i);
            if ((origin.indexOf('2') != -1 || origin.indexOf('5') != -1 || origin.indexOf('6') != -1 || origin.indexOf('9') != -1)
                    && (origin.indexOf('3') == -1 && origin.indexOf('4') == -1 && origin.indexOf('7') == -1)) {
                valid += 1;
            }
        }
        return valid;
    }

    /**
     * O(logN) 0ms 100%
     * 计算第x位数字z的方法 计算x位为[0,z-1]的可能组合 当x为z时考虑x-1位的情况
     * 例子: N = 542 count = 0
     * ds里的数据为2,4,5
     * 先取出d=5 i=2
     * good=2 soso=0,1 count += (3) * (7 ^ 2) - 2 * (3 ^ 2)
     * 再取出d=4 i=1
     * good=2 soso=0,1 count += (3) * (7 ^ 1) - 2 * (3 ^ 1)
     * 然后因为4是invalid的数 算法停止
     * @param N
     * @return
     */
    public int rotatedDigitsFast(int N) {
        // rotate数组对应数字0-9 0表示旋转至自身 1表示旋转后改变 -1表示invalid
        int[] rotate = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
        // 从低位起 存储每一位的数字
        List<Integer> ds = new ArrayList();
        while (N > 0) {
            ds.add(N % 10);
            N /= 10;
        }
        int count = 0;
        boolean diff = false;
        for (int i = ds.size() - 1; i > -1; --i) {
            int good = 0, soso = 0, d = ds.get(i);
            // 从最高位开始统计 例如如果N=399 那么从300开始的数实际上都是false的
            // 如果N=399 第一次就是统计N=0xx N=1xx N=2xx
            for (int j = 0; j < d; ++j) {
                if (rotate[j] == 1) ++good;
                else if (rotate[j] == 0) ++soso;
            }
            // 关键的地方
            // (good + soso)表示当前位可选的值 i则表示当前位后面的位数
            // Math.pow(7, i)表示假设数字一共i+1位情况下 在最高位合理的时候后面i位也合理的组合数量
            count += (good + soso) * Math.pow(7, i);
            // 如果diff仍为false(从次高位开始考虑) 需要减去 soso * Math.pow(3, i)
            // 减去的部分表示全部由[0,1,8]构成的数字组合 因为它们只和自身交换 旋转后不会改变原数字 需要舍弃
            // 如果diff为true说明已经存在一个有效的旋转数字 之后的只要是不包含invalid数字的组合相比原数都是发生了变化的
            if (!diff) count -= soso * Math.pow(3, i);
            // 如果这一位的数字是invalid 那么之后的所有都是invalid的 直接return
            if (rotate[d] == -1) return count;
            // 如果有一个digit是旋转后变化的 这个数字就是有差异的
            if (rotate[d] == 1) diff = true;
        }
        return diff && rotate[ds.get(0)] > -1 ? count + 1 : count;
    }


}
