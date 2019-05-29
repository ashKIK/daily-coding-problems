package Problems_41_50;

import java.util.function.BiFunction;

// Microsoft.
//
// Suppose an arithmetic expression is given as a binary tree.
// Each leaf is an integer and each internal node is one of '+', '−', '∗', or '/'.
//
// Given the root to such a tree, write a function to evaluate it.
//
// For example, given the following tree:
//
//        *
//       / \
//      +   +
//     / \  / \
//    3  2  4  5
//
//    You should return 45, as it is (3 + 2) * (4 + 5).

/**
 * @author ashKIK
 */
public class Problem_50 {

  interface Node {

  }

  private static class Operator implements Node {

    private Node left;
    private Node right;
    private BiFunction<Double, Double, Double> function;

    Operator(BiFunction<Double, Double, Double> function, Node left, Node right) {
      this.function = function;
      this.left = left;
      this.right = right;
    }
  }

  private static double evaluate(Node node) {
    if (node instanceof ValueNode) {
      return ((ValueNode) node).val;
    }
    double left = evaluate(((Operator) node).left);
    double right = evaluate(((Operator) node).right);

    return ((Operator) node).function.apply(left, right);
  }

  private static class ValueNode implements Node {

    private double val;

    ValueNode(double val) {
      this.val = val;
    }
  }

  public static void main(String... args) {

    ValueNode node2 = new ValueNode(3);
    ValueNode node3 = new ValueNode(2);
    ValueNode node4 = new ValueNode(4);
    ValueNode node5 = new ValueNode(5);

    BiFunction<Double, Double, Double> add = Double::sum;
    BiFunction<Double, Double, Double> multiply = (one, two) -> one * two;

    Operator addLeft = new Operator(add, node3, node2);
    Operator addRight = new Operator(add, node4, node5);

    System.out.println(evaluate(new Operator(multiply, addLeft, addRight)));
  }
}
