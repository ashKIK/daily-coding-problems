import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Uber.
//
// A rule looks like this:
//
// A NE B
//
// This means this means point A is located northeast of point B.
//
// A SW C
//
// means that point A is southwest of C.
//
// Given a list of rules, check if the sum of the rules validate. For example:
//
// A N B
// B NE C
// C N A
//
// does not validate, since A cannot be both north and south of C.
//
// A NW B
// A N B
//
// is considered valid.

/**
 * @author ashKIK
 */
public class Problem_87 {

  private static void test1() {
    String[] rules = {"A N B", "B NE C", "C N A"};
    System.out.println(validate(rules));
  }

  private static void test2() {
    String[] rules = {"A NW B", "A N B"};
    System.out.println(validate(rules));
  }

  private static void test3() {
    String[] rules = {"A N B", "C N B"};
    System.out.println(validate(rules));
  }

  static class Node {

    List<Set<Node>> edges = new ArrayList<>();
    char val;

    Node(char val) {
      this.val = val;
      for (int i = 0; i < 4; i++) {
        edges.add(new HashSet<>());
      }
    }
  }

  public static final int N = 0;
  public static final int E = 1;
  public static final int S = 2;
  public static final int W = 3;
  public static final int[] DIRS = {N, E, S, W};
  public static final Map<Character, Integer> charToDir = new HashMap<>();

  static {
    charToDir.put('N', N);
    charToDir.put('E', E);
    charToDir.put('S', S);
    charToDir.put('W', W);
  }

  private static boolean validate(String[] rules) {
    Map<Character, Node> map = new HashMap<>();

    for (String line : rules) {
      String[] rule = line.split(" ");
      System.out.println("Rule " + rule[0] + " " + rule[1] + " " + rule[2]);
      char fromVal = rule[2].charAt(0);
      char toVal = rule[0].charAt(0);

      if (!map.containsKey(fromVal)) {
        Node n = new Node(fromVal);
        map.put(fromVal, n);
      }

      if (!map.containsKey(toVal)) {
        Node n = new Node(toVal);
        map.put(toVal, n);
      }

      Node from = map.get(fromVal);
      Node to = map.get(toVal);

      for (char dirChar : rule[1].toCharArray()) {
        int dir = charToDir.get(dirChar);
        if (!isValid(from, to, dir)) {
          return false;
        }
        addEdges(from, to, dir);
      }
    }

    return true;
  }

  private static int opposite(int dir) {
    return (dir + 2) % 4;
  }

  private static boolean isValid(Node from, Node to, int newDir) {
    int oppositeDir = opposite(newDir);
    return !from.edges.get(oppositeDir).contains(to);
  }

  private static void addEdges(Node from, Node to, int newDir) {
    int oppositeDir = opposite(newDir);

    from.edges.get(newDir).add(to);
    to.edges.get(oppositeDir).add(from);

    for (int dir : DIRS) {
      if (dir == newDir) {
        continue;
      }

      for (Node neighbor : from.edges.get(dir)) {
        if (neighbor == to) {
          continue;
        }
        neighbor.edges.get(newDir).add(to);
        to.edges.get(oppositeDir).add(neighbor);
      }
    }
  }

  public static void main(String[] args) {
    test1();
    test2();
    test3();
  }
}
