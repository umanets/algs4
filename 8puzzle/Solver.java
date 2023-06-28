/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private class Node implements Comparable<Node> {
        private final Board board;
        private final Node prev;
        private final int moves;
        private final int manhattan;

        public Node(Board board, Node prevNode) {
            this.board = board;
            this.prev = prevNode;
            this.manhattan = board.manhattan();
            if (prevNode != null) moves = prevNode.moves + 1;
            else moves = 0;
        }

        public int compareTo(Node that) {
            int priorityDiff = (this.manhattan + this.moves) - (that.manhattan + that.moves);
            return priorityDiff == 0 ? this.manhattan - that.manhattan : priorityDiff;
        }
    }

    private Node solution;
    private Iterable<Board> solutionPath;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();

        solution = solutionNode(initial);
        solutionPath = pathFromNode(solution);
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.solution != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (this.solution == null) return -1;
        return this.solution.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solutionPath;
    }

    private Node solutionNode(Board initial) {
        MinPQ<Node> minpq = new MinPQ<Node>();
        minpq.insert(new Node(initial, null));
        minpq.insert(new Node(initial.twin(), null));

        while (!minpq.min().board.isGoal()) {
            Node node = minpq.delMin();
            expand(node, minpq);
        }

        Node node = minpq.min();
        if (pathFromNode(node).peek().equals(initial))
            return node;
        else
            return null;
    }

    private void expand(Node node, MinPQ<Node> pq) {
        for (Board board : node.board.neighbors()) {
            if (node.prev == null || !node.prev.board.equals(board)) {
                Node neighbor = new Node(board, node);
                pq.insert(neighbor);
            }
        }
    }

    private Stack<Board> pathFromNode(Node node) {
        if (node == null) return null;

        Stack<Board> stack = new Stack<>();

        while (node != null) {
            stack.push(node.board);
            node = node.prev;
        }
        return stack;
    }

    // test client (see below)
    public static void main(String[] args) {

    }
}
