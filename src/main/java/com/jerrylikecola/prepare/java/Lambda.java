package com.jerrylikecola.prepare.java;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaxiang
 * @date 2021/3/9 16:19
 * @description
 */
public class Lambda {

    @Test
    public void test() {
        List<Integer> arr = new ArrayList<>();
        List<List<Integer>> arrList = new ArrayList<>();
        List<Integer> collect = arr.stream().map(o -> o++).collect(Collectors.toList());
        List<Stream<Integer>> collect1 = arrList.stream().map(Collection::stream).collect(Collectors.toList());

    }
}
