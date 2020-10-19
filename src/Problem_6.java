// Google.
//
// An XOR linked list is a more memory efficient doubly linked list.
// Instead of each node holding next and prev fields,
// it holds a field named prevXorNext, which is an XOR of the next node and the previous node.
//
// Implement an XOR linked list;
// it has an add(element) which adds the element to the end,
// and a get(index) which returns the node at index.
//
// If using a language that has no pointers (such as Python),
// you can assume you have access to get_pointer and dereference_pointer functions
// that converts between nodes and memory addresses.

/**
 * @author ashKIK
 */
public class Problem_6 {

  class Node {

    int val;
    Node prevXorNext;

    Node(int val) {
      this.val = val;
    }
  }

  private Node head;
  private Node tail;

  public Problem_6() {
    this.head = null;
    this.tail = null;
  }

  private Node get(int index) {
    Node curr = head;
    Node prev = null;
    Node next;

    while (curr != null && index > 0) {
      next = xor(prev, curr.prevXorNext);
      prev = curr;
      curr = next;
      index--;
    }

    return curr;
  }

  private void add(int value) {
    Node newNode = new Node(value);

    //xor of last node and null
    newNode.prevXorNext = xor(tail, null);

    if (head == null) {
      head = newNode;
    }

    if (tail != null) {
      // current tail.prevXorNext is prev xor null
      Node prev = xor(tail.prevXorNext, null);
      tail.prevXorNext = xor(prev, newNode);
    }

    tail = newNode;
  }

  private Node xor(Node a, Node b) {
    return dereferencePointer(getPointer(a) ^ getPointer(b));
  }

  // assumed implemented
  private int getPointer(Node node) {
    return 0;
  }

  // assumed implemented
  private Node dereferencePointer(int addr) {
    return new Node(addr);
  }

  public static void main(String... args) {
  }
}