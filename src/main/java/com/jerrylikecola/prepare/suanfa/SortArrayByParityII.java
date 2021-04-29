package com.jerrylikecola.prepare.suanfa;

/**
 * @author xiaxiang
 * @date 2021/4/2 16:52
 * @description 922
 */
public class SortArrayByParityII {
    public int[] sortArrayByParityII(int[] nums) {
        int count = 1;
        int doubleCount = 0;
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]%2==0){
                arr[doubleCount]=nums[i];
                doubleCount+=2;
            }else {
                arr[count] = nums[i];
                count+=2;
            }
        }
        return arr;
    }
}
