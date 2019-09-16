package medium;

import java.util.*;

/**
 * Queue Reconstruct By Height
 * 输入为一个随机数组 int[][] 存储的数据为(h, k) h代表一个人的身高 k代表一个人前面大于等于他身高的人数
 * 输出排序后的结果
 *
 */
public class Problem406 {

    /**
     * 411ms 5.11% ...
     * 思路: 按照插入的方法来做
     * 首先对数据进行排序 按照身高从高到低 身高一样 前面人数少的靠前
     * 迭代的时候 每个元素只需要找到自己在当前队列里的位置 然后插入该位置
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        // 排序 身高高的在前面 身高相同的情况下 前面人少的在前面
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        LinkedList<Integer[]> res = new LinkedList<>();
        for (int k = 0; k < people.length; k++) {
            Integer[] poll = new Integer[] {people[k][0], people[k][1]};
            int offset = poll[1];
            int height = poll[0];
            if (res.size() == 0) {
                res.add(poll);
            } else {
                if (offset == 0) {
                    res.add(0, poll);
                    continue;
                }
                for (int i = 0; i < res.size() && offset > 0; i++) {
                    if (res.get(i)[0] >= height) {
                        offset -= 1;
                        if (offset == 0) {
                            res.add(i + 1, poll);
                            break;
                        }
                    }
                }
                if (offset > 0) {
                    res.add(poll);
                }

            }
        }
        int[][] resArray = new int[res.size()][2];
        int i = 0;
        for (Integer[] tmp : res) {
            resArray[i++] = new int[] {tmp[0], tmp[1]};
        }
        return resArray;
    }

    /**
     * 上面方法的优化
     * 1. 直接确认插入的位置 不需要再用一个循环 因为数据从大到小排过序的 所以现有的数据一定是大于等于自身的
     * 2. 原来List里面可以放int... 省去了转换
     * @param people
     * @return
     */
    public int[][] reconstructQueueOptimize(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        List<int []> result = new ArrayList<>();

        for(int i=0;i< people.length; i++) {
            result.add(people[i][1], people[i]);
        }

        return result.toArray(new int[people.length][]);
    }

    public static void main(String[] args) {
        Problem406 problem406 = new Problem406();
        int[][] data = new int[6][2];
        data[0] = new int[] {7, 0};
        data[1] = new int[] {4, 4};
        data[2] = new int[] {7, 1};
        data[3] = new int[] {5, 0};
        data[4] = new int[] {6, 1};
        data[5] = new int[] {5, 2};
        problem406.reconstructQueue(data);
    }
}
