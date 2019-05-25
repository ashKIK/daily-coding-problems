package Problems_21_30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Google.
//
// Given a singly linked list and an integer k, remove the kth last element from the list.
// k is guaranteed to be smaller than the length of the list.
//
// The list is very long, so making more than one pass is prohibitively expensive.
//
// Do this in constant space and in one pass.

/**
 * @author ashKIK
 */
public class Problem_26 {

  private static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  private static ListNode createList(int total) {
    ListNode root = null;
    ListNode node = null;

    for (int i = 0; i < total; i++) {
      ListNode temp = new ListNode(i);
      if (node != null) {
        node.next = temp;
      }
      node = temp;
      if (root == null) {
        root = temp;
      }
    }

    return root;
  }

  private static ListNode removeKthLast(ListNode root, int k) {
    if (root == null) {
      return null;
    }

    int pos = 0;
    ListNode prev = null;
    ListNode node = root;

    while (node != null) {
      node = node.next;
      pos++;

      if (pos > k + 1) {
        prev = prev != null ? prev.next : root;
      }
    }

    if (prev == null) {
      node = root.next;
      root.next = null;
      return node;
    }

    node = prev.next.next;
    prev.next = null;
    prev.next = node;

    return root;
  }

  private static void test(int total, int k) {
    ListNode root = createList(total);
    System.out.println(print(root) + " -> " + print(removeKthLast(root, k)));
  }

  private static String print(ListNode node) {

    List<Integer> values = new ArrayList<>();

    while (node != null) {
      values.add(node.val);
      node = node.next;
    }

    return Arrays.toString(values.stream().mapToInt(val -> val).toArray());
  }

  public static void main(String... args) {
    test(5, 0); // [0, 1, 2, 3]
    test(5, 1); // [0, 1, 2, 4]
    test(5, 2); // [0, 1, 3, 4]
    test(5, 3); // [0, 2, 3, 4]
    test(5, 4); // [1, 2, 3, 4]
    test(1, 0); // []
  }
}
