package Problems_11_20;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// Google.
//
// Suppose we represent our file system by a string in the following manner:
//
// The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
//
// dir
//   subdir1
//   subdir2
//     file.ext
//
// The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
//
// The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
//
// dir
//   subdir1
//     file1.ext
//     subsubdir1
//   subdir2
//     subsubdir2
//       file2.ext
//
// The directory dir contains two sub-directories subdir1 and subdir2.
// subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1.
// subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
//
// We are interested in finding the longest (number of characters)
// absolute path to a file within our file system.
//
// For example, in the second example above,
// the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
// and its length is 32 (not including the double quotes).
//
// Given a string representing the file system in the above format,
// return the length of the longest absolute path to a file in the abstracted file system.
// If there is no file in the system, return 0.
//
// Note:
//
// The name of a file contains at least a period and an extension.
//
// The name of a directory or sub-directory will not contain a period.

/**
 * @author ashKIK
 */
public class Problem_17 {

  private static void addAllFiles(Deque<String> parent, String[] parts, int i, Set<String> files) {
    if (i >= parts.length) {
      return;
    }

    String part = parts[i];
    String trimPart = part.trim();
    int depth = part.length() - trimPart.length();

    if (parent.size() < depth) {
      if (parent.size() + 1 < depth) {
        throw new IllegalArgumentException();
      }
    } else {
      while (parent.size() > depth) {
        parent.removeLast();
      }
    }

    // is File
    if (trimPart.contains(".")) {
      files.add(buildFilePath(parent, trimPart));
    } else {
      parent.add(trimPart);
    }

    addAllFiles(parent, parts, i + 1, files);
  }

  private static int getLongestPath(String str) {
    Set<String> files = new HashSet<>();

    addAllFiles(new LinkedList<>(), str.split("\n"), 0, files);

    boolean seen = false;
    int best = 0;
    for (String file : files) {
      int length = file.length();
      if (!seen || length > best) {
        seen = true;
        best = length;
      }
    }
    return seen ? best : 0;
  }

  private static String buildFilePath(Queue<String> parent, String fileName) {
    StringBuilder builder = new StringBuilder();

    parent.forEach(path -> {
      if (builder.length() > 0) {
        builder.append('/');
      }
      builder.append(path);
    });

    if (builder.length() > 0) {
      builder.append('/');
    }

    builder.append(fileName);
    return builder.toString();
  }

  public static void main(String... args) {
    System.out.println(getLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));  // 20
    System.out.println(getLongestPath(
        "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));    // 32
  }
}
