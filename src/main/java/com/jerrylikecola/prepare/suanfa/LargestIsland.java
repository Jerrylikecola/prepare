package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author xiaxiang
 * @date 2021/4/1 17:05
 * @description
 */
public class LargestIsland {
    Map<Integer, Integer> map = new HashMap<>();
    int x, y;
    int max = 0;

    @Test
    public void test() {
        int[][] arr = {{0, 0}, {0, 1}};
        System.out.println(largestIsland(arr));
    }

    public int largestIsland(int[][] grid) {
        x = grid[0].length;
        y = grid.length;
        int count = 2;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                ranSe(grid, i, j, count);
                count++;
            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }
                jiSuan(i, j, grid);
            }
        }
        if (max == 0) {
            max = x * y;
        }

        return max;
    }

    public void ranSe(int[][] grid, int i, int j, int count) {
        if (i < 0 || j < 0 || i > y - 1 || j > x - 1) {
            return;
        }
        if (grid[i][j] != 1) {
            return;
        }
        grid[i][j] = count;
        map.put(count, map.getOrDefault(count, 0) + 1);
        ranSe(grid, i - 1, j, count);
        ranSe(grid, i + 1, j, count);
        ranSe(grid, i, j + 1, count);
        ranSe(grid, i, j - 1, count);
    }

    public void jiSuan(int i, int j, int[][] grid) {
        Set<Integer> set = new HashSet<>();
        if (j + 1 < x) {
            set.add(grid[i][j + 1]);
        }
        if (j - 1 >= 0) {
            set.add(grid[i][j - 1]);
        }
        if (i + 1 < y) {
            set.add(grid[i + 1][j]);
        }
        if (i - 1 >= 0) {
            set.add(grid[i - 1][j]);
        }
        set.remove(0);
        Iterator<Integer> iterator = set.iterator();
        int childMax = 1;
        if (map.size() > 0 && set.size() > 0) {
            while (iterator.hasNext()) {
                childMax = childMax + map.get(iterator.next());
            }
        }
        if (childMax > max) {
            max = childMax;
        }
    }
}
