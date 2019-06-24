package Problems_71_80;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// Google.
//
// Given the root of a binary tree, return a deepest node. For example, in the following tree, return d.
//
//     a
//    / \
//   b   c
//  /
// d

/**
 * @author ashKIK
 */
public class Problem_80 {

  private static final class Node {

    private final char data;
    private Node left;
    private Node right;

    Node(char data) {
      this.data = data;
    }
  }

  private static Node createTree() {
    Node d = new Node('d');
    Node b = new Node('b');
    b.left = d;

    Node c = new Node('c');
    Node root = new Node('a');
    root.left = b;
    root.right = c;

    return root;
  }

  private static char deepestNode(Node root) {
    if (root == null) {
      return '\0';
    }

    Deque<List<Node>> queue = new LinkedList<>();
    queue.add(List.of(root));

    List<Node> deepestNodes = Collections.emptyList();

    while (!queue.isEmpty()) {
      deepestNodes = queue.poll();
      List<Node> nodes = new ArrayList<>();

      deepestNodes.forEach(node -> {
        if (node.left != null) {
          nodes.add(node.left);
        }
        if (node.right != null) {
          nodes.add(node.right);
        }
      });

      if (!nodes.isEmpty()) {
        queue.add(nodes);
      }
    }

    if (deepestNodes.isEmpty()) {
      return '\0';
    } else {
      return deepestNodes.iterator().next().data;
    }
  }

  public static void main(String... args) {

    System.out.println(deepestNode(createTree()));
  }
}