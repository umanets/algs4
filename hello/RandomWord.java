/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Objects;

public class RandomWord {
    public static void main(String[] args) {
        int wordIndex = 0;
        String result = "";
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();

            if (Objects.equals(result, "")) {
                result = word;
            }

            if (StdRandom.bernoulli(1.0 / ++wordIndex)) {
                result = word;
            }
        }
        StdOut.println(result);
    }
}
