package medium;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Clumsy Factorial
 * 把原来的*分别换成* / + -
 * * /在+ -前面
 * 除法向下取整 这个在java里可以直接用/
 */
public class Problem1006 {

    /**
     * 把中缀表达式转换为后缀表达式 并且计算后缀表达式
     * @param N
     * @return
     */
    public int clumsy(int N) {
        Stack<Character> operationStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        int circulation = 0;
        ArrayList<Character> operations = new ArrayList<>();
        operations.add('*');
        operations.add('/');
        operations.add('+');
        operations.add('-');

        for (int i = N; i >= 1; i--) {
            numStack.push(i);
            if (i == 1) {
                break;
            }
            if (operationStack.isEmpty()) {
                operationStack.push(operations.get(circulation));
            } else {
                // 当前操作数为+ -
                if (circulation > 1) {
                    while (!operationStack.isEmpty()) {
                        numStack.push(numOperation(operationStack, numStack));
                    }
                    operationStack.push(operations.get(circulation));
                } else if (circulation == 1) {
                    while (!operationStack.isEmpty() && operations.indexOf(operationStack.peek()) == 0) {
                        numStack.push(numOperation(operationStack, numStack));
                    }
                    operationStack.push(operations.get(circulation));
                } else if (circulation == 0) {

                    operationStack.push(operations.get(circulation));
                }
            }

            circulation = (circulation + 1) % 4;
        }
        while (!operationStack.isEmpty()) {
            while (!operationStack.isEmpty() && !numStack.isEmpty()) {
                numStack.push(numOperation(operationStack, numStack));

            }
        }

        return numStack.pop();
    }



    public int numOperation(Stack<Character> operationStack, Stack<Integer> numStack) {
        char operation = operationStack.pop();
        if (numStack.capacity() < 2) {
            return -1;
        }
        int pop1 = numStack.pop();
        int pop2 = numStack.pop();
        switch (operation) {
            case '+':
                return pop1 + pop2;
            case '-':
                return pop2 - pop1;
            case '*':
                return pop1 * pop2;
            case '/':
                return pop2 / pop1;
        }
        return -1;
    }

    /**
     * 两个思路都是找规律 发现4个数一组
     * -(5 * 4 / 3) + 2 = -4
     * -(6 * 5 / 4) + 3 = -4
     * -(7 * 6 / 5) + 4 = -4
     * -(8 * 7 / 6) + 5 = -4
     * -(125 * 124 / 123) + 122 = -4
     * -(997 * 996 / 995) + 994 = -4
     * https://leetcode.com/problems/clumsy-factorial/discuss/257935/This-is-Math-problem-no-loop%3A-O(1)-time-and-O(1)-space%3A-36ms.-Python3.
     *
     * https://leetcode.com/problems/clumsy-factorial/discuss/252279/You-never-think-of-this-amazing-O(1)-solution
     * 在i >= 5的时候
     * i * (i-1) / (i-2) = i + 1
     *     i * (i-1) / (i-2) + (i-3) - (i-4) * (i-5) / (i-6) + (i-7) - (i-8) * .... + rest elments
     * =   (i+1) + "(i-3)" - "(i-4) * (i-5) / (i-6)" + "(i-7)" - "(i-8) * " .... + rest elments
     * =   (i+1) + "(i-3) - (i-3)" + "(i-7) - (i-7)" +  ....  + rest elments
     * =   (i+1) + rest elments
     * when 0 element left: final result is (i+1) + ... + 5 - (4*3/2) + 1, which is i+1
     * when 1 element left: final result is (i+1) + ... + 6 - (5*4/3) + 2 - 1, which is i+2
     * when 2 element left: final result is (i+1) + ... + 7 - (6*5/4) + 3 - 2 * 1, which is i+2
     * when 3 element left: final result is (i+1) + ... + 8 - (7*6/5) + 4 - 3 * 2 / 1, which is i-1
     * @param N
     * @return
     */
    public int awesomeResult(int N) {
        if (N <= 2) {
            return N;
        }
        if (N <= 4) {
            return N + 3;
        }
        if ((N - 4) % 4 == 0) {
            return N + 1;
        } else if ((N - 4) % 4 <= 2) {
            return N + 2;
        } else {
            return N - 1;
        }
    }

    /**
     * 递归法
     * @param N
     * @param cnt
     * @return
     */
    public int recursion(int N, int cnt) {
        int highOrder = N;
        int subResult = 0;

        if(N-1 > 0) {
            highOrder *= (N-1);
        }

        if(N-2 > 0) {
            highOrder /= (N-2);
        }

        if(cnt >= 1) {
            highOrder *= -1;
        }

        subResult += highOrder;

        if(N-3 > 0) {
            subResult += (N-3);
        }


        if(N-4 > 0) {
            subResult += recursion(N-4, cnt+1);
        }

        return subResult;
    }

    public static void main(String[] args) {
        Problem1006 problem1006 = new Problem1006();
        System.out.println(problem1006.awesomeResult(6));
    }
}
