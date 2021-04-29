package com.jerrylikecola.prepare.suanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaxiang
 * @date 2021/3/26 11:18
 * @description
 */
public class LRUCache {
    class Node {
        private int val;
        private Node head;
        private Node tail;

        public Node(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }

        public Node() {

        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node head, Node tail) {
            this.val = val;
            this.head = head;
            this.tail = tail;
        }
    }

    private Map<Integer, Integer> map;
    private int size;
    private int count = 0;
    private Node node = new Node();
    private Node tailNode;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.size = capacity;
    }

    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        }
        while (node.val != key) {
            node = node.tail;
        }
        if (node.val != tailNode.val) {
            Node head = node.head;
            Node tail = node.tail;
            head.tail = tail;
            tail.head = head;
            tailNode.tail = node;
            node.head = tailNode;
            tailNode = head;
        }

        return map.get(key);
    }

    public void put(int key, int value) {
        if (count==0){
            node.val=key;
            tailNode=node;
            map.put(key,value);
            count++;
            return;
        }
        boolean isExist = map.containsKey(key);
        map.put(key,value);
        if (isExist){
            while (node.val != key) {
                node = node.tail;
            }
            if (node.val != tailNode.val) {
                Node head = node.head;
                Node tail = node.tail;
                head.tail = tail;
                tail.head = head;
                tailNode.tail = node;
                node.head = tailNode;
                tailNode = head;
            }
        }else {
            if (count==size){
                node=node.tail;
                node.head=null;
            }
            Node newTail = new Node(key);
            tailNode.tail=newTail;
            newTail.head=tailNode;
            tailNode=newTail;
            count++;
        }
    }
}
