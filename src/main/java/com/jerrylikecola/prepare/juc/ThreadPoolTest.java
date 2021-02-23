package com.jerrylikecola.prepare.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaxiang
 * @date 2021/2/23 15:01
 * @description
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        /**
         * >>>构造参数<<<
         * corePoolSize:指定了线程池中的线程数量
         * maximumPoolSize:指定了线程池中的最大线程数量
         * keepAliveTime:当前线程池数量超过corePoolSize时，多余的空闲线程的存活时间
         * unit:keepAliveTime的单位
         * workQueue:任务队列，被提交但尚未被执行的任务
         * threadFactory:线程工厂，用于创建线程，一般用默认的即可
         * handler:拒绝策略，当任务太多来不及处理，如何拒绝任务（线程到达了maximumPoolSize且等待队列已满）
         *
         * >>>线程池组成<<<
         * ctl：AtomicInteger类型的变量，高三位用来表示线程池的状态，低29位用来表示当前运行的worker数量
         * workQueue：BlockingQueue<Runnable> 用来存放任务的阻塞队列
         * workers：HashSet<Worker> worker的集合
         */
        ExecutorService threadPool = new ThreadPoolExecutor(5, 10,
                20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        threadPool.submit(() -> System.out.println("我是一个任务"));
        /**
         * submit()后的时序
         * 1、获取正在运行的worker数量？如果数量小于corePoolSize，则调用addWorker()方法创建worker：如果数量已经等于corePoolSize，则将任务丢进阻塞队列
         *    如果阻塞队列已经满了，则创建worker执行任务：如果worker数量=maximumPoolSize了，则调用reject()方法执行拒绝策略
         * 2、worker.run()方法会调用runWorker()方法，流程为取出worker中的任务，如果有则执行，如果没有会调用getTask()方法从阻塞队列中获取任务，长时间获取不到的任务的worker会被删除
         */
    }

    /**
     * 线程池拒绝策略
     */
    static class Handler{
        /**
         * 直接抛出异常，阻止系统正常运行。
         */
        private ThreadPoolExecutor.AbortPolicy abortPolicy;
        /**
         * 只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降
         */
        private ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy;
        /**
         * 丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务
         */
        private ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy;
        /**
         * 该策略默默地丢弃无法处理的任务，不予任何处理。如果允许任务丢失，这是最好的一种方案
         */
        private ThreadPoolExecutor.DiscardPolicy discardPolicy;
        /**
         * 可以实现RejectedExecutionHandler接口自己拓展拒绝策略
         */
        private String extHandler;
    }
}
