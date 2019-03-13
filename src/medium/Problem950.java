package medium;

import java.util.*;

/**
 * 输入是一个乱序的数组
 * 按照游戏规则使输出有序
 */
public class Problem950 {


    /**
     * 这个解法是找规律
     * [2,13,3,11,5,17,7]里可以看出来第一轮2 3 5 7的位置是隔了一个的
     * 第二轮开始时,从7的位置开始继续往右数两个(没有被访问过的)(如果超出长度则回到开头)
     * 这时应该指向现在11的位置,再继续第三轮从11开始往右数两个,在13的位置
     * 因此将原始数据从小到大排序后，利用这个规律可以对数组重排
     * 这里利用set来存储数据是否被访问过
     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasing(int[] deck) {
       int[] result = new int[deck.length];
        Set<Integer> revealSet = new HashSet<>();
       Arrays.sort(deck);

       int deckIndex = 0, resultIndex = 0;
       int count = deck.length;
       int temp;

       while (count > 0) {
           temp = 2;
           // 如果这个数还没被访问
           if (!revealSet.contains(deck[deckIndex])) {
               result[resultIndex] = deck[deckIndex];
               revealSet.add(deck[deckIndex]);
               deckIndex += 1;
               count -= 1;
               if (count == 0) {
                   break;
               }
               while (temp > 0) {
                   resultIndex = (resultIndex + 1) % result.length;
                   if (revealSet.contains(result[resultIndex])) {
                       continue;
                   } else {
                       temp -= 1;
                   }
               }
           }
       }
       return result;
    }

    /**
     * 讨论区的一种思路：反推法
     * https://leetcode.com/problems/reveal-cards-in-increasing-order/discuss/244441/Easy-solution-with-queue-by-reverse-engineering-beats-99.83
     * 假设最后两个数是13和17
     * 可以知道最后一个是17,把循环往前反推(把牌堆最上方的要放到底部)
     * 所以原来是17,13的顺序时,把17放到底部 再翻的就是13了
     * 逐个往前递推 当只剩一张牌没有放置的时候 直接放在顶部
     * @param deck
     * @return
     */
    public int[] reorder(int[] deck) {
        Arrays.sort(deck);

        if(deck.length < 3) {
            return deck;
        }

        Queue<Integer> queue = new LinkedList<Integer>();

        for(int i=deck.length-1; i>=1; i--) {
            queue.add(deck[i]);
            queue.add(queue.remove());
        }

        queue.add(deck[0]);

        for(int i=deck.length-1; i>=0; i--) {
            deck[i] = queue.remove();
        }
        return deck;
    }

    public static void main(String[] args) {
        Problem950 problem950 = new Problem950();
        int[] data = new int[] {17, 13, 11, 2, 3, 5, 7};
        int[] result = problem950.reorderFast(data);
        for (int i : result) {
            System.out.println(i);
        }
    }

    /**
     * 2ms 评论里找到的最快的方法
     * 思路大致上与用队列的方法一样是反推
     * 但是这里用数组来模拟了队列 操作上更快
     * @param deck
     * @return
     */
    public int[] reorderFast(int[] deck) {
        Arrays.sort(deck);
        int[] result=new int[deck.length*2];
        int C=deck.length;
        int R=result.length;
        if(R<=2)
            return deck;
        result[R-1]=deck[--C];
        result[R-2]=deck[--C];
        int end=0;
        for(int i=result.length-4;i>=2;i=i-2)
        {
            end=result[--R];
            result[i]=deck[--C];
            result[i+1]=end;
        }
        for(int i=2;i<deck.length+2;i++)
        {
            deck[i-2]=result[i];
        }
        return deck;
    }

}
