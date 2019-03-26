package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Problem797 {


    /**
     * 用栈存储先前访问的路径
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        boolean[][] vertextVisited = new boolean[graph.length][];
        for (int i = 0; i < graph.length; i++) {
            vertextVisited[i] = new boolean[graph[i].length];
            Arrays.fill(vertextVisited[i], false);
        }
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        boolean hasUnVisitedNode = false;
        List<Integer> temp = new ArrayList<>();
        int peekNode, popNode;
        // 初始化
        for (int i = 0; i < vertextVisited.length; i++) {
            Arrays.fill(vertextVisited[i], false);
        }
        while (!stack.isEmpty()) {
            peekNode = stack.peek();
            if (peekNode == graph.length - 1) {
                for (Integer i : stack) {
                    temp.add(i);
                }
                result.add(new ArrayList<>(temp));
                temp.clear();
                stack.pop();

            } else {
                hasUnVisitedNode = false;
                for (int i = 0; i < vertextVisited[peekNode].length; i++) {
                    if (vertextVisited[peekNode][i] == false) {
                        hasUnVisitedNode = true;
                        vertextVisited[peekNode][i] = true;
                        stack.push(graph[peekNode][i]);
                        break;
                    }
                }
                if (!hasUnVisitedNode) {
                    popNode = stack.pop();
                    for (int i = 0; i < vertextVisited[popNode].length; i++) {
                        vertextVisited[peekNode][i] = false;
                    }
                }
            }
        }

        return result;
    }

    /**
     * 递归dfs
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTargetRecursion(int[][] graph) {
        List<List<Integer>> res = new ArrayList();
        backTracking(res, 0, graph.length, graph, new ArrayList());
        return res;
    }

    void backTracking(List<List<Integer>> res, int currPos, int N,int[][] graph, List<Integer> path) {
        path.add(currPos);
        if (currPos == N - 1) {
            res.add(new ArrayList(path));
            return;
        }

        for (int neighbor : graph[currPos]) {
            backTracking(res, neighbor, N, graph, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Problem797 problem797 = new Problem797();
        int[][] data = new int[][]{new int[]{4,3,1}, new int[]{3,2,4},new int[]{3}, new int[]{4}, new int[]{}};
        List<List<Integer>> result = problem797.allPathsSourceTarget(data);
    }
}
