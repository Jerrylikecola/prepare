package com.jerrylikecola.prepare.suanfa;

import java.util.Iterator;

/**
 * @author xiaxiang
 * @date 2021/3/23 15:35
 * @description 341
 */
public class NestedIterator implements Iterator<Integer> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Integer next() {
        return null;
    }

    public boolean isPowerOfFour(int n) {
        if (n < 1) {
            return false;
        }
        while (true) {
            if (n == 1) {
                return true;
            }
            if (n % 4 != 0) {
                return false;
            }
            n = n / 4;
        }
    }
}
