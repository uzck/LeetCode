package easy;

import basicstruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树每一层的均值
 * 每个结点的值的范围是[-2^31,2^31-1] 需要考虑溢出
 */
public class Problem637 {

    /**
     * 超时了 思想是用队列做层次遍历 假设的存储方式为满二叉树 空节点用null表示
     * @param root
     * @return
     */
    public List<Double> averageOfLevelsTLE(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            result.add(0.0);
            return result;
        }
        Queue<TreeNode> levelVal = new LinkedList<>();
        levelVal.add(root);
        int currentLevelCount = 1;
        int nullCount;
        double currentLevelSum;
        while (levelVal.size() > 0) {
            nullCount = 0;
            currentLevelSum = 0.0;
            for (int i = 1; i <= currentLevelCount; i++) {
                TreeNode pollNode = levelVal.poll();
                if (pollNode == null) {
                    levelVal.offer(null);
                    levelVal.offer(null);
                    nullCount += 1;
                } else {
                    currentLevelSum += pollNode.val;
                    levelVal.offer(pollNode.left);
                    levelVal.offer(pollNode.right);
                }
            }
            // 全为null说明是最后一层了
            if (nullCount == currentLevelCount) {
                break;
            }
            result.add(currentLevelSum / (currentLevelCount - nullCount));
            currentLevelCount *= 2;
        }
        return result;
    }

    /**
     * 比上面的方法优化了对null的处理
     * 用currentLevelCount和nextLevelCount两个值来存储本层和下层的实际结点个数
     * 每次循环开始时队列里只有本层的元素 每次出队currentLevelCount个元素
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            result.add(0.);
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 下一层的结点数
        int nextLevelCount;
        // 当前层的结点数
        int currentLevelCount = 1;
        int currentLevelCountCopy;
        double levelSum;
        while (queue.size() > 0) {
            levelSum = 0.;
            nextLevelCount = 0;
            currentLevelCountCopy = currentLevelCount;
            while (queue.size() > 0 && currentLevelCount > 0) {
                TreeNode poll = queue.poll();
                currentLevelCount -= 1;
                if (poll.left != null) {
                    queue.offer(poll.left);
                    nextLevelCount += 1;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    nextLevelCount += 1;
                }
                levelSum += poll.val;
            }
            result.add(levelSum / currentLevelCountCopy);
            currentLevelCount = nextLevelCount;
        }
        return result;
    }

    /**
     * 递归方法
     * @param root
     * @return
     */
    public List<Double> averageOfLevelsRecursive(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if(root==null){
            return result;
        } else {
            List<TreeNode> level = new ArrayList<>();
            level.add(root);
            getSum(result, level);
            return result;
        }
    }

    private void getSum(List<Double> result, List<TreeNode> level){
        double sum =0;
        double count = 0;
        List<TreeNode> nextLevel = new ArrayList<TreeNode>();
        for(TreeNode node:level){
            sum += node.val;
            count ++;
            if(node.left != null){
                nextLevel.add(node.left);
            }
            if(node.right != null){
                nextLevel.add(node.right);
            }
        }
        result.add(sum / count);
        if(nextLevel.size() > 0){
            // 依次递归求解
            getSum(result,nextLevel);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(30);
        root.right.left = new TreeNode(10);
        root.right.left.right = new TreeNode(15);
        root.right.left.right.right = new TreeNode(45);
        Problem637 problem637 = new Problem637();
        problem637.averageOfLevels(root);
    }
}
