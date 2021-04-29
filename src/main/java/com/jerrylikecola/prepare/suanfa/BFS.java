package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xiaxiang
 * @date 2021/3/9 17:17
 * @description
 */
public class BFS {

    @Test
    public void test() {
        testBFS(0, 6);
    }

    public List<List<Integer>> getPic() {
        int node = 7;
        List<List<Integer>> pic = new ArrayList<>(node);
        for (int i = 0; i < node; i++) {
            pic.add(new ArrayList<>());
        }
        /**
         * 0 - 1 - 2 - 3
         * |       |
         * 4 - 5 - 6
         * 假设无向图如上所示
         */
        addNode(pic, 0, 1);
        addNode(pic, 1, 2);
        addNode(pic, 2, 3);
        addNode(pic, 0, 4);
        addNode(pic, 4, 5);
        addNode(pic, 5, 6);
        addNode(pic, 2, 6);
        return pic;
    }

    public void addNode(List<List<Integer>> pic, int x, int y) {
        pic.get(x).add(y);
        pic.get(y).add(x);
    }

    public void testBFS(int q, int z) {
        List<List<Integer>> pic = getPic();
        // 构造用于bfs的队列
        Queue<Integer> queue = new LinkedList<>();
        // 用于标记已走过的点
        boolean[] isOld = new boolean[pic.size()];
        // 用于标记这个点的上一个点位置
        int[] pre = new int[pic.size()];
        for (int i = 0; i < pic.size(); i++) {
            pre[i] = -1;
        }
        queue.add(q);
        isOld[q] = true;
        while (queue.size() != 0) {
            // 现在到达的点
            int now = queue.poll();
            for (int i = 0; i < pic.get(now).size(); i++) {
                int next = pic.get(now).get(i);
                if (!isOld[next]) {
                    pre[next] = now;
                    if (next == z) {
                        for (int j = 0; j < pre.length; j++) {
                            System.out.print(pre[j]+" ");
                        }
                        System.out.println();
                        printBFS(pre, q, z);
                        return;
                    }
                    isOld[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

    public void printBFS(int[] pre, int q, int z) {
        if (pre[z] != -1 && q != z) {
            printBFS(pre, q, pre[z]);
        }
        System.out.print(z + " ");
    }
}
