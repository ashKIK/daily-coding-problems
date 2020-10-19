// Dropbox.
//
// Given the root to a binary search tree, find the second largest node in the tree.

/**
 * @author ashKIK
 */
public class Problem_36 {

  private static class Node {

    int data;
    Node left, right;

    Node(int d) {
      data = d;
      left = right = null;
    }
  }

  private static class BST {

    Node root;

    BST() {
      root = null;
    }

    private void insert(int data) {
      this.root = this.insert(this.root, data);
    }

    private Node insert(Node node, int data) {
      if (node == null) {
        this.root = new Node(data);
        return this.root;
      }

      if (data < node.data) {
        node.left = this.insert(node.left, data);
      } else {
        node.right = this.insert(node.right, data);
      }
      return node;
    }

    private Node secondLargestNode(Node root) {
      while (root.right != null) {
        if (root.right.right == null) {
          return root;
        }
        root = root.right;
      }
      return null;
    }
  }

  public static void main(String... args) {
    BST bst = new BST();

//              50
//           /     \
//          30      70
//         /  \    /  \
//       20   40  60   80

    bst.insert(50);
    bst.insert(30);
    bst.insert(20);
    bst.insert(40);
    bst.insert(70);
    bst.insert(60);
    bst.insert(80);

    int result = 0;
    if (bst.secondLargestNode(bst.root) != null) {
      result = bst.secondLargestNode(bst.root).data;
      System.out.println(result);
    }
  }
}
