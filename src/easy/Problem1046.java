package easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有一堆石头 每个石头有自己的重量(正数)
 * 每次选择最重的两块石头进行撞击 假设两块石头的质量分别为x和y
 * 如果x == y 两个石头都完全破裂
 * 如果x != y 质量较小的完全破裂 大的石头的质量变为 Math.max(x, y) - Math.min(x, y)
 * 循环结束的时候还剩下一块石头 求该石头的质量 (如果没有石头剩余 返回0)
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class Problem1046 {

    /**
     * 每次重排序 数据量小的时候问题不大
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        Arrays.sort(stones);
        if (stones.length == 1) {
            return stones[0];
        }
        if (stones.length == 2) {
            return Math.max(stones[0], stones[1]) - Math.min(stones[0], stones[1]);
        }
        while (stones[stones.length - 1] > 0 && stones[stones.length - 2] > 0) {
            smash(stones);
        }
        return stones[stones.length - 1];

    }

    public void smash(int[] stones) {
        int minWeight = Math.min(stones[stones.length - 1], stones[stones.length - 2]);
        stones[stones.length - 1] -= minWeight;
        stones[stones.length - 2] -= minWeight;
        Arrays.sort(stones);
    }

    /**
     * 用优先队列实现 内部进行了排序 指定Collections.reverseOrder让较大的数在队列前面
     * @param stones
     * @return
     */
    public int lastStoneWeightUsePriorityQueue(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i: stones) pq.offer(i);
        while(pq.size() > 1){
            int x = pq.poll();
            int y = pq.poll();
            int diff = Math.abs(x-y);
            if(diff > 0) pq.offer(diff);
        }
        return pq.size() > 0 ? pq.poll() : 0;
    }

    /**
     * 用最大堆来实现 最大堆忘了怎么实现了,<del>以后再看</del>
     * @param stones
     * @return
     */
    public int lastStoneWeightUseMaxHeap(int[] stones) {
        //build maxHeap
        int n = stones.length;
        for(int i = n/2-1;i>=0;i--){
            heapify(stones, i, n);
        }

        boolean swap = true;
        int i = n-1;

        while(i>=0){
            if(swap){
                int temp = stones[0];
                stones[0] = stones[i];
                stones[i] = temp;
                heapify(stones, 0, i);
                swap = false;
                i--;
            }
            else{
                stones[0] = stones[i+1] - stones[0];
                heapify(stones, 0, i+1);
                swap = true;
            }
        }


        return stones[0];
    }

    public void heapify(int[] arr, int i, int heapSize){
        // max-Heapify
        int largest = i;
        int left = 2*i+1;
        int right  = 2*i+2;

        if(left<heapSize && arr[left]>arr[i]){
            largest = left;
        }
        if(right<heapSize && arr[right]>arr[largest]){
            largest = right;
        }

        if(largest!=i){
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            heapify(arr, largest, heapSize);
        }

    }

    public static void main(String[] args) {
        Problem1046 problem1046 = new Problem1046();
        problem1046.lastStoneWeight(new int[] {2, 7, 4, 1, 8 ,1});
    }
}
