import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// Google.
//
// Given pre-order and in-order traversals of a binary tree, write a function to reconstruct the tree.
//
// For example, given the following preorder traversal:
//
// [a, b, d, e, c, f, g]
//
// And the following inorder traversal:
//
// [d, b, e, a, f, c, g]
//
// You should return the following tree:
//
//        a
//       / \
//      b   c
//     / \ / \
//    d  e f  g

/**
 * @author ashKIK
 */
public class Problem_48 {

  private static class TreeNode {

    Character val;
    TreeNode left;
    TreeNode right;

    TreeNode(Character chr) {
      val = chr;
    }
  }

  private static class BinaryTree {

    private Set<TreeNode> set = new HashSet<>();
    private Stack<TreeNode> stack = new Stack<>();

    private TreeNode constructTree(char[] preorder, char[] inorder) {

      TreeNode root = null;
      for (int pre = 0, in = 0; pre < preorder.length; ) {

        TreeNode node;
        do {
          node = new TreeNode(preorder[pre]);
          if (root == null) {
            root = node;
          }
          if (!stack.isEmpty()) {
            if (set.contains(stack.peek())) {
              set.remove(stack.peek());
              stack.pop().right = node;
            } else {
              stack.peek().left = node;
            }
          }
          stack.push(node);
        } while (preorder[pre++] != inorder[in] && pre < preorder.length);

        node = null;
        while (!stack.isEmpty() && in < inorder.length && stack.peek().val == inorder[in]) {
          node = stack.pop();
          in++;
        }

        if (node != null) {
          set.add(node);
          stack.push(node);
        }
      }

      return root;
    }

    private void printInorder(TreeNode node) {
      if (node == null) {
        return;
      }

      printInorder(node.left);
      System.out.print(node.val + " ");
      printInorder(node.right);
    }
  }

  public static void main(String... args) {

    BinaryTree tree = new BinaryTree();

    char[] preorder = "abdecfg".toCharArray();
    char[] inorder = "dbeafcg".toCharArray();

    TreeNode root = tree.constructTree(preorder, inorder);

    tree.printInorder(root);
  }
}
