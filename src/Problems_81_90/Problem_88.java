package Problems_81_90;

// ContextLogic.
//
// Implement division of two positive integers without using the division, multiplication, or modulus operators.
// Return the quotient as an integer, ignoring the remainder.

/**
 * @author ashKIK
 */
public class Problem_88 {

  // Quotient = sign * exp(log(dividend) – log(divisor))
  private static long divide(long dividend, long divisor) throws Exception {

    long sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

    dividend = Math.abs(dividend);
    divisor = Math.abs(divisor);

    if (divisor == 0) {
      throw new Exception("Division By Zero Error!");
    }

    if (dividend == 0) {
      return 0;
    }

    if (divisor == 1) {
      return (sign * dividend);
    }

    return (long) Math.floor(sign * (Math.exp(Math.log(dividend) - Math.log(divisor))));
  }

  // Keep subtracting the divisor from dividend until dividend becomes less than divisor
  private static long divide2(long dividend, long divisor) {

    long sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

    dividend = Math.abs(dividend);
    divisor = Math.abs(divisor);

    long quotient = 0;

    while (dividend >= divisor) {
      dividend -= divisor;
      quotient++;
    }

    return sign * quotient;
  }

  private static long divide3(long dividend, long divisor) {

    long sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

    dividend = Math.abs(dividend);
    divisor = Math.abs(divisor);

    long quotient = 0;
    long temp = 0;

    for (int i = 31; i >= 0; i--) {
      if (temp + (divisor << i) <= dividend) {
        temp += divisor << i;
        quotient |= 1L << i;
      }
    }

    return (sign * quotient);
  }

  public static void main(String... args) throws Exception {

    System.out.println(divide3(10, 10));
    System.out.println(divide3(10, 3));
    System.out.println(divide3(10, 2));
    System.out.println(divide3(10, 1));
    System.out.println(divide3(10, 0));
  }
}
