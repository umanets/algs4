/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class SolverTest {
    public static void main(String[] args) {
        System.out.println("Goal board need moves 0: " + (new Solver(new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }
        })).moves() == 0));

        System.out.println("1 move board need moves 1: " + (new Solver(new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 7, 0, 8 }
        })).moves() == 1));

        System.out.println("unsolvable board need moves -1: " + (new Solver(new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 }
        })).moves() == -1));

        Board twin = new Board(new int[][] {
                { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 }
        }).twin();
        System.out.println(
                "twin from unsolvable is solvable: " + (new Solver(twin).moves() != -1));
    }
}
