package com.jerrylikecola.prepare.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaxiang
 * @date 2021/2/23 15:49
 * @description
 */
@Slf4j
public class JerryBlockingQueue {

    private final List<Integer> items = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    static int SIZE = 10;

    public void put(Integer item) {
        lock.lock();
        try {
            if (items.size() == SIZE) {
                log.info("满了");
                notFull.await();
            }
            items.add(item);
            count++;
            notEmpty.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take(){
        lock.lock();
        try{
            if (count == 0){
                log.info("空了");
                notEmpty.await();
            }
            items.remove(0);
            count--;
            notFull.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
