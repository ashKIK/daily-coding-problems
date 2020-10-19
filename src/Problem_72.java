import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Google.
//
// In a directed graph, each node is assigned an uppercase letter.
// We define a path's value as the number of most frequently-occurring letter along that path.
// For example, if a path in the graph goes through "ABACA", the value of the path is 3,
// since there are 3 occurrences of 'A' on the path.
//
// Given a graph with n nodes and m directed edges, return the largest value path of the graph.
// If the largest value is infinite, then return null.
//
// The graph is represented with a string and an edge list.
// The i-th character represents the uppercase letter of the i-th node.
// Each tuple in the edge list (i, j) means there is a directed edge from the i-th node to the j-th node.
// Self-edges are possible, as well as multi-edges.
//
// For example, the following input graph:
//
// ABACA
//
// [(0, 1),
//  (0, 2),
//  (2, 3),
//  (3, 4)]
//
// Would have maximum value 3 using the path of vertices [0, 2, 3, 4], (A, A, C, A).
//
// The following input graph:
//
// A
//
// [(0, 0)]
//
// Should return null, since we have an infinite loop.

/**
 * @author ashKIK
 */
public class Problem_72 {

  private final int UNVISITED = 0;
  private final int VISITING = 1;
  private final int VISITED = 2;

  private boolean dfs(String str, Map<Integer, List<Integer>> graph,
      int[] states, int[][] maxPaths, int node) {

    if (states[node] == VISITED) {
      return false;
    } else if (states[node] == VISITING) {
      return true;
    }

    states[node] = VISITING;
    for (int neighbor : graph.get(node)) {
      dfs(str, graph, states, maxPaths, neighbor);
    }

    for (int neighbor : graph.get(node)) {
      for (int letter = 0; letter < 26; letter++) {
        maxPaths[node][letter] = Math.max(maxPaths[node][letter], maxPaths[neighbor][letter]);
      }
    }

    maxPaths[node][str.charAt(node) - 'A']++;
    states[node] = VISITED;

    return false;
  }

  public int maxGraphPath(String str, int[][] edges) {
    int strLength = str.length();

    Map<Integer, List<Integer>> graph = new HashMap<>();

    for (int i = 0; i < strLength; i++) {
      graph.put(i, new ArrayList<>());
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
    }

    int[] states = new int[strLength];
    Arrays.fill(states, UNVISITED);

    int[][] maxPaths = new int[strLength][26];

    for (int node = 0; node < strLength; node++) {
      if (states[node] == UNVISITED) {
        if (dfs(str, graph, states, maxPaths, node)) {
          return Integer.MAX_VALUE;
        }
      }
    }

    int maxValue = Integer.MIN_VALUE;
    for (int i = 0; i < strLength; i++) {
      for (int j = 0; j < 26; j++) {
        maxValue = Math.max(maxPaths[i][j], maxValue);
      }
    }

    return maxValue;
  }

  public static void main(String... args) {
    Problem_72 problem_72 = new Problem_72();

    String str = "ABACA";
    int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {3, 4}};

    int maxValue = problem_72.maxGraphPath(str, edges);
    System.out.print(maxValue);
  }
}
