package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个数字集合 输出所有可能的子集 包含空集
 */
public class Problem78 {

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
        for (int i = pos + 1; i <= nums.length; i++) {
            getSubset(i, nums, current, result);
        }
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) {
        Problem78 problem78 = new Problem78();
        List<List<Integer>> result = problem78.subsets(new int[] {1, 2, 3});
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.println(result.get(i).get(j));
            }
        }
    }
}
