package com.jerrylikecola.prepare.suanfa;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaxiang
 * @date 2021/3/5 14:49
 * @description
 */

/**
 * 用栈或者list实现queue
 */
public class ListToQueue {

    private List<Integer> list;

    private int size = 0;

    /**
     * Initialize your data structure here.
     */
    public ListToQueue() {
        this.list = new ArrayList<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        list.add(x);
        ++size;
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        int result = list.get(0);
        list.remove(0);
        --size;
        return result;

    }

    /**
     * Get the front element.
     */
    public int peek() {
        return list.get(0);
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        // return size == 0;
        return CollectionUtils.isEmpty(list);
    }

    public static void main(String[] args) {
        ListToQueue queue = new ListToQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }
}
