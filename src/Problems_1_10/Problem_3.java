package Problems_1_10;

import java.util.Deque;
import java.util.LinkedList;

// Google.
//
// Given the root to a binary tree,
// implement serialize(root), which serializes the tree into a string,
// and deserialize(s), which deserializes the string back into the tree.
//
// For example, given the following Node class
//
// class Node:
//     def __init__(self, val, left=None, right=None):
//     self.val = val
//     self.left = left
//     self.right = right
//
// The following test should pass:
//
//     node = Node('root', Node('left', Node('left.left')), Node('right'))
//     assert deserialize(serialize(node)).left.left.val == 'left.left'

/**
 * @author ashKIK
 */
public class Problem_3 {

  private static Node createTree() {
    Node five = new Node(5);
    five.left = new Node(6);

    Node two = new Node(2);
    two.left = new Node(4);
    two.right = five;

    Node seven = new Node(7);
    seven.right = new Node(8);

    Node three = new Node(3);
    three.left = seven;

    Node root = new Node(1);
    root.left = two;
    root.right = three;

    return root;
  }

  private static String serialize(Node root) {
    return serialize(new StringBuilder(), root).toString();
  }

  private static StringBuilder serialize(StringBuilder builder, Node node) {
    if (node == null) {
      return builder;
    }

    builder.append(node.val);

    builder.append('(');
    serialize(builder, node.left).append(')');

    builder.append('(');
    serialize(builder, node.right).append(')');

    return builder;
  }

  private static Node deserialize(String str) {
    Deque<Node> stack = new LinkedList<>();
    deserialize(str, 0, stack);
    return stack.pop();
  }

  private static int deserialize(String str, int position, Deque<Node> stack) {
    if (position >= str.length() || str.charAt(position) == ')') {
      stack.push(null);
    } else {
      int offset = position;

      while (str.charAt(offset) != '(') {
        offset++;
      }

      Node node = new Node(Integer.parseInt(str.substring(position, offset)));
      stack.push(node);

      position = deserialize(str, offset + 1, stack);
      position = deserialize(str, position + 2, stack) + 1;

      node.right = stack.pop();
      node.left = stack.pop();
    }

    return position;
  }

  public static void main(String... args) {
    Node root = createTree();
    String str = serialize(root);
    System.out.println(str);
    Node deserialize = deserialize(str);
  }

  private static final class Node {

    private final int val;
    private Node left;
    private Node right;

    Node(int val) {
      this.val = val;
    }
  }
}
