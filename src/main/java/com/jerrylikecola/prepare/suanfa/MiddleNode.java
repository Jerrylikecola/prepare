package com.jerrylikecola.prepare.suanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaxiang
 * @date 2021/4/29 16:45
 * @description
 */
public class MiddleNode {
    public ListNode middleNode(ListNode head) {
        int i = 1;
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        while (head != null) {
            nodeMap.put(i, head);
            head = head.next;
            i++;
        }
        return nodeMap.get(((i - 1) / 2) + 1);
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
