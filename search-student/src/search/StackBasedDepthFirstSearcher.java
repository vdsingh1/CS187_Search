package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

  /**
   * StackBasedDepthFirstSearcher.
   * @param searchProblem : search problem
   */
  public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    Stack<T> stack = new Stack<T>();
    T current = searchProblem.getInitialState();
    stack.push(current);
    visited.add(current);
   
    while (!searchProblem.isGoal(current)) {
      List<T> availableMoves = new ArrayList<T>(searchProblem.getSuccessors(current));
      for (int i = 0; i < availableMoves.size(); i++) {
        if (visited.contains(availableMoves.get(i))) {
          availableMoves.remove(i);
          i--;
        }
      }
      T next;
      if (availableMoves.size() > 0) {
        next = availableMoves.get(0);
      } else {
        while (true) {
          current = stack.peek();
          availableMoves = new ArrayList<T>(searchProblem.getSuccessors(current));
          for (int i = 0; i < availableMoves.size(); i++) {
            if (visited.contains(availableMoves.get(i))) {
              availableMoves.remove(i);
              i--;
            }
          }
          if (availableMoves.size() > 0) {
            break;
          }
          stack.pop();
        }
        next = availableMoves.get(0);
      }
      current = next;
      stack.push(current);
      visited.add(current);
    }
    List<T> solution = new ArrayList<T>();
    Stack<T> tempStack = new Stack<T>();
    while (!stack.isEmpty()) {
      tempStack.push(stack.pop());
    }
    while (!tempStack.isEmpty()) {
      solution.add(tempStack.pop());
    }
    return solution;
  }
}
