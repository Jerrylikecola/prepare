package com.jerrylikecola.prepare.juc;

/**
 * @author xiaxiang
 * @date 2021/2/22 16:36
 * @description
 */
public class SynchronizedTest {

    /**
     * 核心组件由WaitSet、ContentionList、EntryList、Owner、OnDeck组成
     * JVM每次会从ContentionList这个等待队列的尾部取出数据用于OnDeck用于锁竞争候选者，但是并发情况下，该队列会被大量的CAS访问
     * 为了降低对尾部元素的竞争，JVM会把一部分线程移动到EntryList中并指定谁是OnDeck，一般是先进去的那个
     * OnDeck需要重新去竞争锁，称之为竞争切换，获取到锁后该线程会变成Owner线程
     * 如果被wait则会进入WaitSet中，通过notify等后重新回到EntryList中
     * 本质是竞争monitor对象，通过monitorenter、monitorexit以及ACC_SYNCHRONIZED标识
     */
    private String synchronizedText;

    /**
     * java对象由对象头、实例数据、对齐填充组成
     * 对象头由Mark Word和Class Metadata Address组成，后者是类型指针指向对象的类元数据，JVM通过这个指针确定该对象是哪个类的实例，前者存放了锁信息、hashcode、分代信息等
     * 实例数据存放类的属性数据信息，包括父类的属性信息，如果是数组的实例部分还包括数组的长度，这部分内存按4字节对齐
     * 对齐填充不是必须存在的，仅仅是为了字节对齐
     */
    private String model;
}
