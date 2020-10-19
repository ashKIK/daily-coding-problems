// Dropbox.
//
// What does the below code snippet print out? How can we fix the anonymous functions to behave as we'd expect?
//
// functions = []
// for i in range(10):
//    functions.append(lambda : i)
//
// for f in functions:
//    print(f())
//
// Given code prints 9 ten times.

// Solution

//
// functions = []
// for i in range(10):
//    functions.append(lambda i=i: i)
//
// for f in functions:
//    print(f())


/**
 * @author ashKIK
 */
public class Problem_91 {

}
