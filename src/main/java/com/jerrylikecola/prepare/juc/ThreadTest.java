package com.jerrylikecola.prepare.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

/**
 * @author xiaxiang
 * @date 2021/2/22 15:07
 * @description
 */
@Slf4j
public class ThreadTest {

    /**
     * >>>Java中的线程调度<<<
     * 1、抢占式调度：系统自己调度不同线程得到不同的时间片执行（JAVA使用）
     * 2、协同式调度：一个线程执行完后通知系统执行下一个，容易造成系统堵塞
     */

    public static void main(String[] args) throws Exception {

        new Thread1().start();
        new Thread(new Thread2()).start();
        Object call = new Thread3().call();
        log.info(String.valueOf(call));

        // 多线程打印ABC
        printABC();
    }
    /**
     * >>>如何在两个线程中共享数据<<<
     * 1、将数据抽象成一个类，并将对数据操作的方法打上synchronized注解
     * 2、将Runnable对象作为一个类的内部类，共享数据作为这个类的成员变量 == 多个内部类访问这个类的变量
     */

    /**
     * >>>ThreadLocal<<<
     * 线程本地储存
     * 牺牲空间换时间
     * 长生命周期对象Thread持有短生命周期对象ThreadLocal引用导致其无法及时回收造成内存泄漏
     * 其本身的setEntry()和getEntry方法遇到null后会将value设置成null，也可以调用remove()方法来避免
     */

    /**
     * >>>Thread.start()线程是怎么启动的<<<
     * start() -> start0()[JNI方法] -> JVM_StartThread -> new JavaThread -> thread_entry -> Thread::start
     * 并行的os::create_thread -> os::start_thread
     * os::create_thread -> pthread_create -> 调用操作系统的clone的方法
     * 最后会调用javaThread::run()进行回调调用run()方法
     */

    /**
     * 多线程打印ABC
     * @throws InterruptedException
     */
    public static void printABC() throws InterruptedException {
        Semaphore lockA = new Semaphore(1);
        Semaphore lockB = new Semaphore(1);
        Semaphore lockC = new Semaphore(1);


        lockA.acquire();
        lockB.acquire();
        lockC.acquire();
        lockA.release();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockA.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("A");
                lockB.release();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockB.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("B");
                lockC.release();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    lockC.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("C");
                lockA.release();
            }
        }).start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            log.info("sb");
        }
    }

    static class Thread2 implements Runnable {

        @Override
        public void run() {
            log.info("sb");
        }
    }

    static class Thread3 implements Callable {

        @Override
        public Object call() throws Exception {
            return "sb";
        }
    }

    static class ThreadState {
        // NEW
        // 当程序使用 new 关键字创建了一个线程之后，该线程就处于新建状态，此时仅由 JVM 为其分配 内存，并初始化其成员变量的值

        // RUNNABLE
        // 当线程对象调用了 start()方法之后，该线程处于就绪状态。Java 虚拟机会为其创建方法调用栈和程序计数器，等待调度运行

        // BLOCKED
        // 碰到synchronized或者lock等占用资源的情况

        // WAITING
        // 碰到LockSupport.park()、Thread.join()、Object.wait()等方法

        // TIMED_WAITING
        // 碰到LockSupport.parkUntil()、Thread.join(long)、Object.wait(long)、Thread.sleep(long)等方法

        // TERMINATED
        // 执行结束
    }

    // interrupt()方法结束线程，如果线程处于阻塞状态，则会抛出InterruptException异常被捕获后break掉，如果不处于阻塞状态则中断标识会设置成true

    // 守护线程的例子：
    // 1、垃圾回收线程，当该线程是jvm上仅剩的线程时，垃圾回收线程会自己离开
    // 2、守护线程，当jvm中的所有线程都是守护线程时，jvm就可以退出了
    // 线程是JVM级别的
}
