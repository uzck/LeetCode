# Problem771

## 题目

You're given strings `J` representing the types of stones that are jewels, and `S` representing the stones you have.  Each character in `S` is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in `J` are guaranteed distinct, and all characters in `J` and `S` are letters. Letters are case sensitive, so `"a"` is considered a different type of stone from `"A"`.

## 解答

个人求解方法很笨，用HashMap遍历S，这个过程其实也可以用int[]数组来存储，运行时间11ms

```java
HashMap<Character, Integer> store = new HashMap<>();
for (char c : S.toCharArray()) {
	if (store.get(c) == null) {
    	store.put(c, 1);
    } else if (store.get(c) >= 1) {
    	store.put(c, store.get(c) + 1);
    }
}
```

## Discuss里的解法

[原帖](https://leetcode.com/problems/jewels-and-stones/discuss/113574/1-liners-PythonJavaRuby)，里面有ruby, python, java的一行解, f**k awsome

下面是java版的一行解，运行时间28ms

```java
public int numJewelsInStones(String J, String S) {
    return S.replaceAll("[^" + J + "]", "").length();
}
```

用正则表达式在S中匹配J



```java
public int numJewelsInStones(String J, String S) {
    int res = 0;
    for (char c : S.toCharArray()) {
        if (J.indexOf(c) != -1) {
            res++;
        }
    }
    return res;
}
```

上述解法的思路和我的相反，遍历S，如果S在J中，结果加一。运行时间12ms