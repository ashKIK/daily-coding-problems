package Problems_81_90;

// LinkedIn.
//
// Determine whether a tree is a valid binary search tree.
//
// A binary search tree is a tree with two children, left and right,
// and satisfies the constraint that the key in the left child must be less than or
// equal to the root and the key in the right child must be greater than or equal to the root.

/**
 * @author ashKIK
 */
public class Problem_89 {

  private static class Node {

    private int val;
    private Node left;
    private Node right;

    Node(int val) {
      this.val = val;
    }
  }

  private static Node createValidBST() {
    Node one = new Node(1);
    Node four = new Node(4);
    Node seven = new Node(7);
    Node thirteen = new Node(13);

    Node six = new Node(6);
    six.left = four;
    six.right = seven;

    Node three = new Node(3);
    three.left = one;
    three.right = six;

    Node fourteen = new Node(14);
    fourteen.left = thirteen;

    Node ten = new Node(10);
    ten.right = fourteen;

    Node root = new Node(8);
    root.left = three;
    root.right = ten;

    return root;
  }

  private static boolean isValidBST(Node node, int min, int max) {

    if (node == null) {
      return true;
    }

    if (node.val <= min || node.val >= max) {
      return false;
    }

    return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
  }

  private static boolean isValidBST(Node root) {

    return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static void main(String... args) {

    System.out.println(isValidBST(createValidBST()));
  }
}
