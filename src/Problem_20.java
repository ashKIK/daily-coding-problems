// Google.
//
// Given two singly linked lists that intersect at some point, find the intersecting node.
// The lists are non-cyclical.
//
// For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return the node with value 8.
//
// In this example, assume nodes with the same value are the exact same node objects.
//
// Do this in O(M + N) time (where M and N are the lengths of the lists) and constant space.

/**
 * @author ashKIK
 */
public class Problem_20 {

  public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  // Two pointer solution
  // O(M + N)
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    ListNode pointerA = headA;
    ListNode pointerB = headB;

    while (pointerA != pointerB) {
      pointerA = pointerA == null ? headB : pointerA.next;
      pointerB = pointerB == null ? headA : pointerB.next;
    }
    return pointerA;
  }
}
