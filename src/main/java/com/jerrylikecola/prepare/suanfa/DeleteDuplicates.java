package com.jerrylikecola.prepare.suanfa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaxiang
 * @date 2021/3/25 16:43
 * @description
 */
public class DeleteDuplicates {

    public class ListNode {
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

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        deleteDuplicates(head);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode newNode = new ListNode(0, head);
        ListNode count = newNode;
        while (count.next != null && count.next.next != null) {
            if (count.next.val == count.next.next.val) {
                count.next=count.next.next;
            } else {
                count = count.next;
            }
        }
        return newNode.next;
    }
}
