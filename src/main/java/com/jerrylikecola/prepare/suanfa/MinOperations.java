package com.jerrylikecola.prepare.suanfa;

import lombok.ToString;
import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/4/25 16:45
 * @description
 */
@ToString
public class MinOperations {
    @Test
    public void test(){
        System.out.println(minOperations("110"));
    }
    public int[] minOperations(String boxes) {
        char[] chars = boxes.toCharArray();
        int[] arr = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int result = 0;
            for (int j = 0; j < chars.length; j++) {
                if (j != i && chars[j] != '0') {
                    result = result + (i - j < 0 ? j - i : i - j);
                }
            }
            arr[i] = result;
        }
        return arr;
    }
}
