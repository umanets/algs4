/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int count;
    private int gridSize;
    private WeightedQuickUnionUF wquPercolate;
    private WeightedQuickUnionUF wquFull;
    private boolean[][] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        gridSize = n;
        wquPercolate = new WeightedQuickUnionUF(n * n + 2);
        wquFull = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
    }

    private void validate(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize)
            throw new IllegalArgumentException(String.format("row: %s, col: %s", row, col));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            int pos = (row - 1) * gridSize + col - 1 + 1;
            if (row == 1 && row == gridSize) {
                wquPercolate.union(pos, 0);
                wquFull.union(pos, 0);
                wquPercolate.union(pos, 2);
                wquFull.union(pos, 2);
            }
            else if (row == 1) {
                wquPercolate.union(pos, 0);
                wquFull.union(pos, 0);
                if (isOpen(row + 1, col)) {
                    wquPercolate.union(pos, pos + gridSize);
                    wquFull.union(pos, pos + gridSize);
                }
            }
            else if (row == gridSize) {
                if (isOpen(row - 1, col)) {
                    wquPercolate.union(pos, pos - gridSize);
                    wquFull.union(pos, pos - gridSize);
                }
                wquPercolate.union(pos, gridSize * gridSize + 1);
            }
            else {
                if (isOpen(row - 1, col)) {
                    wquPercolate.union(pos, pos - gridSize);
                    wquFull.union(pos, pos - gridSize);
                }

                if (isOpen(row + 1, col)) {
                    wquPercolate.union(pos, pos + gridSize);
                    wquFull.union(pos, pos + gridSize);
                }
            }

            if (col < gridSize && isOpen(row, col + 1)) {
                wquPercolate.union(pos, pos + 1);
                wquFull.union(pos, pos + 1);
            }
            if (col > 1 && isOpen(row, col - 1)) {
                wquPercolate.union(pos, pos - 1);
                wquFull.union(pos, pos - 1);
            }
            count++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int pos = (row - 1) * gridSize + col - 1 + 1;
        return wquFull.find(0) == wquFull.find(pos);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquPercolate.find(0) == wquPercolate.find(gridSize * gridSize + 1);
    }

    public static void main(String[] args) {
    }
}
