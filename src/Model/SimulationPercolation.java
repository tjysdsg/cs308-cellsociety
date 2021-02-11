package Model;

import java.util.List;

public class SimulationPercolation extends Simulation {

  public SimulationPercolation(int n) {
    grid = new Grid(n, n, StatePercolation.OPEN, Neighborhood.Preset8());
  }

  @Override
  public <T> void setConfig(String name, T value) {
  }

  @Override
  protected void updateNextStates() {
    // calculate next state
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nRows; ++c) {
        StatePercolation s = (StatePercolation) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        int nPercolatedNeighbors = 0;
        for (var neighbor : neighbors) {
          if (neighbor.getState() == StatePercolation.PERCOLATED) {
            ++nPercolatedNeighbors;
          }
        }
        boolean updated = false;
        switch (s) {
          case OPEN -> {
            if (nPercolatedNeighbors >= 1) {
              grid.setState(r, c, StatePercolation.PERCOLATED);
              updated = true;
            }
          }
          default -> {
          }
        }
        if (!updated) {
          grid.setState(r, c, s);
        }
      }
    }
  }

}
