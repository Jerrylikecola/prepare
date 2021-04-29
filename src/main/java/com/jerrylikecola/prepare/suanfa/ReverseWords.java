package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author xiaxiang
 * @date 2021/3/23 16:25
 * @description 剑指offer 58
 */
public class ReverseWords {

    @Test
    public void test() {
        System.out.println(reverseWords(""));
    }

    public String reverseWords(String s) {
        String[] strArr = s.split(" ");
        if ("".equals(s)||strArr.length == 0){
            return "";
        }
        strArr[0] = strArr[0].replaceAll(" ", "");
        strArr[strArr.length - 1] = strArr[strArr.length - 1].replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = strArr.length; i > 0; i--) {
            if ("".equals(strArr[i-1])){
                continue;
            }
            sb.append(strArr[i-1]);
            sb.append(" ");
        }
        String result = sb.toString();
        return result.substring(0,result.length()-1);
    }
}
