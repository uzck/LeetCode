package easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 输入一个string[] dict和一个模板字符串 输出dict中最符合模板的词(符合指模板中出现的英文字母在单词中全部出现)
 * 题目说明了一定有解 如果出现多个长度相等的可能的结果 取单词最早出现的那个
 */
public class Problem748 {

    /**
     * 9ms 32.44%
     * 很常规的想法
     * 用int[]存储字母出现的次数 遍历words 找到符合条件的 并根据最小距离保留返回结果
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] maps = new int[26];
        int minDist = Integer.MAX_VALUE;
        String res = "";
        // 拿到所有
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                maps[Character.toLowerCase(c) -'a'] += 1;

            }
        }
        int[] copy;
        for (String word : words) {
            copy = Arrays.copyOf(maps, maps.length);
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    if (copy[Character.toLowerCase(c)-'a'] > 0) {
                        copy[Character.toLowerCase(c)-'a'] -= 1;
                    }
                }
            }
            boolean complete = true;
            for (int i = 0; i < 26; i++) {
                if (copy[i] != 0) {
                    complete = false;
                    break;
                }
            }
            if (complete) {
                if (word.length() < minDist) {
                    res = word;
                    minDist = word.length();
                }
            }
        }
        return res;
    }

    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

    /**
     * 讨论区的解
     * 用质数表示模板中的字母
     * 计算模板乘积 product
     * 那这个表计算word的值 wordProduct 如果 wordProduct % product == 0说明包含了所有模板
     * 问题也有 如果word为zzz....zzz这样的很容易溢出 但还是很妙的想法 利用了质数只能被1和自身整除的性质
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWordUsePrime(String licensePlate, String[] words) {
        long charProduct = getCharProduct(licensePlate.toLowerCase());
        String shortest = "aaaaaaaaaaaaaaaaaaaa"; // 16 a's
        for(String word : words)
            if (word.length() < shortest.length() && getCharProduct(word) % charProduct == 0)
                shortest = word;
        return shortest;
    }

    private long getCharProduct(String plate) {
        long product = 1L;
        for(char c : plate.toCharArray()) {
            int index = c - 'a';
            if (0 <= index && index <= 25)
                product *= primes[index];
        }
        return product;
    }

    public static void main(String[] args) {
        Problem748 problem748 = new Problem748();
        problem748.shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"});
    }
}
