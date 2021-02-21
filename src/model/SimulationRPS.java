package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Simulation model of Rock, Paper, Scissors.
 * <p>
 * See {@link Simulation#setConfig(String, Object)} for general simulation options
 * <p>
 * Configurable options:
 * <ul>
 *   <li>
 *      "threshold" (int): how many neighbors beat the current cell is needed for the current cell to change.
 *   </li>
 * </ul>
 * <p>
 * See also https://softologyblog.wordpress.com/2018/03/23/rock-paper-scissors-cellular-automata/
 *
 * @author jt304
 */
public class SimulationRPS extends Simulation {

  private double threshold = 4;
  private int nRocks = 0;
  private int nPapers = 0;
  private int nScissors = 0;

  /**
   * .get(s) returns a list of states that can beat s
   */
  private static final Map<StateRPS, StateRPS[]> WINNERS = Map.of(
      StateRPS.SCISSORS, new StateRPS[]{StateRPS.ROCK},
      StateRPS.PAPER, new StateRPS[]{StateRPS.SCISSORS},
      StateRPS.ROCK, new StateRPS[]{StateRPS.PAPER}
  );

  public SimulationRPS(int nRows, int nCols) {
    grid = new GridSq(nRows, nCols, StateRPS.ROCK, Neighborhood.Square8());
  }

  @Override
  public <T> void setConfig(String name, T value) {
    super.setConfig(name, value);
    if (name.equals("threshold")) {
      assert value instanceof Double;
      threshold = (int) value;
    }
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    ret.put("nRocks", nRocks);
    ret.put("nPapers", nPapers);
    ret.put("nScissors", nScissors);
    return ret;
  }

  @Override
  public String getSimType() {
    return "Rock, Paper, Scissors";
  }

  @Override
  protected void updateNextStates() {
    boolean updated = false;

    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateRPS s = (StateRPS) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);

        // find who can win me
        StateRPS[] winners = WINNERS.get(s);
        // init win counts of every winner
        Map<StateRPS, Integer> nWins = new HashMap<>();
        for (var w : winners) {
          nWins.put(w, 0);
        }

        // count the wins
        for (Cell neighbor : neighbors) {
          StateRPS neighborState = (StateRPS) neighbor.getState();
          int prevCount = nWins.getOrDefault(neighborState, -1);
          if (prevCount != -1) {
            nWins.put(neighborState, prevCount + 1);
          }
        }

        // find the max wins
        var maxWins = Collections
            .max(nWins.entrySet(), Comparator.comparingInt(Entry::getValue));

        // check if larger than threshold, if so, change the current state
        // TODO: add randomness
        if (maxWins.getValue() > threshold) {
          setState(r, c, maxWins.getKey(), false);
          updated = true;
        }

      }
    }
    if (!updated) {
      isOver = true;
    }
  }

  @Override
  protected void updateStats() {
    nRocks = nPapers = nScissors = 0;
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (grid.getState(r, c).equals(StateRPS.ROCK)) {
          ++nRocks;
        } else if (grid.getState(r, c).equals(StateRPS.PAPER)) {
          ++nPapers;
        } else if (grid.getState(r, c).equals(StateRPS.SCISSORS)) {
          ++nScissors;
        }
      }
    }
  }

}
