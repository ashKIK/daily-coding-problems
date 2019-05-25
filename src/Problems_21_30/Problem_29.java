package Problems_21_30;

// Amazon.
//
// Run-length encoding is a fast and simple method of encoding strings.
// The basic idea is to represent repeated successive characters as a single count and character.
// For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
//
// Implement run-length encoding and decoding.
// You can assume the string to be encoded have no digits and consists solely of alphabetic characters.
// You can assume the string to be decoded is valid.

/**
 * @author ashKIK
 */
public class Problem_29 {

  private static String encode(String str) {
    int strLength = str.length();

    char chr = '\0';
    int length = 0;

    StringBuilder builder = new StringBuilder(strLength + 1);

    for (int i = 0; i < strLength; i++) {
      if (chr == '\0' || chr == str.charAt(i)) {
        chr = str.charAt(i);
        length++;
      } else {
        builder.append(length).append(chr);
        chr = str.charAt(i);
        length = 1;
      }
    }

    if (chr != '\0') {
      builder.append(length).append(chr);
    }

    return builder.toString();
  }

  private static String decode(String str) {
    StringBuilder builder = new StringBuilder();
    int strLength = str.length();
    for (int i = 0; i < strLength; ) {
      int length = str.charAt(i++) - '0';
      builder.append(String.valueOf(str.charAt(i++)).repeat(Math.max(0, length)));
    }

    return builder.toString();
  }

  public static void main(String... args) {
    System.out.println(encode("AAAABBBCCDAA")); // 4A3B2C1D2A
    System.out.println(decode("4A3B2C1D2A"));   // AAAABBBCCDAA
  }
}
