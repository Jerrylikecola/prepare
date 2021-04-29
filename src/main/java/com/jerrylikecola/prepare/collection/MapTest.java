package com.jerrylikecola.prepare.collection;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author xiaxiang
 * @date 2021/3/15 16:34
 * @description
 */
public class MapTest {
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put(null, null);
    }

    @Test
    public void TreeMap() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collections.sort(list, (o1, o2) -> o2 - o1);
        list.forEach(o->{
            System.out.print(o+" ");
        });
    }

    @Test
    public void testLinkedHashMap(){
        LinkedHashMap map = new LinkedHashMap(4,0.75f,true);
        map.put(1,"www");
        map.put(2,"111");
        map.put(3,"111");
        System.out.println(map.toString());
        map.get(2);
        System.out.println(map.toString());
    }
}
