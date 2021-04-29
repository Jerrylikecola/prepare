package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaxiang
 * @date 2021/3/22 11:46
 * @description
 */
public class NumTriplets {

    @Test
    public void test() {
        int[] nums1 = new int[]{4,1,4,1,12};
        int[] nums2 = new int[]{3,2,5,4};
        System.out.println(numTriplets(nums1, nums2));
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            Map<Double, Integer> map = new HashMap<>();
            for (int j = 0; j < nums2.length; j++) {
                long result = (long) nums1[i] * (long) nums1[i];
                double tag = result / (double) nums2[j];
                if (map.containsKey(tag)) {
                    count = count + map.get(tag);
                }
                map.put((double) nums2[j], map.getOrDefault((double) nums2[j], 0) + 1);
            }
        }

        for (int i = 0; i < nums2.length; i++) {
            Map<Double, Integer> map = new HashMap<>();
            for (int j = 0; j < nums1.length; j++) {
                long result = (long) nums2[i] * (long) nums2[i];
                double tag = result / (double) nums1[j];
                if (map.containsKey(tag)) {
                    count = count + map.get(tag);
                }
                map.put((double) nums1[j], map.getOrDefault((double) nums1[j], 0) + 1);
            }
        }
        return count;
    }
}
