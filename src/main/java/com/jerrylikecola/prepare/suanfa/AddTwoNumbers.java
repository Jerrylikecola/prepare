package com.jerrylikecola.prepare.suanfa;

import lombok.Data;

import java.util.Objects;

/**
 * @author xiaxiang
 * @date 2021/3/19 10:46
 * @description 2
 */
public class AddTwoNumbers {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        ListNode inNode = new ListNode();
        int add = 0;
        int count = 0;
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            }
            int num1, num2;
            num1 = Objects.isNull(l1) ? 0 : l1.val;
            num2 = Objects.isNull(l2) ? 0 : l2.val;
            int result = num1 + num2 + add;
            int resultBasic = result % 10;
            add = result / 10;
            if (count == 0) {
                node.val = resultBasic;
                inNode = node;
            } else {
                ListNode listNode = new ListNode();
                inNode.next = listNode;
                inNode = listNode;
                inNode.val = resultBasic;
            }
            if (Objects.nonNull(l1)) l1 = l1.next;
            if (Objects.nonNull(l2)) l2 = l2.next;
        }
        if (add > 0) {
            inNode.next = new ListNode(add);
        }
        return node;
    }
}
