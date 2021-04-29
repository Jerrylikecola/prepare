package com.jerrylikecola.prepare.juc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
     *
     * @throws InterruptedException
     */
    @Test
    public void printABC() {
        AtomicInteger str = new AtomicInteger(1);
        new Thread(() -> {
            int count = 10;
            while (count > 0) {
                if (str.get() == 1) {
                    System.out.print("A");
                    str.set(2);
                    count--;
                }
            }
        }).start();
        new Thread(() -> {
            int count = 10;
            while (count > 0) {
                if (str.get() == 2) {
                    System.out.print("B");
                    str.set(3);
                    count--;
                }
            }
        }).start();
        new Thread(() -> {
            int count = 10;
            while (count > 0) {
                if (str.get() == 3) {
                    System.out.print("C");
                    str.set(1);
                    count--;
                }
            }
        }).start();
    }

    class PrintChar implements Runnable {
        private Object own;
        private Object pre;
        private String str;

        public PrintChar(Object own, Object pre, String str) {
            this.own = own;
            this.pre = pre;
            this.str = str;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (pre) {
                    synchronized (own) {
                        System.out.print(str);
                        own.notifyAll();
                    }
                    try {
                        pre.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Test
    public void testABC() throws InterruptedException {
        printABC();
    }

    @Test
    public void printABC1() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        new Print(c, a, "A").start();
        Thread.sleep(100);
        new Print(a, b, "B").start();
        Thread.sleep(100);
        new Print(b, c, "C").start();

    }

    class Print extends Thread {
        private Object pre;
        private Object own;
        private String print;

        public Print(Object pre, Object own, String print) {
            this.pre = pre;
            this.own = own;
            this.print = print;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (pre) {
                    synchronized (own) {
                        System.out.print(print);
                        own.notifyAll();
//                        try {
//                            pre.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                    try {
                        pre.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
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

    @Test
    public void testThreadLocal(){
        new Local(1).start();
        new Local(2).start();
    }

    class Local extends Thread{
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        private int name;

        public Local(Integer name) {
            this.name = name;
        }

        @Override
        public void run() {
            threadLocal.set(name);
            System.out.println(threadLocal.get());
        }
    }

    @Test
    public void testLocal() throws InterruptedException {

        new Thread(()->{
                TestLocal testLocal = new TestLocal(1);
        System.out.println(testLocal.get());
        }).start();

        Thread.sleep(1000);
        new Thread(()->{
            TestLocal testLocal = new TestLocal(2);
            System.out.println(testLocal.get());
        }).start();
    }

    class TestLocal{
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        public TestLocal(int i) {
            threadLocal.set(i);
        }

        public int get(){
            System.out.println(Thread.currentThread().getName());
            return threadLocal.get();
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
