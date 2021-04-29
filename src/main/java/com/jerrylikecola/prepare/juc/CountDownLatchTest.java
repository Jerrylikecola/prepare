package com.jerrylikecola.prepare.juc;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiaxiang
 * @date 2021/2/19 17:34
 * @description
 */
public class CountDownLatchTest {

    /**
     * 等待release()方法把state的值变成0后，被await()阻塞的线程会从阻塞队列中获取到锁
     */

    private static int SIZE = 7;
    private static CountDownLatch countDownLatch;

    public static void main(String[] args) {
        countDownLatch = new CountDownLatch(SIZE);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 爷爷被妖怪抓走了!");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 爷爷被救了!");
        }).start();

        for (int i = 0; i < 7; i++) {
            new HuLuWa().start();
        }
    }

    static class HuLuWa extends Thread {
        @Override
        public void run() {
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + ":我去救爷爷了!");
        }
    }

    @Test
    public void test() throws InterruptedException {
        System.out.println(new Date());
        new CountDownLatch(1).await();
        System.out.println(new Date());
    }
}
