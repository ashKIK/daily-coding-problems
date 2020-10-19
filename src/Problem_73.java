// Google.
//
// Given the head of a singly linked list, reverse it in-place.

/**
 * @author ashKIK
 */
public class Problem_73 {

  private static class Node {

    private final int val;
    private Node next;

    Node(int val) {
      this.val = val;
    }
  }

  private static Node reverseInPlace(Node node) {
    Node prev = null;

    while (node != null) {

      Node next = node.next;

      if (prev == null) {
        prev = node;
        prev.next = null;
      } else {
        node.next = prev;
        prev = node;
      }
      node = next;
    }

    return prev;
  }

  private static void printList(Node node) {

    while (node != null) {
      System.out.print(node.val + ", ");
      node = node.next;
    }
    System.out.println();
  }

  private static Node createList(int... vals) {
    Node root = null;
    Node node = null;

    for (int val : vals) {
      Node tmp = new Node(val);

      if (node == null) {
        node = tmp;
        root = node;
      } else {
        node.next = tmp;
        node = tmp;
      }
    }

    return root;
  }

  public static void main(String... args) {

    Node root = createList(1, 2, 3, 4, 5, 7, 8, 9, 10);
    printList(root);
    printList(reverseInPlace(root));
  }
}
