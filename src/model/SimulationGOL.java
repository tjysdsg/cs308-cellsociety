package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simulation model of Game of Life.
 * <p>
 * See {@link Simulation#setConfig(String, Object)} for general simulation options
 * <p>
 * See also en.wikipedia.org/wiki/Conway's_Game_of_Life
 *
 * @author jt304
 */
public class SimulationGOL extends Simulation {

  private static final String N_ALIVE_KEY = "nAlive";
  private int nAlive = 0;

  public SimulationGOL(int nRows, int nCols) {
    grid = new GridSq8(nRows, nCols, new State(StateEnumGOL.DEAD));
    simType = "Game of Life";
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    ret.put(N_ALIVE_KEY, nAlive);
    return ret;
  }

  @Override
  public List<String> getStatsNames() {
    ArrayList<String> ret = new ArrayList<>();
    ret.add(N_ALIVE_KEY);
    return ret;
  }

  @Override
  protected void updateNextStates() {
    boolean updated = false;
    // calculate next state
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateEnumGOL s = (StateEnumGOL) grid.getState(r, c).getStateType();
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        int nAliveNeighbors = 0;
        for (var neighbor : neighbors) {
          if (neighbor.getState().getStateType() == StateEnumGOL.ALIVE) {
            ++nAliveNeighbors;
          }
        }
        if (StateEnumGOL.DEAD == s && nAliveNeighbors == 3) {
          grid.setState(r, c, new State(StateEnumGOL.ALIVE));
          updated = true;
        } else if (StateEnumGOL.ALIVE == s && (nAliveNeighbors < 2 || nAliveNeighbors > 3)) {
          grid.setState(r, c, new State(StateEnumGOL.DEAD));
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
    nAlive = 0;
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (grid.getState(r, c).getStateType() == StateEnumGOL.ALIVE) {
          ++nAlive;
        }
      }
    }
  }

}
