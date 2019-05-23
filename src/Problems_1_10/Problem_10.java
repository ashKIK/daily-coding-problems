package Problems_1_10;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Apple.
//
// Implement a job scheduler which takes in a function f and an integer n,
// and calls f after n milliseconds.

/**
 * @author ashKIK
 */
public class Problem_10 {

  private static ScheduledExecutorService executor;

  public static void main(String... args) {
    executor = Executors.newSingleThreadScheduledExecutor();

    ScheduledFuture<?> scheduledFuture = solution(() -> System.out.println("Hello"), 1000);

    executor.shutdown();
    executor = null;
  }

  private static ScheduledFuture<?> solution(Runnable command, int delay) {
    return executor.schedule(command, delay, TimeUnit.MILLISECONDS);
  }
}
