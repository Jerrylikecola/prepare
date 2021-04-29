package com.jerrylikecola.prepare.suanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaxiang
 * @date 2021/4/16 16:02
 * @description
 */
public class IsScramble {

    public boolean isScramble(String s1, String s2) {
        if (s1.length() == 1) {
            return true;
        }
        for (int i = 1; i < s1.length(); i++) {
            String childS1L = s1.substring(0, i);
            String childS1R = s1.substring(i);

            String childS2L = s2.substring(0, i);
            String childS2R = s2.substring(i);

            if (compare(childS1L,childS2L)||compare(childS1L,childS2R)){
                return true;
            }
        }
        return false;
    }

    public boolean compare(String s1, String s2) {
        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            s1Map.put(s1.toCharArray()[i], s1Map.getOrDefault(s1.toCharArray()[i], 0) + 1);
            s2Map.put(s2.toCharArray()[i], s2Map.getOrDefault(s2.toCharArray()[i], 0) + 1);
        }
        for (Map.Entry entry:s1Map.entrySet()) {
            if (!s1Map.get(entry.getKey()).equals(s2Map.get(entry.getKey()))){
                return false;
            }
        }
        return true;
    }
}
