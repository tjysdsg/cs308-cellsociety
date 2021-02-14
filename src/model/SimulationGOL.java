package model;

import java.util.List;
import java.util.Map;

/**
 * Simulation model of Game of Life.
 * <p>
 * No configurable option.
 * <p>
 * See also en.wikipedia.org/wiki/Conway's_Game_of_Life
 *
 * @author jt304
 */
public class SimulationGOL extends Simulation {

  private int nAlive = 0;

  public SimulationGOL(int nRows, int nCols) {
    grid = new Grid(nRows, nCols, StateGOL.DEAD, Neighborhood.Preset8());
  }

  @Override
  public <T> void setConfig(String name, T value) {
  }

  @Override
  public Map<String, Number> getStatsMap() {
    return Map.of("nAlive", nAlive);
  }

  @Override
  public String getSimType() {
    return "Game of Life";
  }

  @Override
  protected void updateNextStates() {
    boolean updated = false;
    // calculate next state
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateGOL s = (StateGOL) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        int nAliveNeighbors = 0;
        for (var neighbor : neighbors) {
          if (neighbor.getState() == StateGOL.ALIVE) {
            ++nAliveNeighbors;
          }
        }
        if (StateGOL.DEAD == s) {
          if (nAliveNeighbors == 3) {
            grid.setState(r, c, StateGOL.ALIVE);
            updated = true;
          }
        } else if (StateGOL.ALIVE == s) {
          if (nAliveNeighbors < 2 || nAliveNeighbors > 3) {
            grid.setState(r, c, StateGOL.DEAD);
            updated = true;
          }
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
        if (grid.getState(r, c).equals(StateGOL.ALIVE)) {
          ++nAlive;
        }
      }
    }
  }

}