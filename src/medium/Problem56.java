package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并间隔
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出:[[1,6],[8,10],[15,18]]
 */
public class Problem56 {

    /**
     * 先对数组按i[0]排序,再按i[1]排序
     * @param intervals
     * @return
     */
    public int[][] mergeSort(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][] {};
        }
        // 数组排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        int n = intervals.length - 1;
        ArrayList<Integer[]> resList = new ArrayList<>();
        for (int i = 0; i < intervals.length - 1; i++) {
            // 比如[1,3]和[2,6]
            // 重叠的定义是 2 <= 3 && 2 >= 1 因为之前根据i[0]排序了 所以不会出现2在1前面的情况
            if (intervals[i+1][0] <= intervals[i][1] && intervals[i+1][0] >= intervals[i][0]) {
                // 根据排序的结果 intervals[i][0] <= intervals[i+1][0]
                int mergeLess = intervals[i][0];
                // 排序不能保证两个数组的最大值落在同一个位置 所以用max求
                int mergeGreater = Math.max(intervals[i][1], intervals[i+1][1]);
                // 把前后两个数组都赋值为合并后的数组
                intervals[i][1] = mergeGreater;
                intervals[i+1][0] = mergeLess;
                intervals[i+1][1] = mergeGreater;
            } else {
                // 如果该间隔无法和下个间隔合并 添加到返回结果
                resList.add(new Integer[] {intervals[i][0], intervals[i][1]});
            }
        }
        // 处理最后一个数据

        resList.add(new Integer[] {intervals[n][0], intervals[n][1]});


        int[][] res = new int[resList.size()][];
        int index = 0;
        for (Integer[] i : resList) {
            res[index++] = new int[] {i[0], i[1]};
        }
        return res;
    }

    /**
     * 1ms 100%
     * discuss里不用排序的方法 空间复杂度O(n) 时间复杂度O(n)
     * 理解不能..
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // 找到间隔里最小的start的最大的end
        for (int i = 0; i < intervals.length; i++) {
            min = Math.min(min, intervals[i][0]);
            max = Math.max(max, intervals[i][1]);
        }


        // open存储每个间隔的start出现次数
        // closed存储每个end出现次数
        int[] open = new int[max-min+1];
        int[] closed = new int[max-min+1];

        for (int i = 0; i < intervals.length; i++) {
            open[intervals[i][0]-min]++;
            closed[intervals[i][1]-min]++;
        }

        int cur_open = 0;
        int cur_closed = 0;
        int merged_open = -1;

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < open.length; i++) {
            // 最小的start
            if (merged_open == -1 && cur_open == 0 && open[i] > 0) merged_open = i;

            // cur_open 当前间隔的start值出现次数
            cur_open += open[i];
            // cur_closed 当前间隔的end值出现次数
            cur_closed += closed[i];

            // cur_open == cur_closed 表示间隔闭合
            // 此时 merged_open + min 是最小的start
            // i + min 是当前end的值
            if (cur_open == cur_closed && cur_open > 0) {
                res.add(new int[] {merged_open+min, i+min});
                merged_open = -1;
                cur_open = 0;
                cur_closed = 0;
            }
        }

        int[][] res_arr = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            res_arr[i] = res.get(i);
        }

        return res_arr;
    }

    /**
     * 排序法的简化
     * @param intervals
     * @return
     */
    public int[][] mergeSimple(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[4][2];
        matrix[0] = new int[] {1, 3};
        matrix[1] = new int[] {2, 6};
        matrix[2] = new int[] {8, 10};
        matrix[3] = new int[] {15, 18};
        Problem56 problem56 = new Problem56();
        problem56.merge(matrix);
    }
}
