package com.jerrylikecola.prepare.suanfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaxiang
 * @date 2021/4/29 11:08
 * @description
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        ListNode next = head;
        int i = 0;
        while (next != null) {
            nodeMap.put(i, next);
            i++;
            next = next.next;
        }
        ListNode prep = nodeMap.get(i - n - 1);
        if (n == 1) {
            if (prep == null) {
                head = null;
            } else {
                prep.next = null;
            }
        } else if (n == i) {
            head = nodeMap.get(1);
        } else {
            prep.next = prep.next.next;
        }
        return head;
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
