package com.jerrylikecola.prepare.suanfa;

/**
 * @author xiaxiang
 * @date 2021/3/19 10:38
 * @description 1
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i==j){
                    continue;
                }
                if (nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    return result;
                }
            }
        }
        return result;
    }
}
