package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

/**
 * @author xiaxiang
 * @date 2021/3/10 15:36
 * @description
 */
public class Dijkstra {

    @Test
    public void dijktra() {
        int max = Integer.MAX_VALUE;
        int[][] pic = {{0, 1, 12, max, max, max},
                {max, 0, 9, 3, max, max},
                {max, max, 0, max, 5, max},
                {max, max, 4, 0, 13, 15},
                {max, max, max, max, 0, 4},
                {max, max, max, max, max, max, 0}};
        boolean[] looked = new boolean[pic[0].length];
        int[] pre = new int[pic[0].length];
        for (int i = 0; i < pic[0].length; i++) {
            looked[i] = false;
            pre[i] = -1;
        }
        looked[0] = true;
        for (int i = 1; i < pic[0].length; i++) {
            int min = max;
            int selectNode = 0;
            for (int j = 0; j < pic[0].length; j++) {
                if (!looked[j] && pic[0][j] != max && pic[0][j] < min) {
                    min = pic[0][j];
                    selectNode = j;
                }
            }
            if (i == 1) {
                pre[selectNode] = 0;
            }
            for (int j = 0; j < pic[0].length; j++) {
                if (pic[selectNode][j] != max && selectNode != j) {
                    if (min + pic[selectNode][j] < pic[0][j]) {
                        pic[0][j] = min + pic[selectNode][j];
                        pre[j] = selectNode;
                    }
                }
            }
            looked[selectNode] = true;
        }
        for (int i = 0; i < pic[0].length; i++) {
            System.out.print(pic[0][i] + " ");
        }
        System.out.println();
        print(0, 5, pre);
    }

    public void print(int q, int z, int[] pre) {
        if (pre[z] != -1 && q != z) {
            print(q, pre[z], pre);
        }
        System.out.print(z + 1 + " ");
    }
}
