import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Jane Street.
//
// Suppose you are given a table of currency exchange rates, represented as a 2D array.
// Determine whether there is a possible arbitrage: that is,
// whether there is some sequence of trades you can make,
// starting with some amount A of any currency,
// so that you can end up with some amount greater than A of that currency.
//
// There are no transaction costs and you can trade fractional quantities.

/**
 * @author ashKIK
 */
public class Problem_32 {

  private static List<Integer> arbitrage(double[][] rates) {

    // create new graph with same vertices and edges but modified weights
    int v = rates.length; // number of currencies
    double[][] graph = new double[v][v];

    for (int i = 0; i < v; i++) {
      for (int j = i + 1; j < v; j++) {
        // for each edge (u,v) change weight to -ln(rates[u][v])
        graph[i][j] = -Math.log(rates[i][j]);
        graph[j][i] = -Math.log(rates[j][i]);
      }
    }

    // use bellman ford to detect if there is a negative weight cycle
    double[] distance = new double[v]; // shortest distance from source vertex
    int[] parent = new int[v]; // parent of vertex in shortest-path tree

    // initialize single source
    for (int i = 0; i < v; i++) {
      distance[i] = Double.MAX_VALUE;
      parent[i] = Integer.MAX_VALUE;
    }
    distance[0] = 0; // source vertex - since graph is complete i.e. if a negative cycle exists it can be reached from
    // any vertex, source vertex can be any vertex

    // relax edges v-1 times
    for (int i = 0; i < v - 1; i++) {
      for (int w = 0; w < v; w++) {
        for (int x = w + 1; x < v; x++) {
          // relax each edge in the graph
          relax(w, x, graph, distance, parent);
          relax(x, w, graph, distance, parent);
        }
      }
    }

    // make a final pass to detect negative weight cycle
    for (int w = 0; w < v; w++) {
      for (int x = w + 1; x < v; x++) {
        if (distance[x] > distance[w] + graph[w][x]) {
          parent[x] = w;
          return findCycle(x, parent);
        }
        if (distance[w] > distance[x] + graph[x][w]) {
          parent[w] = x;
          return findCycle(w, parent);
        }
      }
    }

    return Collections.emptyList();
  }

  private static void relax(int u, int v, double[][] w, double[] distance, int[] parent) {
    if (distance[v] > distance[u] + w[u][v]) {
      distance[v] = distance[u] + w[u][v];
      parent[v] = u;
    }
  }

  private static List<Integer> findCycle(int u, int[] parent) {

    List<Integer> cycle = new LinkedList<>();

    // count the num of times a vertex is in the path
    int[] count = new int[parent.length];
    u = parent[u];

    while (true) {
      cycle.add(u);
      count[u] += 1;
      if (count[u] > 1) {
        break; // cycle
      }
      u = parent[u];
    }

    int v;
    do {
      v = cycle.get(0);
      cycle.remove(0);
    } while (v != u); // modify path to exclude vertices that are not in the cycle

    // reverse path to reflect direction of edges
    Collections.reverse(cycle);

    return cycle;
  }

  public static void main(String... args) {
    double[][] rates = {{1, 0.66, 0.77}, {1.53, 1, 1.16}, {1.30, 0.86, 1}};
    List<Integer> cycle = arbitrage(rates);
    if (cycle.size() > 0) {
      System.out.println("Arbitrage possible");
      System.out.println(cycle);
    } else {
      System.out.println("Arbitrage not possible");
    }
  }
}
