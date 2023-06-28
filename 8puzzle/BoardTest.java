/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

public class BoardTest {
    private static Board goalBoard() {
        int n = 3;
        int tile = 0;
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = tile++;
            }
        }
        Board board = new Board(tiles);
        return board;
    }

    private static boolean toStringTest1() {
        Board board = goalBoard();
        String s1 = board.toString();
        String s2 = "3\n0 1 2\n3 4 5\n6 7 8";
        return Objects.equals(s1, s2);
    }

    public static void main(String[] args) {
        Board q = new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }
        });
        Board w = new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }
        });
        Board e = new Board(new int[][] {
                { 2, 1, 3 }, { 4, 5, 6 }, { 7, 8, 0 }
        });
        StdOut.println("q == w: " + q.equals(w));
        StdOut.println("q != e: " + !q.equals(e));

        StdOut.println("toStringTest1: " + toStringTest1());
        StdOut.println("dimension: " + (goalBoard().dimension() == 3));
        StdOut.println("hamming1: " + (goalBoard().hamming() == 8));
        StdOut.println("hamming2: " + (new Board(new int[][] {
                { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 }
        }).hamming() == 5));
        StdOut.println("hamming3: " + (new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }
        }).hamming() == 0));
        StdOut.println("mannhattan1: " + (new Board(new int[][] {
                { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 }
        }).manhattan() == 10));
        StdOut.println("mannhattan2: " + (new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }
        }).manhattan() == 0));
        StdOut.println("isGoal1: " + (!new Board(new int[][] {
                { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 }
        }).isGoal()));
        StdOut.println("isGoal2: " + (new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }
        }).isGoal()));

        Board bottomRight = new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }
        });
        StdOut.println(
                "Neighbours for bottomRight board size: " + neighborsSize(bottomRight) + " " + (
                        neighborsSize(bottomRight) == 2));
        Board middle = new Board(new int[][] {
                { 1, 2, 3 }, { 4, 0, 6 }, { 7, 8, 5 }
        });
        StdOut.println(
                "Neighbours for middle board size: " + neighborsSize(middle) + " " + (
                        neighborsSize(middle) == 4));
        String[] n = {
                "3\n1 0 3\n4 2 6\n7 8 5",
                "3\n1 2 3\n4 6 0\n7 8 5",
                "3\n1 2 3\n4 8 6\n7 0 5",
                "3\n1 2 3\n0 4 6\n7 8 5",
                };

        int si = 0;
        for (Board b : middle.neighbors()) {
            StdOut.println(Objects.equals(b.toString(), n[si++]));
        }
    }

    private static int neighborsSize(Board board) {
        int size = 0;
        for (Board b : board.neighbors()) {
            size++;
        }
        return size;
    }
}
