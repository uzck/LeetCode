package easy;

import basicstruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem872 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> sequence1 = getLeafSequence(root1);
        List<Integer> sequence2 = getLeafSequence(root2);
        for (int i = 0; i < sequence1.size(); i++) {
            if (sequence1.get(i) != sequence2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getLeafSequence(TreeNode root) {
        ArrayList<Integer> sequence = new ArrayList<>();
        if (root == null) {
            return sequence;
        }
        if (root.left == null && root.right == null) {
            sequence.add(root.val);
            return sequence;
        }
        if (root.left != null) {
            sequence.addAll(getLeafSequence(root.left));
        }
        if (root.right != null) {
            sequence.addAll(getLeafSequence(root.right));
        }
        return sequence;
    }
}
