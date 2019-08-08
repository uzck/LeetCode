package easy;

import java.util.HashMap;

/**
 * Distribute Candies
 *
 */
public class Problem575 {

    public int distributeCandies(int[] candies) {
        // hashset更快
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < candies.length; i++) {
            if (!map.containsKey(candies[i])) {
                map.put(candies[i], true);
            }
        }
        int type = 0;
        for (Integer key : map.keySet()) {
            type += 1;
        }
        // java8写法
//        return Math.min((int)Arrays.stream(candies).boxed().distinct().count(), candies.length/2);
        return Math.min(type, (candies.length / 2));
    }

    public int distributeCandiesArray(int[] candies) {
        int len = candies.length;
        if (len == 0) return 0;
        int half = len / 2;

        // 种类有上限 [-100000, 100000]
        byte[] kinds = new byte[200001];
        int count = 0;

        for (int kind : candies) {
            count += 1 - kinds[kind + 100000];
            kinds[kind + 100000] = 1;
            if (count == half) break;
        }

        return count;
    }
}
