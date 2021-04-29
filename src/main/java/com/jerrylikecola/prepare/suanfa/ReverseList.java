package com.jerrylikecola.prepare.suanfa;


/**
 * @author xiaxiang
 * @date 2021/4/28 14:33
 * @description
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode prep = null;
        ListNode now = head;
        while (now != null) {
            ListNode next = now.next;
            now.next = prep;
            prep = now;
            now = next;
        }
        return prep;
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
