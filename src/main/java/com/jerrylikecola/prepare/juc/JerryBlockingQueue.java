package com.jerrylikecola.prepare.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaxiang
 * @date 2021/2/23 15:49
 * @description
 */
public class JerryBlockingQueue {
    private int[] arr;
    private AtomicInteger count = new AtomicInteger(0);
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public JerryBlockingQueue(int size) {
        this.arr = new int[size];
    }

    public void put(int value) throws InterruptedException {
        lock.lock();
        try{
            if (count.get() == arr.length) {
                notFull.await();
            }
            arr[count.getAndIncrement()] = value;
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public int take() throws InterruptedException {
        lock.lock();
        try{
            if (count.get() == 0) {
                notEmpty.await();
            }
            int result = arr[count.get() - 1];
            arr[count.get() - 1] = 0;
            count.decrementAndGet();
            notFull.signalAll();
            return result;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JerryBlockingQueue queue = new JerryBlockingQueue(10);
        for (int i = 0; i < 10; i++) {
            queue.put(i);
        }
        new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("我put成功了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                queue.take();
                System.out.println("我take成功了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
