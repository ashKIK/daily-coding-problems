import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Microsoft.
//
// Implement a URL shortener with the following methods:
//
// 1) shorten(url), which shortens the url into a six-character alphanumeric string, such as zLg6wl.
// 2) restore(short), which expands the shortened string into the original url. If no such shortened string exists, return null.
//
// Hint: What if we enter the same URL twice?

/**
 * @author ashKIK
 */
public class Problem_55 {

  private static class URLShortener {

    private Map<String, String> map = new HashMap<>();
    private Random random = new Random();
    private int size;

    URLShortener(int size) {
      this.size = size;
    }

    private String shorten(String url) {
      String shortUrl;

      do {
        shortUrl = getRandomString(size);
      } while (map.containsKey(shortUrl));

      map.put(shortUrl, url);
      return shortUrl;
    }

    private String restore(String shortUrl) {
      return map.get(shortUrl);
    }

    private String getRandomString(int size) {

      StringBuilder temp = new StringBuilder();
      for (int i = 0; i < size; i++) {
        if (random.nextBoolean()) {
          temp.append(random.nextInt(10));
        } else {
          char chr = random.nextBoolean() ? 'A' : 'a';
          chr += random.nextInt(26);
          temp.append(chr);
        }
      }

      return temp.toString();
    }
  }

  public static void main(String... args) {

    final int size = 6;
    URLShortener shortener = new URLShortener(size);

    String URL = "www.github.com";
    String shortURL;
    System.out.format(
        "%s - %s - %s%n", URL, shortURL = shortener.shorten(URL), shortener.restore(shortURL));
    System.out.format(
        "%s - %s - %s%n", URL, shortURL = shortener.shorten(URL), shortener.restore(shortURL));

    URL = "www.dailycodingproblem.com";
    System.out.format(
        "%s - %s - %s%n", URL, shortURL = shortener.shorten(URL), shortener.restore(shortURL));
    System.out.format(
        "%s - %s - %s%n", URL, shortURL = shortener.shorten(URL), shortener.restore(shortURL));

    URL = "www.apple.com";
    System.out.format(
        "%s - %s - %s%n", URL, shortURL = shortener.shorten(URL), shortener.restore(shortURL));
    System.out.format(
        "%s - %s - %s%n", URL, shortURL = shortener.shorten(URL), shortener.restore(shortURL));
  }
}
