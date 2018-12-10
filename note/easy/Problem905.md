## Problem905 Sort Array By Parity

Given an array `A` of non-negative integers, return an array consisting of all the even elements of `A`, followed by all the odd elements of `A`.

You may return any answer array that satisfies this condition.

也就是把偶数放到前面

## 解答

```java
public int[] sortArrayByParity(int[] A) {
	if (A.length == 1 || A == null) {
    	return A;
    }
    // 找到第一个奇数和第一个偶数
    int odd = findNextOdd(A, -1);
    // A中不存在奇数，不需要调换位置
    if (odd == -1) {
    	return A;
    }
    // 在第一个奇数后面找第一个偶数
    int even = findNextEven(A, odd);
    int tempEven = even;
    while (true) {
        // 交换奇偶数
        if (odd < tempEven) {
            swap(A, odd, tempEven);
        }
        // 找下一个奇数
        odd = findNextOdd(A, odd);
        // 找下一个偶数
        tempEven = findNextEven(A, odd);
        if (tempEven == even) {
            break;
        }
        even = tempEven;
    }

    return A;
}

/**
 * 找下一个奇数
 * @param A
 * @param odd
 * @return
 */
public int findNextOdd(int[] A, int odd) {
    for (int i = odd + 1; i < A.length; i++) {
        if (A[i] % 2 != 0) {
            return i;
        }
    }
    return odd;
}

/**
 * 找下一个偶数
 * @param A
 * @param even
 * @return
 */
public int findNextEven(int[] A, int even) {
    for (int i = even + 1; i < A.length; i++) {
        if (A[i] % 2 == 0) {
            return i;
        }
    }
    return even;
}

/**
 * 交换奇数和偶数
 * @param A
 * @param odd
 * @param even
 */
public void swap(int[] A, int odd, int even) {
    int temp = A[odd];
    A[odd] = A[even];
    A[even] = temp;
}
```

代码写的比较复杂，核心思路倒是挺简单：用两个变量odd和even指向奇数和偶数，每一次循环交换。做题的时候考虑的不够仔细，对于`A.length=1`和A全为奇数的情况考虑不当。运行时间也很长，21ms。
## Discuss里的解答
```java
public int[] sortArrayByParity(int[] A) {
    if (A == null)
        return null;
    int[] ret = new int[A.length];
    int k = A.length-1, j=0;
    for (int i=0; i<A.length; i++) {
        if (isEven(A[i])) {
            ret[j++] = A[i];
        } else {
            ret[k--] = A[i];
        }
    }
    return ret;
}

private boolean isEven(int n) {
    return (n & 1) == 0;
}
```

利用新数组来存储结果，遍历的同时分两头存储，妙啊。