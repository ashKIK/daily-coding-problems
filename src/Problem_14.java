// Google.
//
// The area of a circle is defined as πr^2.
// Estimate π to 3 decimal places using a Monte Carlo method.
//
// Hint: The basic equation of a circle is x^2 + y^2 = r^2.

/**
 * @author ashKIK
 */
public class Problem_14 {

  private static double calculatePI(int numThrows) {
    int inCircle = 0;

    for (int i = 0; i < numThrows; i++) {

      // a square with a side of length 2 centered at 0 has x and y range of -1 to 1

      double randX = (Math.random() * 2) - 1; //range -1 to 1
      double randY = (Math.random() * 2) - 1; //range -1 to 1

      // distance from (0,0) = sqrt((x-0)^2+(y-0)^2)

      // double dist = Math.sqrt(randX * randX + randY * randY);

      double dist = Math.hypot(randX, randY);
      if (dist < 1) {
        inCircle++;
      }
    }

    return 4.0 * inCircle / numThrows;
  }

  public static void main(String... args) {

    System.out.println(calculatePI(10000));
    System.out.println(calculatePI(100000));
    System.out.println(calculatePI(1000000));
    System.out.println(calculatePI(10000000));
    System.out.println(calculatePI(100000000));
  }
}
