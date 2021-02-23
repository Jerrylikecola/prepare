package com.jerrylikecola.prepare.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaxiang
 * @date 2021/2/22 17:11
 * @description
 */

@Slf4j
public class ReentrantLockTest {

    private static Lock lock;

    /**
     * ReentrantLock可以自定义是公平锁还是非公平锁，默认实现是非公平锁，它是一种乐观锁且是可重入锁
     * >>非公平锁的实现<<
     * lock()方法的实现是无论是哪个线程去竞争锁都是直接做CAS操作去设置AQS组件中state的值，如果成功即获取到锁，这也是其不公平所在
     * unlock()方法调用AQS封装的release()方法
     * >>公平锁的实现<<
     * lock()方法会追加判断是否是等待队列里面的线程，只有满足才会去尝试CAS去获取
     */
    public static void main(String[] args) {
        lock = new ReentrantLock();
        new Thread1().start();
        new Thread2().start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + " :我尝试去获取锁");
            lock.lock();
            log.info(Thread.currentThread().getName() + " :我获取到锁了");
            lock.unlock();
            log.info(Thread.currentThread().getName() + " :我释放锁了");
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + " :我尝试去获取锁");
            lock.lock();
            log.info(Thread.currentThread().getName() + " :我获取到锁了");
            lock.unlock();
            log.info(Thread.currentThread().getName() + " :我释放锁了");
        }
    }
}
