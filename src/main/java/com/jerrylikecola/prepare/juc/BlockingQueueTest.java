package com.jerrylikecola.prepare.juc;

/**
 * @author xiaxiang
 * @date 2021/2/23 15:32
 * @description
 */
public class BlockingQueueTest {

    /**
     * >>>阻塞队列<<<
     * 当队列满的时候，插入线程被阻塞，等到取出线程执行后被唤醒
     * 当队列空的时候，取出线程被阻塞，等到插入线程执行后被唤醒
     *
     * >>>ArrayBlockingQueue<<<
     * 用数组实现的有界阻塞队列
     *
     * >>>LinkedBlockingQueue<<<
     * 基于链表的阻塞队列，默许一个类似无限大的容量Integer.MAX_VALUE
     * 生产者端和消费者端分别采用了独立的锁
     *
     * >>>PriorityBlockingQueue<<<
     * 是一个支持优先级的无界队列
     * 实现compareTo()方法来指定元素排序
     *
     * >>>DelayQueue<<<
     * 支持延时获取元素的无界阻塞队列
     * 缓存系统的设计&定时任务调度
     *
     * >>>SynchronousQueue<<<
     * 是一个不存储元素的阻塞队列。每一个 put 操作必须等待一个 take 操作，否则不能继续添加元素
     *
     * >>>LinkedTransferQueue<<<
     * >>>LinkedBlockingDeque<<<
     */
    public static void main(String[] args) {
    }
}
