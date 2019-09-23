package easy;

import basicstruct.ListNode;

/**
 * 设计链表
 */
public class Problem707 {

    private ListNode head;

    private ListNode tail;

    private int size;

    /** Initialize your data structure here. */
    public Problem707() {
        head = new ListNode(-1);
        tail = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode tmp = head;
        while (index >= 0) {
            tmp = tmp.next;
            index--;
        }
        return tmp.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode node = head.next;
        ListNode newNode = new ListNode(val);
        newNode.next = node;
        head.next = newNode;
        if (tail == null) {
            tail = head.next;
        }
        size += 1;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if (tail == null) {
            return;
        }
        ListNode newNode = new ListNode(val);
        tail.next = newNode;
        tail = tail.next;
        size += 1;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else if (index > size) {
            return;
        } else {
            ListNode tmp = head;
            while (index > 0) {
                tmp = tmp.next;
                index--;
            }
            ListNode after = tmp.next;
            ListNode newNode = new ListNode(val);
            newNode.next = after;
            tmp.next = newNode;
            size += 1;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode tmp = head;
        while (index > 0) {
            tmp = tmp.next;
            index--;
        }
        ListNode after = tmp.next;
        if (after.next == null) {
            tail = tmp;
        }
        tmp.next = after.next;
        size -= 1;
    }
}
