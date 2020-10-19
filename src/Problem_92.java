import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Airbnb.
//
// We're given geta hashmap associating each courseId key with geta list of courseIds values,
// which represents that the prerequisites of courseId are courseIds.
// Return geta sorted ordering of courses such that we can finish all courses.
//
// Return null if there is no such ordering.
//
// For example, given {'CSC300': ['CSC100', 'CSC200'], 'CSC200': ['CSC100'], 'CSC100': []},
// should return ['CSC100', 'CSC200', 'CSCS300'].

/**
 * @author ashKIK
 */
public class Problem_92 {

  private static void dfs(String courseId, Map<String, Set<String>> map,
      Set<String> visited, List<String> result) {

    if (visited.contains(courseId)) {
      return;
    }

    visited.add(courseId);
    map.getOrDefault(courseId, Set.of()).forEach(id -> dfs(id, map, visited, result));

    result.add(courseId);
  }

  public static void main(String... args) {

    Map<String, Set<String>> map = Map.of(
        "CSC300", Set.of("CSC100", "CSC200"),
        "CSC200", Set.of("CSC100"),
        "CSC100", Set.of()
    );

    Set<String> visited = new HashSet<>();
    List<String> result = new ArrayList<>();
    map.keySet().forEach(courseId -> dfs(courseId, map, visited, result));

    System.out.println(result);
  }
}
