package easy;

import java.util.HashSet;

/**
 * localname里的.会被忽略
 * localname里的+之后@之前的字符会被忽略
 * email长度1-100
 * emails最多包含100个email
 * 每个email只有一个@
 * 输出: 处理后的emails里不同的email数量
 */
public class Problem929 {

    /**
     *
     * @param emails
     * @return
     */
    public int numUniqueEmails(String[] emails) {
        for (int i = 0; i < emails.length; i++) {
            emails[i] = processEmail(emails[i]);
        }
        HashSet<String> store = new HashSet<>();
        for (String email : emails) {
            store.add(email);
        }
        return store.size();
    }

    /**
     * 对email地址进行处理
     * @param email
     * @return
     */
    public String processEmail(String email) {
        int plusIndex = email.indexOf('+'); // '+'的位置
        int atIndex = email.indexOf('@'); // '@'的位置
        String firstProcess; // 忽略'+'后的部分
        if (plusIndex == -1) {
            // email中没有'+'
            firstProcess = email.substring(0, atIndex);
        } else {
            firstProcess = email.substring(0, plusIndex);
        }
        String secondProcess = firstProcess.replace(".", "");
        return secondProcess + email.substring(atIndex, email.length());
    }

    public static void main(String[] args) {
        String[] emails = new String[] {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        Problem929 problem929 = new Problem929();
        System.out.println(problem929.numUniqueEmails(emails));
    }
 }
