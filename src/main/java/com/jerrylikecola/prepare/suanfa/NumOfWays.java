package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaxiang
 * @date 2021/4/2 17:19
 * @description 1411
 */
public class NumOfWays {
    public int numOfWays(int n) {
        if (n == 1) {
            return 12;
        }
        long ABA = 6;
        long ABC = 6;
        long count = 1000000007;
        for (int i = 2; i <= n; i++) {
            long nextABA = (3 * ABA + 2 * ABC) % count;
            long nextABC = (2 * ABA + 2 * ABC) % count;
            ABA = nextABA;
            ABC = nextABC;
        }
        return (int) ((ABA + ABC) % count);
    }

    @Test
    public void test(){
        List<Integer> child = new ArrayList<>();
        child.add(1);
        child.add(2);
        List<List<Integer>> list = new ArrayList<>();
        list.add(child);
        list.add(child);
        List<Integer> collect = list.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collect.toString());
    }
}
