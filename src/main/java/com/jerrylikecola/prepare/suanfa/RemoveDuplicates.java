package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/4/6 10:32
 * @description 80
 */
public class RemoveDuplicates {

    @Test
    public void test() {
        int[] arr = {1, 1, 1, 2, 2, 3, 3};
        removeDuplicates(arr);
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        return 1;

    }
}
