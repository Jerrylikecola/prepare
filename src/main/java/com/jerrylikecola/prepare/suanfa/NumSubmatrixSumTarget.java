package com.jerrylikecola.prepare.suanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaxiang
 * @date 2021/3/19 15:01
 * @description 1074
 */
public class NumSubmatrixSumTarget {

    public static void main(String[] args) {
        int[][] num = {{0, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 0}, {1, 0, 0, 1, 0, 0}};
        System.out.println(numSubmatrixSumTarget(num, 0));
    }

    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int[] yasuo = yaSuo(matrix, i, j);
                int pre = 0;
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                for (int k = 0; k < yasuo.length; k++) {
                    pre = pre + yasuo[k];
                    if (map.containsKey(pre - target)){
                        count=count+map.get(pre-target);
                    }
                    map.put(pre,map.getOrDefault(pre,0)+1);
                }
            }
        }
        return count;
    }

    public static int[] yaSuo(int[][] matrix, int i, int j) {
        int[] yasuo = new int[matrix[0].length];
        for (int k = 0; k < matrix[0].length; k++) {
            int sum = 0;
            for (int l = i; l <= j; l++) {
                sum = sum + matrix[l][k];
            }
            yasuo[k] = sum;
        }
        return yasuo;
    }

    public static int sum(int x1, int x2, int[] matrix) {
        int sum = 0;
        for (int i = x1; i <= x2; i++) {
            sum = sum + matrix[i];
        }
        return sum;
    }
}
