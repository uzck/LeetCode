package medium;

import java.util.*;

/**
 * 输入一个数字集合 输出所有可能的子集 包含空集
 */
public class Problem78 {

    /**
     * 回溯法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            getSubset(i, nums, current, result);
        }
        result.add(new ArrayList<>());
        return result;
    }

    public void getSubset(int pos, int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (pos == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[pos]);
        // 注意这个 <= 而不是 <
        for (int i = pos + 1; i <= nums.length; i++) {
            getSubset(i, nums, current, result);
        }
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) {
        Problem78 problem78 = new Problem78();
        List<List<Integer>> result = problem78.subsetsBFS(new int[] {1, 2, 3});
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.println(result.get(i).get(j));
            }
        }
    }

    /**
     * BFS
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsBFS(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) return result;

        Arrays.sort(nums);

        // list记录组合结果 queue用来做bfs
        List<Integer> list = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        result.add(list);
        queue.offer(list);

        //bfs
        while(queue.size() > 0){
            List<Integer> curr = queue.poll();
            for(int i = 0; i < nums.length; i++){

                // 跳过比当前值小的 因为已经在之前添加过了
                if(curr.size() > 0 && nums[i] <= curr.get(curr.size()-1)) continue;

                List<Integer> newList = new ArrayList<>(curr);
                newList.add(nums[i]);
                result.add(newList);
                queue.offer(newList);
            }
        }
        return result;
    }

    private void addToList(List<List<Integer>> li, int num){
        int size=li.size();
        for(int i=0;i<size;i++){
            List newList=new ArrayList<Integer>(li.get(i));
            newList.add(num);
            li.add(newList);
        }
    }

    /**
     * 从最短的结果开始构建
     * 首先powerSet中依次添加[nums[i]]
     * 下一次的时候遍历整个powerSet给所有的list加上当前的nums[j]作为新的结果添加到powerSet中
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsAddToList(int[] nums) {
        List<List<Integer>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<Integer>()); //adding empty set
        for(int i=0;i<nums.length;i++){
            addToList(powerSet,nums[i]);
        }
        return powerSet;
    }
}
