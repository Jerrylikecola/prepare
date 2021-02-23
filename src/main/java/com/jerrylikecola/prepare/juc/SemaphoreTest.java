package com.jerrylikecola.prepare.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaxiang
 * @date 2021/2/23 10:34
 * @description
 */
@Slf4j
public class SemaphoreTest {

    /**
     * 原理同ReentrantLock、信号量能实现多个计数，默认为1
     */
    public static Semaphore semaphore;
    public static volatile AtomicInteger count;

    public static void main(String[] args) {
        semaphore = new Semaphore(5);
        count = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            new Thread1().start();
        }
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            try {
                log.info(Thread.currentThread().getName() + " :我去申请停车位了");
                semaphore.acquire();
                count.incrementAndGet();
                log.info(Thread.currentThread().getName() + " :我申请到停车位了,现在有 " + count + " 辆车");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.decrementAndGet();
                semaphore.release();
                log.info(Thread.currentThread().getName() + " :我开走了");
            }
        }
    }
}
