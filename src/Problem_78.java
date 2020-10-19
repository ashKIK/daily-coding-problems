import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

// Google.
//
// Given k sorted singly linked lists, write a function to merge all the lists into one sorted singly linked list.

/**
 * @author ashKIK
 */
public class Problem_78 {

  private static class ListNode {

    int data;
    ListNode next;

    ListNode(int data) {
      this.data = data;
      next = null;
    }
  }

  private static void printList(ListNode head) {
    while (head != null) {
      System.out.print(head.data + " ");
      head = head.next;
    }
  }

  private static ListNode mergeKSortedLists(ListNode[] lists) {

    PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.data));
    Collections.addAll(pq, lists);

    ListNode head = new ListNode(0);
    ListNode tail = head;

    while (!pq.isEmpty()) {
      ListNode temp = pq.remove();
      if (temp.next != null) {
        pq.add(temp.next);
      }
      tail.next = temp;
      tail = temp;
    }
    return head.next;
  }

  public static void main(String... args) {
    int k = 3;

    ListNode[] linkedLists = new ListNode[k];

    linkedLists[0] = new ListNode(1);
    linkedLists[0].next = new ListNode(3);
    linkedLists[0].next.next = new ListNode(5);
    linkedLists[0].next.next.next = new ListNode(7);

    linkedLists[1] = new ListNode(2);
    linkedLists[1].next = new ListNode(4);
    linkedLists[1].next.next = new ListNode(6);
    linkedLists[1].next.next.next = new ListNode(8);

    linkedLists[2] = new ListNode(0);
    linkedLists[2].next = new ListNode(9);
    linkedLists[2].next.next = new ListNode(10);
    linkedLists[2].next.next.next = new ListNode(11);

    ListNode head = mergeKSortedLists(linkedLists);
    printList(head);
  }
}
