package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiaxiang
 * @date 2021/4/25 11:02
 * @description
 */
public class MaxProfitAssignment {

    @Test
    public void test() {
        int[] arr1 = new int[]{68, 35, 52, 47, 86};
        int[] arr2 = new int[]{67, 17, 1, 81, 3};
        int[] arr3 = new int[]{92, 10, 85, 84, 82};
        System.out.println(maxProfitAssignment(arr1, arr2, arr3));
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Map<Integer, Integer> jobs = new HashMap<>();
        for (int i = 0; i < profit.length; i++) {
            if (Objects.isNull(jobs.get(profit[i]))) {
                jobs.put(profit[i], difficulty[i]);
            } else {
                if (jobs.get(profit[i]) > difficulty[i]) {
                    jobs.put(profit[i], difficulty[i]);
                }
            }
        }

        int max = 0;
        Arrays.sort(profit);
        Arrays.sort(worker);
        int n = profit.length-1;
        for (int i = worker.length - 1; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (jobs.get(profit[j]) <= worker[i]) {
                    max = max + profit[j];
                    n = j;
                    break;
                }
            }
        }
        return max;
    }
}
