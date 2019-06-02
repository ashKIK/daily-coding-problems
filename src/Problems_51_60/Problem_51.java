package Problems_51_60;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Facebook.
//
// Given a function that generates perfectly random numbers between 1 and k (inclusive),
// where k is an input, write a function that shuffles a deck of cards represented as an array using only swaps.
//
// It should run in O(N) time.
//
// Hint: Make sure each one of the 52! permutations of the deck is equally likely.

/**
 * @author ashKIK
 */
public class Problem_51 {

  private static void shuffle(List<Integer> list) {

    Random random = new Random();

    for (int i = list.size() - 1; i >= 1; i--) {
      // generate a random number j such that 0 <= j <= i
      int j = random.nextInt(i + 1);

      // swap current element with randomly generated index
      Collections.swap(list, i, j);
    }
  }

  public static void main(String... args) {

    List<Integer> list = IntStream.rangeClosed(1, 52).boxed()
        .collect(Collectors.toCollection(() -> new ArrayList<>(52)));

    shuffle(list);

    System.out.println(list);
  }
}
