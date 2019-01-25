package easy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 找出在两个句子中都只出现过一次的词
 */
public class Problem884 {

    public String[] uncommonFromSentences(String A, String B) {
        String[] splitA = A.split(" ");
        String[] splitB = B.split(" ");

        HashMap<String, Integer> store = new HashMap<>();
        for (String s : splitA) {
            if (store.containsKey(s)) {
                store.put(s, store.get(s) + 1);
            } else {
                store.put(s, 1);
            }
        }
        for (String s : splitB) {
            if (store.containsKey(s)) {
                store.put(s, store.get(s) + 1);
            } else {
                store.put(s, 1);
            }
        }
        ArrayList<String> array = new ArrayList<>();
        for (String s : store.keySet()) {
            if (store.get(s) == 1) {
                array.add(s);
            }
        }
        String[] result = new String[array.size()];
        int k = 0;
        for (String s : array) {
            result[k++] = s;
        }
        return result;
    }
}
