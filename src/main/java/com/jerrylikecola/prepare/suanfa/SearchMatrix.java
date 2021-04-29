package com.jerrylikecola.prepare.suanfa;

import java.util.Arrays;

/**
 * @author xiaxiang
 * @date 2021/3/30 17:02
 * @description
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int[] arr = new int[matrix.length];
        int count = -1;
        for (int i = 0; i < matrix.length; i++) {
            arr[i] = matrix[i][0];
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
            if (i + 1 < arr.length && target > arr[i] && target < arr[i + 1]) {
                count = i;
                break;
            }
            if (i == arr.length - 1) {
                count = i;
            }
        }
        if (count < 0) {
            return false;
        } else {
            return binarySearch(matrix[count], target);
        }
    }

    public boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int count = (left + right) / 2;
            if (arr[count] > target) {
                right = count - 1;
            } else if (arr[count] < target) {
                left = count + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
