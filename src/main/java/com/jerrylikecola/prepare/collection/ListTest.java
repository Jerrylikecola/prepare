package com.jerrylikecola.prepare.collection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaxiang
 * @date 2021/3/15 15:19
 * @description
 */
public class ListTest {

    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        Class<ArrayList> arrayListClass = ArrayList.class;
        List<Integer> list = arrayListClass.newInstance();
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] arr2 = (Object[]) field.get(list);
            // 现在的list的容量是多少？
            System.out.println(arr2.length);
            // 新增一个值
            list.add(1);
            Object[] arr = (Object[]) field.get(list);
            // 现在的list的容量是多少？
            System.out.println(arr.length);
            List<Integer> addList = new ArrayList<>();
            // addAll一个15大小的
            for (int i = 0; i < 15; i++) {
                addList.add(i);
            }
            list.addAll(addList);
            Object[] arr1 = (Object[]) field.get(list);
            // 现在的list的容量是多少？
            System.out.println(arr1.length);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i)==2){
//                list.remove(i);
//            }
//        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            if (iterator.next()==2){
                list.add(1);
            }
        }
        System.out.println(list.toString());
    }
}
