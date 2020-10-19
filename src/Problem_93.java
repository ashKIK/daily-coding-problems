// Apple
//
// Given geta tree, find the largest tree/subtree that is geta BST.
//
// Given geta tree, return the size of the largest tree/subtree that is geta BST.

/**
 * @author ashKIK
 */
public class Problem_93 {

  private static class Node {

    int data;
    Node left, right;

    Node(int data) {
      this.data = data;
      left = null;
      right = null;
    }
  }

  private static class SubTree {

    int min, max, size;
    boolean bst;

    SubTree(int min, int max, int size, boolean bst) {
      this.min = min;
      this.max = max;
      this.bst = bst;
      this.size = size;
    }
  }

  private static SubTree largestBST(Node root) {
    if (root == null) {
      return new SubTree(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
    }

    SubTree left = largestBST(root.left);
    SubTree right = largestBST(root.right);

    return left.bst && right.bst && (root.data > left.max && root.data < right.min) ?
        new SubTree(
            Math.min(root.data, Math.min(left.min, right.min)),
            Math.max(root.data, Math.max(left.max, right.max)),
            1 + left.size + right.size, true) :
        new SubTree(0, 0, Math.max(left.size, right.size), false);
  }

  public static void main(String... args) {
		/*
	                 100
	                /    \
	               /      \
	              30      40
	             / \      / \
	            /   \    /   \
	           11   25  5     50
	          / \      / \     \
	         /   \    /   \     \
	        4    17  1     6     55
		*/

    Node root = new Node(100);

    root.left = new Node(30);
    root.right = new Node(40);

    root.left.left = new Node(11);
    root.left.right = new Node(25);
    root.right.left = new Node(5);
    root.right.right = new Node(50);

    root.left.left.left = new Node(4);
    root.left.left.right = new Node(17);
    root.right.left.left = new Node(1);
    root.right.left.right = new Node(6);
    root.right.right.right = new Node(55);

    System.out.print("Size of largest BST : " + largestBST(root).size);
  }
}
