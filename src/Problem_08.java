// Google.
//
// A unival tree (which stands for "universal value") is a tree where
// all nodes under it have the same value.
//
// Given the root to a binary tree, count the number of unival subtrees.
//
// For example, the following tree has 5 unival subtrees:
//
//       0
//      / \
//     1   0
//        / \
//       1   0
//      / \
//     1   1

/**
 * @author ashKIK
 */
public class Problem_08 {

  private static int count = 0;

  private static boolean is_valid_part(Node node, int val) {

    // considered a valid subtree
    if (node == null) {
      return true;
    }

    // check if node.left and node.right are univalue subtrees of value node.val
    // note that || short circuits but | does not - both sides of the or get evaluated with | so we explore all possible routes
    if (!is_valid_part(node.left, node.val) | !is_valid_part(node.right, node.val)) {
      return false;
    }

    // if it passed the last step then this a valid subtree - increment
    count++;

    // at this point we know that this node is a univalue subtree of value node.val
    // pass a boolean indicating if this is a valid subtree for the parent node
    return node.val == val;
  }

  private static int countUnivalSubtrees(Node root) {
    is_valid_part(root, 0);
    return count;
  }

  private static Node createTree() {
    Node a = new Node(1);
    a.left = new Node(1);
    a.right = new Node(1);

    Node b = new Node(0);
    b.left = a;
    b.right = new Node(0);

    Node root = new Node(0);
    root.left = new Node(1);
    root.right = b;

    return root;
  }

  public static void main(String... args) {
    Node node = createTree();
    System.out.println(countUnivalSubtrees(node));
  }

  private static class Node {

    private final int val;
    private Node left;
    private Node right;

    Node(int val) {
      this.val = val;
    }
  }
}
