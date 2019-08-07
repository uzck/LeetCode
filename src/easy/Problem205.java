package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Isomorphic Strings 同构字符串
 * 输入s和t两个同样长度的字符串 判断是否是同构的
 * 同构的定义： s中的字符串中的字符通过替换可以变为t 但是不同字母的替换选择不能相同
 */
public class Problem205 {

    /**
     * 12ms 36.33% 37.2MB 100%
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphicSlow(String s, String t) {
        // 用来存储相同位置的s和t的字符差值 用s的字符作为索引
        // list里存储s里相同的字符和各自位置上t字符的差值是否一致
        ArrayList<Integer>[] counter = new ArrayList[256];
        int[] sCount = new int[256];
        int[] tCount = new int[256];
        // 统计s和t的字符种类
        int sTypeCount = 0;
        int tTypeCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (counter[sChar] == null) {
                counter[sChar] = new ArrayList<>();
            }
            if (counter[tChar] == null) {
                counter[tChar] = new ArrayList<>();
            }
            if (sCount[sChar] ==  0) {
                sTypeCount += 1;
                sCount[sChar] += 1;
            }
            if (tCount[tChar] == 0) {
                tTypeCount += 1;
                tCount[tChar] += 1;
            }
            counter[s.charAt(i)].add(Math.abs(s.charAt(i) - t.charAt(i)));
        }
        if (sTypeCount != tTypeCount) {
            return false;
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] == null || counter[i].size() == 0) {
                continue;
            }
            int diff = counter[i].get(0);
            for (int diffCount : counter[i]) {
                if (diff != diffCount) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 4ms
     * 映射的思想 把配好对的两个字符制定分配规则
     * 如果下一次有其中的一个字符和另一个字符做替换的时候,检查是否符合分配规则
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic (String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
            // i + 1是一个trick 不一定采用这个数字
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }

    /**
     * 1ms 100%
     * 思想也是自定配对规则 然后再检测是否符合规则
     * 优化的部分是用数组来代替charAt函数 少了index的检查
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphicFast(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        if(a.length!=b.length) return false;
        char[] aa = new char[256];
        char[] bb = new char[256];
        for(int i=0;i<a.length;i++){
            char aaa = a[i];
            char bbb = b[i];
            if(aa[aaa]==0 && bb[bbb]==0){
                aa[aaa] = bbb;
                bb[bbb] = aaa;
            }else{
                if(aa[aaa]!=bbb && bb[bbb]!=aaa) return false;
            }
        }
        return true;
    }

    /**
     * 使用hashmap来代替int[]来存储
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphicHashMap(String s, String t) {
        if(s == null || s.length() <= 1) return true;
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for(int i = 0 ; i< s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            // 1. 检查是否已经给a设定了配对字符
            // 2. 如果已设定了 检查b是否是设定的配对字符 如果否 返回false 是 继续迭代
            // 3. 如果还未给a设定了配对字符 且b也没有设定配对字符 添加 a <--> b规则到hashmap中
            // 4. 如果未给a设定了配对字符 但containsValue(b)为true说明b已经和其他字符配对了 返回false
            if(map.containsKey(a)){
                if(map.get(a).equals(b))
                    continue;
                else
                    return false;
            }else{
                if(!map.containsValue(b))
                    // 添加配对规则
                    map.put(a,b);
                else return false;

            }
        }
        return true;
    }

    public static void main(String[] args) {
        Problem205 problem205 = new Problem205();
        boolean result = problem205.isIsomorphicHashMap("ab", "aa");
        System.out.println(result);
    }
}
