package Problems_51_60;

import java.util.LinkedHashMap;
import java.util.Map;

// Google.
//
// Implement an LRU (Least Recently Used) cache.
// It should be able to be initialized with a cache size n, and contain the following methods:
//
// 1) set(key, value): sets key to value.
//    If there are already n items in the cache and we are adding a new item,
//    then it should also remove the least recently used item.
//
// 2) get(key): gets the value at key. If no such key exists, return null.
//
// Each operation should run in O(1) time.

/**
 * @author ashKIK
 */
public class Problem_52 {

  public static class LRUCache<K, V> {

    private Map<K, V> map = new LinkedHashMap<>();
    private int maxSize;

    LRUCache(int maxSize) {
      this.maxSize = maxSize;
    }

    public void set(K key, V value) {
      if (map.containsKey(key)) {
        map.remove(key);
      } else if (map.size() == maxSize) {
        map.remove(map.keySet().iterator().next());
      }

      map.put(key, value);
    }

    public V get(K key) {
      if (!map.containsKey(key)) {
        return null;
      }

      V value = map.remove(key);
      map.put(key, value);
      return value;
    }
  }

  public static void main(String... args) {

    LRUCache<String, String> cache = new LRUCache<>(3);
    cache.set("one", "1");
    cache.set("two", "2");
    cache.set("three", "3");
    cache.set("four", "4");
    System.out.println(cache.get("two"));
    System.out.println(cache.get("one"));

    cache.set("five", "5");
    System.out.println(cache.get("two"));
    System.out.println(cache.get("three"));

    cache.set("four", "4");
    System.out.println(cache.get("four"));
  }
}
