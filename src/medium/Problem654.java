package medium;

import basicstruct.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return new SegmentTree(nums).buildSegmentTree(0, nums.length - 1);
    }

    class SegmentTree {
        private int[] source;
        private int length;

        private int findMaxValueInSegment(int start, int end) {
            int max = Integer.MIN_VALUE, maxIndex = -1;
            for (int i = start; i <= end; i++) {
                if (source[i] > max) {
                    max = source[i];
                    maxIndex = i;
                }
            }
            return maxIndex;

        }

        public int getLength() {
            return length;
        }

        public SegmentTree(int[] source) {
            this.source = source;
            length = source.length;
        }

        public TreeNode buildSegmentTree(int start, int end) {
            if (start > end) {
                return null;
            }
            int maxIndex = findMaxValueInSegment(start, end);
            TreeNode temp = new TreeNode(source[maxIndex]);
            if (start == end) {
                return temp;
            }
            // int mid = (start + end) / 2;
            temp.left = buildSegmentTree(start, maxIndex - 1);
            temp.right = buildSegmentTree(maxIndex + 1, end);
            return temp;
        }
    }

    //递归会报stackoverflow
    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode root;
        int maxIndex = -1, max = Integer.MIN_VALUE;

        // find max
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                maxIndex = i;
                max = nums[i];
            }
        }
        root = new TreeNode(nums[maxIndex]);
        TreeNode left = constructMaximumBinaryTree(nums, start, maxIndex - 1);
        TreeNode right = constructMaximumBinaryTree(nums, maxIndex +  1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        int[] source = new int[] {3, 2, 1, 6, 0, 5};
        Problem654 problem654 = new Problem654();
        System.out.println(problem654.constructMaximumBinaryTree(source).val);
    }
}
