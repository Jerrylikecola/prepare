package com.jerrylikecola.prepare.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xiaxiang
 * @date 2021/2/19 17:02
 * @description
 */
public class CyclicBarrierTest {

    /**
     * await()方法会使用Condition的await()方法阻塞线程，并且--count
     * 如果count==0时，会调用Condition的signalAll()方法唤醒所有线程，并且将count的值初始化
     */

    private static int SIZE = 5;
    private static CyclicBarrier cb;

    public static void main(String[] args) throws InterruptedException {
        cb=new CyclicBarrier(SIZE);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            Thread thread = new Thread(new Coin());
            thread.start();
        }
    }

    static class Coin implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" wait to coin");
            try {
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" coin finish");
        }
    }
}
