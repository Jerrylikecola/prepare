package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaxiang
 * @date 2021/4/1 16:06
 * @description 1006
 */
public class Clumsy {
    @Test
    public void test() {
        System.out.println(clumsy(10));
    }

    public int clumsy(int N) {
        int last = N % 4;
        int lastResult = 0;
        if (last == 1) {
            lastResult = 1;
        } else if (last == 2) {
            lastResult = 2;
        } else if (last == 3) {
            lastResult = 6;
        }
        if (N < 4) {
            return lastResult;
        }
        List<List<Integer>> resultList = new ArrayList<>();
        int count = 0;
        List<Integer> child = new ArrayList<>();
        for (int i = N; i > last; i--) {
            child.add(i);
            count++;
            if (count == 4) {
                count = 0;
                resultList.add(new ArrayList<>(child));
                child = new ArrayList<>();
            }
        }
        int result = resultList.get(0).get(0) * resultList.get(0).get(1) / resultList.get(0).get(2) + resultList.get(0).get(3);
        for (int i = 1; i < resultList.size(); i++) {
            result = result - (resultList.get(i).get(0) * resultList.get(i).get(1) / resultList.get(i).get(2) - resultList.get(i).get(3));
        }

        return result - lastResult;
    }

}
