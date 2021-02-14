package Model;

import java.util.List;

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

  public SimulationPercolation(int nRows, int nCols) {
    grid = new Grid(nRows, nCols, StatePercolation.OPEN, Neighborhood.Preset8());
  }

  @Override
  public <T> void setConfig(String name, T value) {
  }

  @Override
  protected void updateNextStates() {
    boolean updated = false;
    // calculate next state
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nCols; ++c) {
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

}
