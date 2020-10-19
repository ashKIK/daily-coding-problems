import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

// Facebook.
//
// Given a stream of elements too large to store in memory,
// pick a random element from the stream with uniform probability.

/**
 * @author ashKIK
 */
public class Problem_15 {

  private static class Packet {

    int result;
    int count = 1;
  }

  private static final Random random = new Random();

  private static int random(Stream<Integer> stream) {

    Packet packet = new Packet();

    stream.forEachOrdered(x -> {
      if (packet.count == 1) {
        packet.result = x;
      } else {
        if (random.nextInt(packet.count) == 0) {
          packet.result = x;
        }
      }
      packet.count++;
    });

    return packet.result;
  }

  public static void main(String... args) {

    Integer[] list = {1, 3, 5, 6, 7, 9, 13, 33};

    for (int i = 0; i < 10; i++) {
      System.out.println(random(Arrays.stream(list)));
    }
  }
}
