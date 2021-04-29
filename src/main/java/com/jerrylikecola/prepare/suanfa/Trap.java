package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/4/2 15:21
 * @description mianshi 17.21
 */
public class Trap {

    @Test
    public void test() {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr));
    }

    public int trap(int[] height) {
        if (height.length==0){
            return 0;
        }
        int[] leftHigh = new int[height.length];
        int[] rightHigh = new int[height.length];
        int lMax = height[0];
        int rMax = height[height.length - 1];
        for (int i = 0; i < height.length; i++) {
            if (height[i] > lMax) {
                lMax = height[i];
            }
            leftHigh[i] = lMax;
        }
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > rMax) {
                rMax = height[i];
            }
            rightHigh[i] = rMax;
        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result = result + Math.min(leftHigh[i],rightHigh[i]) - height[i];
        }
        return result;
    }
}
