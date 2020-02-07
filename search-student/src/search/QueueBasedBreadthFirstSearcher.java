package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

  /**
   * QueueBasedBreadthFirstSearcher.
   * @param searchProblem : search problem
   */
  public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    List<T> solution = new ArrayList<T>();
    ArrayList<T> queue = new ArrayList<T>();
    T current = searchProblem.getInitialState();
    queue.add(current);
    List<T> visitedParents = new ArrayList<T>();
    visited.add(current);
    while (!searchProblem.isGoal(current)) {
      current = queue.remove(0);      
      List<T> availableMoves = new ArrayList<T>(searchProblem.getSuccessors(current));
      for (int i = 0; i < availableMoves.size(); i++) {
        if (visited.contains(availableMoves.get(i))) {
          availableMoves.remove(i);
          i--;
        } else {
          queue.add(availableMoves.get(i));
          visited.add(availableMoves.get(i));
          visitedParents.add(current);
        }
      }
    }
    while (!current.equals(searchProblem.getInitialState())) {
      solution.add(current);
      current = visitedParents.get(visited.indexOf(current) - 1);
    }
    solution.add(searchProblem.getInitialState());
    Collections.reverse(solution);
    return solution;
  }
}
