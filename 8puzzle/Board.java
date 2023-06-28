/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.ResizingArrayQueue;

public class Board {
    private int N;
    private String boardString;
    private int hamming;
    private int manhattan;
    private boolean isGoal;
    private int[][] tiles;

    // zero tile coordinates
    private int x, y;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.hamming = 0;
        this.manhattan = 0;
        this.isGoal = true;
        this.tiles = new int[N][N];
        this.boardString = null;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];

                if (tiles[i][j] == 0) {
                    x = i;
                    y = j;
                    continue;
                }

                // hamming
                if (tiles[i][j] != ((i * N) + j + 1) % (N * N)) hamming++;

                // manhattan
                int row = Math.abs((tiles[i][j] - 1) / N);
                int col = (tiles[i][j] - 1) - row * N;
                manhattan += Math.abs(row - i) + Math.abs(col - j);

                // isGoal
                this.isGoal = this.isGoal && tiles[i][j] == i * N + j + 1;
            }
        }
    }

    // move tile[i][j] to zero
    private int[][] swapZeroWith(int i, int j) {
        int[][] copy = copy();
        copy[x][y] = copy[i][j];
        copy[i][j] = 0;
        return copy;
    }

    private int[][] copy() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    // string representation of this board
    public String toString() {
        if (this.boardString != null) return this.boardString;
        StringBuilder builder = new StringBuilder();
        builder.append(N);
        builder.append(System.lineSeparator());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                builder.append(tiles[i][j]);
                builder.append(' ');
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append(System.lineSeparator());
        }
        builder.deleteCharAt(builder.length() - 1);
        this.boardString = builder.toString();
        return this.boardString;
    }

    // board dimension n
    public int dimension() {
        return N;
    }

    // number of tiles out of place
    public int hamming() {
        return this.hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return this.manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.isGoal;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.dimension() != this.dimension())
            return false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (that.tiles[i][j] != this.tiles[i][j]) return false;
            }
        }
        return true;
    }

    // all neighbors boards
    public Iterable<Board> neighbors() {
        ResizingArrayQueue<Board> neighbors = new ResizingArrayQueue<Board>();
        if (x > 0) neighbors.enqueue(new Board(swapZeroWith(x - 1, y)));
        if (y < N - 1) neighbors.enqueue(new Board(swapZeroWith(x, y + 1)));
        if (x < N - 1) neighbors.enqueue(new Board(swapZeroWith(x + 1, y)));
        if (y > 0) neighbors.enqueue(new Board(swapZeroWith(x, y - 1)));
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = copy();
        int a = -1;
        int b = -1;
        for (int i = 0; i < N * N; i++) {
            if (copy[i / N][i % N] == 0) continue;
            if (a != -1 && b != -1) break;
            if (a != -1) b = i;
            else a = i;
        }
        int c = copy[a / N][a % N];
        copy[a / N][a % N] = copy[b / N][b % N];
        copy[b / N][b % N] = c;
        return new Board(copy);
    }

    public static void main(String[] args) {

    }
}
