package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simulation model of Percolation.
 * <p>
 * No configurable option.
 * <p>
 * See also https://www2.cs.duke.edu/courses/compsci308/spring21/assign/02_simulation/PercolationCA.pdf
 *
 * @author jt304
 */
public class SimulationPercolation extends Simulation {

  private int nPercolated = 0;

  public SimulationPercolation(int nRows, int nCols) {
    grid = new Grid(nRows, nCols, StatePercolation.OPEN, Neighborhood.Preset8());
  }

  @Override
  public <T> void setConfig(String name, T value) {
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    ret.put("nPercolated", nPercolated);
    return ret;
  }

  @Override
  public String getSimType() {
    return "Percolation";
  }

  @Override
  protected void updateNextStates() {
    boolean updated = false;
    // calculate next state
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StatePercolation s = (StatePercolation) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        int nPercolatedNeighbors = 0;
        for (var neighbor : neighbors) {
          if (neighbor.getState() == StatePercolation.PERCOLATED) {
            ++nPercolatedNeighbors;
          }
        }
        if (StatePercolation.OPEN.equals(s)) {
          if (nPercolatedNeighbors >= 1) {
            grid.setState(r, c, StatePercolation.PERCOLATED);
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
    nPercolated = 0;
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (grid.getState(r, c).equals(StatePercolation.PERCOLATED)) {
          ++nPercolated;
        }
      }
    }
  }

}
