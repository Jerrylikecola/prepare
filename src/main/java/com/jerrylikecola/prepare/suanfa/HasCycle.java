package com.jerrylikecola.prepare.suanfa;

/**
 * @author xiaxiang
 * @date 2021/4/28 15:23
 * @description
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode gui = head;
        ListNode tu = head.next;
        while (gui != tu) {
            if (tu == null || tu.next == null) {
                return false;
            }
            gui = gui.next;
            tu = tu.next.next;
        }
        return true;
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
