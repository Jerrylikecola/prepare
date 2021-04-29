package com.jerrylikecola.prepare.suanfa;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xiaxiang
 * @date 2021/3/5 17:26
 * @description
 */
public class LRU {
    private List<Integer> lru;
    private int size;
    private int max = 5;

    public LRU() {
        lru = new LinkedList<>();
        size = 0;
    }

    public void add(int num) {
        boolean isExist = false;
        if (lru.isEmpty()) {
            lru.add(num);
            size++;
        }
        for (int i = 0; i < lru.size(); i++) {
            if (num == lru.get(i)) {
                lru.remove(i);
                lru.add(0,num);
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            if (size > max - 1) {
                lru.remove(size - 1);
                lru.add(0,num);
            }else {
                lru.add(0,num);
                size++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,1,4,5,6};
        LRU lru = new LRU();
        for (int i = 0; i < arr.length; i++) {
            lru.add(arr[i]);
        }
        System.out.println(1);
    }
}
