package Problems_81_90;

// Microsoft.
//
// Using a read7() method that returns 7 characters from a file, implement readN(n) which reads n characters.
//
// For example, given a file with the content “Hello world”, three read7() returns “Hello w”, “orld” and then “”.

/**
 * @author ashKIK
 */
public class Problem_82 {

  // dummy read7()
  private static int read7(char[] buffer7) {
    return 7;
  }

  private static int offset = 0;
  private static int bufferSize = 0;
  private static char[] buffer7 = new char[7];

  // call read() multiple times
  private static int readM(char[] buffer, int n) {

    int charsRead = 0;
    boolean eof = false;

    while (!eof && charsRead < n) {
      if (bufferSize == 0) {
        bufferSize = read7(buffer7);
        eof = bufferSize < 7;
      }

      int bytes = Math.min(n - charsRead, bufferSize);
      System.arraycopy(buffer7, offset, buffer, charsRead, bytes);

      offset = (offset + bytes) % 7;
      bufferSize -= bytes;
      charsRead += bytes;
    }

    return charsRead;
  }

  // call read() once
  private static int readO(char[] buffer, int n) {

    int charsRead = 0;
    boolean eof = false;
    char[] buf7 = new char[7];

    while (!eof && charsRead < n) {
      int size = read7(buf7);
      if (size < 7) {
        eof = true;
      }

      if (charsRead + size > n) {
        size = n - charsRead;
      }

      System.arraycopy(buf7, 0, buffer, charsRead, size);
      charsRead += size;
    }

    return charsRead;
  }
}
