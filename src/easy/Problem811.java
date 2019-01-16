package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求子域名出现的次数
 */
public class Problem811 {

    public List<String> subdomainVistis(String[] cpdomains) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> storeMap = new HashMap<>();
        String[] split;
        List<String> subDomains;
        for (String domain : cpdomains) {
            split = domain.split(" ");
            subDomains = getSubdomains(split[1]);
            for (String subdomain : subDomains) {
                if (!storeMap.containsKey(subdomain)) {
                    storeMap.put(subdomain, Integer.parseInt(split[0]));
                } else {
                    storeMap.put(subdomain, Integer.parseInt(split[0]) + storeMap.get(subdomain));
                }
            }
        }
        for (String key : storeMap.keySet()) {
            result.add(storeMap.get(key) + " " + key);
        }
        return result;
    }

    /**
     * 获取域名列表 这个地方做法有很多种
     * 可以从前面减 也可以先split完再一个一个加回来
     * @param domain
     * @return
     */
    public List<String> getSubdomains(String domain) {
        List<String> subDomains = new ArrayList<>();
        subDomains.add(domain); // 自身也算
        int start = 0, find = 0;
        while (true) {
            find = domain.indexOf(".", start);
            if (find == -1) {
                break;
            }
            subDomains.add(domain.substring(find + 1));
            start = find + 1;
        }
        for (String s : subDomains) {
            System.out.println(s);
        }
        return subDomains;
    }

    public static void main(String[] args) {
    }
}
