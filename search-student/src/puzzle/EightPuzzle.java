package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * The spaces in an 8-puzzle are indexed as follows:
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {
  List<Integer> start = new ArrayList<Integer>();

  /**
   * Creates a new instance of the 8 puzzle with the given starting values.
   * The values are indexed as described above, and should contain exactly the
   * nine integers from 0 to 8.
   * 
   * @param startingValues
   *            the starting values, 0 -- 8
   * @throws IllegalArgumentException
   *             if startingValues is invalid
   */
  public EightPuzzle(List<Integer> startingValues) {
    if (startingValues.size() != 9) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i <= 8; i++) {
      if (!startingValues.contains(i)) {
        throw new IllegalArgumentException();
      }
    }
    start = startingValues;
  }

  @Override
  public List<Integer> getInitialState() {
    return start;
  }

  @Override
  public List<List<Integer>> getSuccessors(List<Integer> currentState) {
    List<List<Integer>> finalList = new ArrayList();
    int zeroIndex = currentState.indexOf(0);
    int leftIndex;
    int rightIndex;
    
    if (zeroIndex == 1 || zeroIndex == 4 || zeroIndex == 7 || zeroIndex == 2 || zeroIndex == 5 || zeroIndex == 8) {
      leftIndex = zeroIndex - 1;
    } else {
      leftIndex = -1;
    }
    if (zeroIndex == 1 || zeroIndex == 4 || zeroIndex == 7 || zeroIndex == 0 || zeroIndex == 3 || zeroIndex == 6) {
      rightIndex = zeroIndex + 1;
    } else {
      rightIndex = -1;
    }
    int topIndex = zeroIndex - 3;
    int bottomIndex = zeroIndex + 3;
    if (topIndex >= 0) {
      List<Integer> swapTop = new ArrayList<Integer>(currentState);
      int top = currentState.get(topIndex);
      swapTop.set(topIndex, 0);
      swapTop.set(zeroIndex, top);
      finalList.add(swapTop);
    }
    if (bottomIndex <= 8) {
      List<Integer> swapBot = new ArrayList<Integer>(currentState);
      int bot = currentState.get(bottomIndex);
      swapBot.set(bottomIndex, 0);
      swapBot.set(zeroIndex, bot);
      finalList.add(swapBot);
    }
    if (rightIndex >= 0) {
      List<Integer> swapRight = new ArrayList<Integer>(currentState);
      int right = currentState.get(rightIndex);
      swapRight.set(rightIndex, 0);
      swapRight.set(zeroIndex, right);
      finalList.add(swapRight);
    }
    if (leftIndex >= 0) {
      List<Integer> swapLeft = new ArrayList<Integer>(currentState);
      int left = currentState.get(leftIndex);
      swapLeft.set(leftIndex, 0);
      swapLeft.set(zeroIndex, left);
      finalList.add(swapLeft);
    }
    return finalList;
  }


  @Override
  public boolean isGoal(List<Integer> state) {
    // TODO
    List<Integer> goalList = new ArrayList<Integer>();
    for (int i = 1; i <= 8; i++) {
      goalList.add(i);
    }
    goalList.add(0);
    return state.equals(goalList);
  }

  /**
   * supporting man method.
   */
  public static void main(String[] args) {
    EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
        4, 0, 6, 7, 5, 8 }));

    List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
    for (List<Integer> l : r) {
      System.out.println(l);
    }
  }
}
