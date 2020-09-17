package data.structure.array;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: <p>链表相关练习</p>
 * @author: terui
 * @date: 2020/9/2 9:01 下午
 */
public class LinkedDemo {
    private static class ListNode {
        /**
         * 链表当前值
         */
        int val;

        /**
         * 链表指针，指向下一个节点
         */
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * # 19 删除链表倒数第N个节点(给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点)
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     *
     * @param head 链表头结点
     * @param n    倒数第n个数字移除
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        /**
         * 哨兵节点
         */
        pre.next = head;

        ListNode start = pre;
        ListNode end = pre;
        while (n > 0) {
            start = start.next;
            n--;
        }
        while (start.next != null) {
            start = start.next;
            end = end.next;
        }
        end.next = end.next.next;
        return pre.next;
    }

    /**
     * #21 合并两个有序链表(将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的)
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * @param l1 有序链表1
     * @param l2 有序链表2
     * @return 合并链表
     */
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode pre = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    /**
     * # 141 判定链表是否有环。给定一个链表，判断链表中是否有环。为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
     * 输入: 链表: [3,2,0,-4]，输入一个节点，判断这个链表是否有环
     *
     * @param head 头节点
     * @return true 有环 false 无环
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> result = new HashSet();
        if (head == null) {
            return false;
        }
        while (head != null) {
            if (result.contains(head)) {
                return true;
            }
            result.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * #2 两数相加(给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字)
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public ListNode addTwoNumber(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode deadNode = new ListNode(0);
        ListNode currentNode = deadNode;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + carry;
            carry = sum / 10;
            currentNode.next = new ListNode(sum % 10);
            currentNode = currentNode.next;
            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l1.next;
            }
        }
        if (carry > 0) {
            currentNode.next = new ListNode(carry);
        }
        return deadNode.next;
    }
}
