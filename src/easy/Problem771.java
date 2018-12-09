package easy;

import java.util.HashMap;

/**
 * J: jewel种类
 * S: 拥有的stone
 * 问题求解手里多少stone是jewel
 */
public class Problem771 {

    /**
     * 问题简化为为J中不同种类字符在S中出现的次数和
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        if (J == null || J.equals("") || S == null || S.equals("")) {
            return 0;
        }
        HashMap<Character, Integer> store = new HashMap<>();
        for (char c : S.toCharArray()) {
            if (store.get(c) == null) {
                store.put(c, 1);
            } else if (store.get(c) >= 1) {
                store.put(c, store.get(c) + 1);
            }
        }
        int sum = 0;
        for (char c : J.toCharArray()) {
            if (store.get(c) != null) {
                sum += store.get(c);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        String J = "z";
        String S = "ZZ";
        Problem771 problem771 = new Problem771();
        System.out.println(problem771.numJewelsInStones(J, S));
    }
}
