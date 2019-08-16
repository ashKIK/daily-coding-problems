package Problems_81_90;

// Google.
//
// Invert a binary tree.
//
// For example, given the following tree:
//
//       a
//     /   \
//    b     c
//   / \   /
//  d   e f
//
// should become:
//
//       a
//     /   \
//    c     b
//     \   / \
//      f e   d

/**
 * @author ashKIK
 */
public class Problem_83 {

  private static class TreeNode {

    private final char val;
    private TreeNode left;
    private TreeNode right;

    TreeNode(char val) {
      this.val = val;
    }
  }

  private static TreeNode createTree() {
    TreeNode a = new TreeNode('a');
    TreeNode b = new TreeNode('b');
    TreeNode c = new TreeNode('c');
    TreeNode d = new TreeNode('d');
    TreeNode e = new TreeNode('e');
    TreeNode f = new TreeNode('f');

    a.left = b;
    a.right = c;

    b.left = d;
    b.right = e;

    c.left = f;

    return a;
  }

  private static TreeNode invert(TreeNode node) {
    if (node == null) {
      return null;
    }

    TreeNode left = node.left;
    TreeNode right = node.right;
    node.right = invert(left);
    node.left = invert(right);

    return node;
  }

  public static void main(String... args) {
    TreeNode root = invert(createTree());
  }
}
