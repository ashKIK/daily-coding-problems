package Problems_61_70;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// Google.
//
// Implement an LFU (Least Frequently Used) cache.
// It should be able to be initialized with a cache size n, and contain the following methods:
//
// 1) set(key, value): sets key to value.
//    If there are already n items in the cache and we are adding a new item,
//    then it should also remove the least frequently used item.
//    If there is a tie, then the least recently used key should be removed.
// 2) get(key): gets the value at key. If no such key exists, return null.
//
// Each operation should run in O(1) time.

/**
 * @author ashKIK
 */
public class Problem_67 {

  private static class LFUCache {

    private static class Node {

      int key;
      int val;
      int freq;

      Node(int k, int v) {
        this.key = k;
        this.val = v;
        this.freq = 1;
      }
    }

    private Map<Integer, Node> values;
    private Map<Integer, Set<Integer>> freqs;

    private int minFreq = 1;
    private int cap;
    private int count = 0;

    LFUCache(int capacity) {
      this.values = new HashMap<>();
      this.freqs = new HashMap<>();
      this.cap = capacity;
      this.freqs.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {

      if (!values.containsKey(key)) {
        return -1;
      }

      Node n = values.get(key);
      freqs.get(n.freq).remove(key);
      n.freq++;

      if (!freqs.containsKey(n.freq)) {
        freqs.put(n.freq, new LinkedHashSet<>());
      }

      freqs.get(n.freq).add(key);
      if (n.freq - 1 == minFreq && freqs.get(n.freq - 1).size() == 0) {
        minFreq = n.freq;
      }

      return n.val;
    }

    public void set(int key, int value) {

      if (cap == 0) {
        return;
      }

      if (values.containsKey(key)) {
        values.get(key).val = value;
        get(key);
        return;
      }

      if (count == cap) {
        count--;
        int minKey = freqs.get(minFreq).iterator().next();
        freqs.get(minFreq).remove(minKey);
        values.remove(minKey);
      }

      Node n = new Node(key, value);
      values.put(key, n);
      minFreq = 1;
      freqs.get(1).add(key);
      count++;
    }
  }

  public static void main(String... args) {

    LFUCache cache = new LFUCache(4);
    cache.set(1, 1);
    cache.set(2, 2);
    cache.set(3, 3);
    cache.set(4, 4);
    System.out.println(cache.get(2));
    System.out.println(cache.get(1));

    cache.set(5, 5);
    System.out.println(cache.get(2));
    System.out.println(cache.get(3));

    cache.set(4, 14);
    System.out.println(cache.get(4));
  }
}
