package medium;

import java.util.*;

/**
 * Top K Frequent Elements
 * 输入不为空的数字数组 返回k个出现频次最高的数字
 */
public class Problem347 {

    class ValIndex {
        int index;
        int val = Integer.MIN_VALUE;

    }

    /**
     * 傻吊数组存储后排序 复杂度O(NlogN+N+M) M为数组里最大值减最小值
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        ValIndex[] tmp = new ValIndex[max-min+1];
        for (int i = 0; i < nums.length; i++) {
            if (tmp[nums[i]-min] == null) {
                tmp[nums[i]-min] = new ValIndex();
                tmp[nums[i]-min].index = nums[i]-min;
                tmp[nums[i]-min].val = 1;
            } else {
                tmp[nums[i]-min].val += 1;
            }

        }
        int res = 0;
        Arrays.sort(tmp, new Comparator<ValIndex>() {
            @Override
            public int compare(ValIndex o1, ValIndex o2) {
                if (o1 != null && o2 != null) {
                    return o2.val - o1.val;
                }
                if (o1 == null && o2 != null) {
                    return -1;
                }
                if (o2 == null && o1 != null) {
                    return 1;
                }
                if (o1 == null && o2 == null) {
                    return 0;
                }
                return 0;
            }
        });
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (k > 0) {
            if (tmp[i] == null) {
                i++;
            } else {
                ans.add(tmp[i++].index + min);
                k--;
            }
        }

        return ans;
    }

    /**
     * 先用hashmap存储所有值出现的次数
     * 使用最小堆
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequentUsePriorityQueue(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap();
        // 存储数字出现的次数 O(N)
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // 最小堆 排序条件为出现次数
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // 保持堆里面只有k个值 复杂度O(Nlog^k)
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                // 每次的poll操作复杂度为O(log^k)
                heap.poll();
        }

        // 构造输出结果
        List<Integer> top_k = new LinkedList<>();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    /**
     * 桶排序 复杂度O(N)
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequentBukectSort(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 存数字出现结果O(N)
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        // bucket用来存储每个频率的值 最多出现nums.length次 最少0次 所以长度为nums.length + 1
        List<Integer>[] bucket = new List[nums.length+1];
        for(int n:map.keySet()){
            int freq = map.get(n);
            if(bucket[freq]==null)
                bucket[freq] = new LinkedList<>();
            bucket[freq].add(n);
        }

        List<Integer> res = new LinkedList<>();
        // 这里需要注意k > 0的情况 题目没有明确如果两个数出现次数一样要选择哪一个
        // 看discuss里 test case好像没考虑到这样的情况
        // 下面的代码是把出现次数相同的数全部加到结果里了
        // 如果只是选k个的代码应该修改为
//        for (int i = bucket.length-1; i > 0 && k > 0; i--) {
//            if (bucket[i] != null) {
//                List<Integer> list = bucket[i];
//                if (list.size() <= k) {
//                    res.addAll(list);
//                } else {
//                    res.addAll(list.subList(0, k));
//                }
//            }
//        }
        for(int i=bucket.length-1; i>0 && k>0; --i){
            if(bucket[i]!=null){
                List<Integer> list = bucket[i];
                res.addAll(list);
                k-= list.size();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Problem347 problem347 = new Problem347();
        List<Integer> ans = problem347.topKFrequent(new int[] {2,3,4,1,2,1,3,3,3,5,5,5,6,6,6,8}, 3);

    }
}
