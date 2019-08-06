package easy;

import basicstruct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否对称
 */
public class Problem101 {

    /**
     * 检查每一层的元素是否对称
     * @param levelVals
     * @return
     */
    public boolean checkLevelSymmetric(TreeNode[] levelVals) {
        int start = 0;
        int end = levelVals.length - 1;
        while (start < end) {
            // 两者都不为null且值不相等
            if (levelVals[start] != null && levelVals[end] != null) {
                if (levelVals[start].val != levelVals[end].val) {
                    return false;
                }
                start++;
                end--;
                continue;
            }
//            if ((levelVals[start] == null && levelVals[end] != null) || (levelVals[start] != null && levelVals[end] == null)) {
//                return false;
//            }
            if (levelVals[start] != levelVals[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean checkAllNull(TreeNode[] current) {
        for (TreeNode node : current) {
            if (node != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 23ms 5.26% 。菜到不知道说啥
     * @param root
     * @return
     */
    public boolean isSymmetricVerySlow(TreeNode root) {
        TreeNode[] current;
        TreeNode[] children;
        // 如果二叉树为空
        if (root == null) {
            return true;
        }
        int currentLevelNodeCount = 1;
        current = new TreeNode[1];
        current[0] = root;
        while (true) {
            children = new TreeNode[currentLevelNodeCount * 2];
            int childrenIndex = 0;
            for (int i = 0; i < current.length; i++){
                TreeNode tmpNode = current[i];
                if (tmpNode == null) {
                    children[childrenIndex++] = null;
                    children[childrenIndex++] = null;
                } else {
                    children[childrenIndex++] = tmpNode.left;
                    children[childrenIndex++] = tmpNode.right;
                }
            }
            boolean checkResult = checkLevelSymmetric(current);
            System.out.println("level: " + currentLevelNodeCount + " " + String.valueOf(checkResult));
            if (!checkResult) {
                return false;
            }
            currentLevelNodeCount *= 2;
            current = children;
            if (checkAllNull(children)) {
                break;
            }
        }

        return true;

    }

    /**
     * 利用一倍的存储空间简化对"对称"的判断
     * 存储的时候存储两倍的子节点 同样的节点按照t1.left, t2.right, t1.right, t2.left来存储
     * @param root
     * @return
     */
    public boolean isSymmetricIterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            // 都为null
            if (t1 == null && t2 == null) continue;
            // 其中一个为null
            if (t1 == null || t2 == null) return false;
            // 不为null但是值不相等
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    /**
     * 递归解法 也是用两个树进行比较
     * @param root
     * @return
     */
    public boolean isSymmetricRecursive(TreeNode root) {
        return isMirror(root, root);
    }
}
