package Problems_41_50;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Facebook.
//
// Given an unordered list of flights taken by someone,
// each represented as (origin, destination) pairs, and a starting airport,
// compute the person's itinerary. If no such itinerary exists, return null.
//
// If there are multiple possible itineraries,
// return the lexicographically smallest one. All flights must be used in the itinerary.
//
// For example, given the list of flights
// [('SFO', 'HKO'), ('YYZ', 'SFO'), ('YUL', 'YYZ'), ('HKO', 'ORD')] and starting airport 'YUL',
// you should return the list ['YUL', 'YYZ', 'SFO', 'HKO', 'ORD'].
//
// Given the list of flights [('SFO', 'COM'), ('COM', 'YYZ')] and starting airport 'COM',
// you should return null.
//
// Given the list of flights [('A', 'B'), ('A', 'C'), ('B', 'C'), ('C', 'A')]
// and starting airport 'A', you should return the list ['A', 'B', 'C', 'A', 'C']
// even though ['A', 'C', 'A', 'B', 'C'] is also a valid itinerary.
//
// However, the first one is lexicographically smaller.

/**
 * @author ashKIK
 */
public class Problem_41 {

  // Hierholzer’s algorithm --> Eulerian Path
  private static List<String> findItinerary(String[][] flights, String origin) {

    // Airport list
    Set<String> airports = new HashSet<>();
    Map<String, PriorityQueue<String>> graph = new HashMap<>();

    for (String[] flight : flights) {
      Collections.addAll(airports, flight);
      graph.putIfAbsent(flight[0], new PriorityQueue<>());
      graph.get(flight[0]).offer(flight[1]);
    }

    LinkedList<String> result = new LinkedList<>();
    dfs(origin, graph, result);
    return result.containsAll(airports) ? result : null;
  }

  private static void dfs(String airport, Map<String, PriorityQueue<String>> graph,
      LinkedList<String> result) {
    PriorityQueue<String> pq = graph.get(airport);

    while (pq != null && !pq.isEmpty()) {
      dfs(pq.poll(), graph, result);
    }

    result.addFirst(airport);
  }

  public static void main(String... args) {
    String[][] flights = {{"SFO", "HKO" }, {"YYZ", "SFO" }, {"YUL", "YYZ" }, {"HKO", "ORD" }};
    System.out.println(findItinerary(flights, "YUL"));

    flights = new String[][]{{"SFO", "COM" }, {"COM", "YYZ" }};
    System.out.println(findItinerary(flights, "COM"));

    flights = new String[][]{{"A", "B" }, {"A", "C" }, {"B", "C" }, {"C", "A" }};
    System.out.println(findItinerary(flights, "A"));
  }
}
