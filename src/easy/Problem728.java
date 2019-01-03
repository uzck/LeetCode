package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * self dividing numbers：指可以被自身的数字整除的数 如128 可以被1、2、8整除
 * 要求：找到范围内所有的self dividing numbers
 */
public class Problem728 {

    public List<Integer> selfDividingNumbersLowVer(int left, int right) {
        String temp;
        boolean isDividingNumber;
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            temp = "" + i;
            isDividingNumber = true;
            for (char c : temp.toCharArray()) {
                if (c == '0') {
                    isDividingNumber = false;
                    break;
                }
                if (i % Integer.parseInt(String.valueOf(c)) != 0) {
                    isDividingNumber = false;
                }
            }
            if (isDividingNumber) {
                result.add(i);
            }

        }
        return result;
    }

    public List<Integer> selfDividingNumberSimpleVer(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for(int i=left;i<=right;i++) {
            int num=i;
            while(num!=0)
            {
                if(num%10==0 || i%(num%10)!=0 )
                    break;
                else
                    num/=10;
            }
            if(num==0)
                result.add(i);
        }
        return result;
    }

    public List<Integer> selfDividingNumber(int left, int right) {
        List<Integer> result = new ArrayList<>();
        int temp, tmp;
        boolean dividingNumber;
        for (int i = left; i <= right; i++) {
            temp = i; // 保存数据
            tmp = i;
            int index = 1;
            for (int j = 1; j <= getMaxNumber(i); j++) {
                index *= 10;
            }
            dividingNumber = true;
            while (index >= 1) {
                if (tmp / index == 0) {
                    dividingNumber = false;
                    break;
                }
                if (!(temp % (tmp / index) == 0)) {
                    dividingNumber = false;
                    break;
                }
                tmp = tmp % index;
                index /= 10;
            }
            if (dividingNumber) {
                result.add(temp);
            }
        }
        return result;
    }

    private int getMaxNumber(int number) {
        int index = 0;
        while (number / 10 > 0) {
            number /= 10;
            index += 1;
        }
        return index;

    }

    public static void main(String[] args) {
        Problem728 problem728 = new Problem728();
        System.out.println(problem728.selfDividingNumber(10, 10).size());
    }
}
