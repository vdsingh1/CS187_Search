package mazes;

import java.util.List;

import search.Solver;

public class MazeDriver {

  /**
   * Supporting main method.
   */
  public static void main(String[] args) {
    MazeGenerator mg = new MazeGenerator(24, 8, 0);
    Maze m = mg.generateDFS();
    System.out.println(m.toString());
    Solver<Cell> s = new Solver<Cell>(m);
    List<Cell> r = s.solveWithBFS();
    for (Cell cell : r) {
      System.out.println(cell);
    }
    System.out.println(r.size());
  }
}
