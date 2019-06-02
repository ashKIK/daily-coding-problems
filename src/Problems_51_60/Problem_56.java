package Problems_51_60;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// Google.
//
// Given an undirected graph represented as an adjacency matrix and an integer k,
// write a function to determine whether each vertex in the graph can be colored such that
// no two adjacent vertices share the same color using at most k colors.

/**
 * @author ashKIK
 */
public class Problem_56 {

  public static class Graph {

    private int vCount;
    private ArrayList[] adj;

    int getVCount() {
      return vCount;
    }

    Graph(int vCount) {
      this.vCount = vCount;
      adj = new ArrayList[vCount];
      for (int i = 0; i < vCount; i++) {
        adj[i] = new ArrayList<>();
      }
    }

    void addEdge(int i, int j) {
      adj[i].add(j);
      adj[j].add(i);
    }

    public void removeEdge(int i, int j) {
      Iterator<Integer> it = adj[i].iterator();
      while (it.hasNext()) {
        if (it.next() == j) {
          it.remove();
          break;
        }
      }
      Iterator it2 = adj[j].iterator();
      while (it.hasNext()) {
        if (it.next() == i) {
          it.remove();
          return;
        }
      }
    }

    boolean hasEdge(int i, int j) {
      return adj[i].contains(j);
    }

    ArrayList neighbours(int vertex) {
      return adj[vertex];
    }

    void printGraph() {
      for (int i = 0; i < vCount; i++) {
        ArrayList edges = neighbours(i);
        System.out.print(i + ": ");
        for (Object edge : edges) {
          System.out.print(edge + " ");
        }
        System.out.println();
      }
    }
  }

  // Greedy Coloring Function
  private static void greedyColoring(Graph g) {
    int V = g.getVCount();

    int[] colors = new int[V];

    Arrays.fill(colors, -1);

    // assign first color (0) to first vertex
    colors[0] = 0;

    // boolean array that shows us which colors
    // are still available
    boolean[] available = new boolean[V];

    // starting off, all colors are available
    Arrays.fill(available, true);

    // assign colors to the remaining V-1 vertices
    for (int u = 1; u < V; u++) {
      // process adjacent vertices and flag
      // their colors as unavailable
      for (int i : (Iterable<Integer>) g.neighbours(u)) {
        if (colors[i] != -1) {
          available[colors[i]] = false;
        }
      }

      // find the first available color
      int cr;
      for (cr = 0; cr < V; cr++) {
        if (available[cr]) {
          break;
        }
      }

      // assign the first available color
      colors[u] = cr;

      // reset values back to true for the next iteration
      Arrays.fill(available, true);
    }

    printColors(colors);
  }


  // Backtracking Coloring Utility Functions
  private static boolean isSafe(int v, Graph g, int[] colors, int cr) {
    for (int i = 0; i < g.getVCount(); i++) {
      if (g.hasEdge(v, i) && cr == colors[i]) {
        return false;
      }
    }
    return true;

  }

  private static boolean graphColoringUtil(Graph g, int m, int[] colors, int v) {
    // all vertices have a color then just true
    if (v == g.getVCount()) {
      return true;
    }

    // try different colors for v
    for (int cr = 1; cr <= m; cr++) {
      // Check if assignment of color cr to v is fine
      if (isSafe(v, g, colors, cr)) {
        colors[v] = cr;
        // recur to assign colors to rest of the vertices
        if (graphColoringUtil(g, m, colors, v + 1)) {
          return true;
        }

        // If assigning color cr doesn't lead
        // to a solution then remove
        colors[v] = 0;
      }
    }

    // if no color can be assigned then return false
    return false;
  }

  private static void backTrackingColoring(Graph g, int m) {
    int V = g.getVCount();

    int[] colors = new int[V];

    Arrays.fill(colors, 0);

    // call graphColoringUtil for vertex 0
    if (!graphColoringUtil(g, m, colors, 0)) {
      System.out.println("Solution does not exist");
    }

    printColors(colors);
  }

  private static void printColors(int[] colors) {
    for (int i = 0; i < colors.length; i++) {
      System.out.println("Vertex " + i + " --->  Color " + colors[i]);
    }
  }

  public static void main(String[] args) {
    // Greedy Coloring
    System.out.println("GREEDY ALGORITHM");
    Graph g1 = new Graph(5);

    System.out.println("Graph:");
    // add Edges
    g1.addEdge(0, 1);
    g1.addEdge(0, 2);
    g1.addEdge(1, 2);
    g1.addEdge(1, 3);
    g1.addEdge(2, 3);
    g1.addEdge(3, 4);
    g1.printGraph();

    greedyColoring(g1);

    System.out.println("=".repeat(25));

    // Backtracking Coloring
    System.out.println("BACKTRACKING ALGORITHM");
    Graph g2 = new Graph(5);

    System.out.println("Graph:");
    g2.addEdge(0, 1);
    g2.addEdge(0, 2);
    g2.addEdge(1, 2);
    g2.addEdge(1, 3);
    g2.addEdge(2, 3);
    g2.addEdge(3, 4);
    g2.printGraph();

    backTrackingColoring(g2, 3);
  }
}
