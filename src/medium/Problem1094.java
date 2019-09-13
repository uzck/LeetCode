package medium;

import java.util.*;

/**
 * Car Pooling
 * 驾驶一辆汽车 有capacity个空座 车子只能往东开
 * 输入trips, trip[i] = [乘客数量, 起始点, 终点]
 * trips的长度 <= 1000  1 <= capacity <= 100000
 * 判断能否完成所有trips
 */
public class Problem1094 {

    /**
     * 30ms 29.94%
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPoolingSlow(int[][] trips, int capacity) {
        // 对数据先进行排序 上车站越小越靠前 如果同样上车站 先下车的排前面
        Arrays.sort(trips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[2] - o2[2];
                }
            }
        });
        // 存储已经上车的不同批次的人的下车站和上车的人数
        Queue<Integer[]> endQueue = new LinkedList<>();
        // 存储当前剩余的座位
        int currentCapacity = capacity;
        for (int i = 0; i < trips.length; i++) {
            // 先让乘客下车
            int queueSize = endQueue.size();
            while (queueSize-- > 0) {
                Integer[] poll = endQueue.poll();
                // 如果该批次的人下车站小于等于当前站点 该批乘客下车
                if (poll[0] <= trips[i][1]) {
                    // 增加当前座位
                    currentCapacity += poll[1];
                } else {
                    // 把该批次的数据放回队列中
                    endQueue.offer(poll);
                }
            }
            // 如果空余的位置不够乘客坐 返回false
            if (currentCapacity < trips[i][0]) {
                return false;
            }
            // 添加该批次乘客的信息
            endQueue.offer(new Integer[] { trips[i][2], trips[i][0] });
            // 更新剩余空座
            currentCapacity -= trips[i][0];
        }
        return true;
    }

    /**
     * 6ms 66.31% 和上面的思路差不多
     * 用优先队列来代替普通队列 减少重复插入数据
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPoolingPriorityQueue(int[][] trips, int capacity) {
        // 对数据先进行排序 上车站越小越靠前 如果同样上车站 先下车的排前面
        Arrays.sort(trips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[2] - o2[2];
                }
            }
        });
        // 存储已经上车的不同批次的人的下车站和上车的人数
        PriorityQueue<Integer[]> endQueue = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0] - o2[0];
            }
        });
        // 存储当前剩余的座位
        int currentCapacity = capacity;
        for (int i = 0; i < trips.length; i++) {
            // 先让乘客下车
            while (!endQueue.isEmpty() && endQueue.peek()[0] <= trips[i][1]) {
                Integer[] poll = endQueue.poll();
                currentCapacity += poll[1];
            }
            // 如果空余的位置不够乘客坐 返回false
            if (currentCapacity < trips[i][0]) {
                return false;
            }
            // 添加该批次乘客的信息
            endQueue.offer(new Integer[] { trips[i][2], trips[i][0] });
            // 更新剩余空座
            currentCapacity -= trips[i][0];
        }
        return true;
    }

    /**
     * 1ms 99.56%
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // 存储一个站上车和下车的人数只差
        int stops[] = new int[1001];
        for (int t[] : trips) {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }
        // 遍历完毕的时候如果capacity >= 0说明能完成
        for (int i = 0; capacity >= 0 && i < 1001; ++i) capacity -= stops[i];
        return capacity >= 0;
    }

    public static void main(String[] args) {
        int[][] trips = new int[][] {new int[] {9, 3, 4}, new int[] {9, 1, 7}, new int[] {4, 2, 4}, new int[] {7, 4, 5}};
        Problem1094 problem1094 = new Problem1094();
        boolean res = problem1094.carPoolingSlow(trips, 23);
        System.out.println(res);
    }
}
