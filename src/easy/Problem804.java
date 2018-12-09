package easy;

import java.util.HashSet;

public class Problem804 {

    String[] mapping = new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

    public int uniqueMorseRepresentations(String[] words) {
        for (int i = 0; i < words.length; i++) {
            words[i] = encode(words[i]);
        }
        HashSet<String> store = new HashSet<>();
        for (String word : words) {
            store.add(word);
        }
        return store.size();
    }

    /**
     * 摩斯编码
     * @param word
     * @return
     */
    public String encode(String word) {
        StringBuilder builder = new StringBuilder();
        for (char c : word.toCharArray()) {
            builder.append(mapping[c - 'a']);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] words = new String[] {"gin", "zen", "gig", "msg"};
        Problem804 problem804 = new Problem804();
        System.out.println(problem804.uniqueMorseRepresentations(words));
    }
}
