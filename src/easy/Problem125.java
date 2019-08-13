package easy;

/**
 * Valid Palindrome
 * 判断是否为回文 只考虑字母和数字 忽略大小写
 * "A man, a plan, a canal: Panama" 输出为true
 */
public class Problem125 {

    /**
     * 6ms 39.68%
     * 创建新数组存储实际上需要判断的字符 然后双指针遍历
     * @param s
     * @return
     */
    public boolean isPalindromeSlow(String s) {
        int size = 0;
        char[] tmp = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                tmp[size++] = Character.toLowerCase(s.charAt(i));
            }
            if (Character.isDigit(s.charAt(i))) {
                tmp[size++] = s.charAt(i);
            }
        }
        if (size == 0) {
            return true;
        }
        if (size == 1) {
            return true;
        }
        int i = 0, j = size - 1;
        while (i < j) {
            if (tmp[i] != tmp[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 4ms 82.19%
     * 直接双指针 和普通的回文判断类似 额外判断当前字符需不需要判断即可
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] sArray = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < sArray.length && !(Character.isDigit(sArray[i]) || Character.isLetter(sArray[i]))) {
                i++;
            }
            while (j >= 0 && !(Character.isDigit(sArray[j]) || Character.isLetter(sArray[j]))) {
                j--;
            }
            if (i < j) {
                if (Character.toLowerCase(sArray[i]) != Character.toLowerCase(sArray[j])) {
                    return false;
                }
            }
            i++;
            j--;
        }
        return true;
    }

    private static final char[]charMap = new char[256];
    static{
        for(int i=0;i<10;i++){
            charMap[i+'0'] = (char)(1+i);  // numeric
        }
        for(int i=0;i<26;i++){
            charMap[i+'a'] = charMap[i+'A'] = (char)(11+i);  //alphabetic, ignore cases
        }
    }

    /**
     * 1ms 100%
     * 先用char[]把所有可行的字符映射到数字 通过比较数字减少toLowerCase这样的操作 想不到想不到
     * @param s
     * @return
     */
    public boolean isPalindromeFast(String s) {
        char[]pChars = s.toCharArray();
        int start = 0,end=pChars.length-1;
        char cS,cE;
        while(start<end){
            cS = charMap[pChars[start]];
            cE = charMap[pChars[end]];
            if(cS!=0 && cE!=0){
                if(cS!=cE)return false;
                start++;
                end--;
            }else{
                if(cS==0)start++;
                if(cE==0)end--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Problem125 problem125 = new Problem125();
        problem125.isPalindrome(".,");
    }
}

