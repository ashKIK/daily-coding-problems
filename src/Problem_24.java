// Google.
//
// Implement locking in a binary tree.
// A binary tree node can be locked or unlocked only if all of its descendants or ancestors are not locked.
//
// Design a binary tree node class with the following methods:
//
// 1) is_locked, which returns whether the node is locked
// 2) lock, which attempts to lock the node. If it cannot be locked, then it should return false.
//      Otherwise, it should lock it and return true.
// 3) unlock, which unlocks the node. If it cannot be unlocked, then it should return false.
//      Otherwise, it should unlock it and return true.
//
// You may augment the node to add parent pointers or any other property you would like.
// You may assume the class is used in a single-threaded program, so there is no need for actual locks or mutexes.
// Each method should run in O(h), where h is the height of the tree.

/**
 * @author ashKIK
 */
public class Problem_24 {

  private class TreeNode {

    int val;
    TreeNode parent;
    TreeNode leftChild;
    TreeNode rightChild;
    boolean locked = false;
    int lockedDescendantCount = 0;
  }

  private boolean isLocked(TreeNode node) {
    return node.locked;
  }

  // if the locking succeeds, traverse the node's ancestors and increment each one's count
  public boolean lock(TreeNode node) {
    if (isLocked(node)) {
      return true;
    }
    if (!canLockOrUnlock(node)) {
      return false;
    }
    node.locked = true;
    TreeNode parentNode = node.parent;
    while (parentNode != null) {
      parentNode.lockedDescendantCount += 1;
      parentNode = parentNode.parent;
    }
    return true;
  }

  // traverse the node's ancestors and decrement each one's count
  public boolean unlock(TreeNode node) {
    if (!isLocked(node)) {
      return true;
    }
    if (!canLockOrUnlock(node)) {
      return false;
    }
    node.locked = false;
    TreeNode parentNode = node.parent;
    while (parentNode != null) {
      parentNode.lockedDescendantCount--;
      parentNode = parentNode.parent;
    }
    return true;
  }

  private boolean canLockOrUnlock(TreeNode node) {
    if (node.lockedDescendantCount > 0) {
      return false;
    }
    TreeNode parentNode = node.parent;
    while (parentNode != null) {
      if (parentNode.locked) {
        return false;
      }
      parentNode = parentNode.parent;
    }
    return true;
  }
}
