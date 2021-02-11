package Model;

import java.util.List;

public class SimulationGOL extends Simulation {

  public SimulationGOL(int n) {
    grid = new Grid(n, n, StateGOL.DEAD, Neighborhood.Preset8());
  }

  @Override
  public <T> void setConfig(String name, T value) {
  }

  @Override
  protected void updateNextStates() {
    // calculate next state
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nRows; ++c) {
        StateGOL s = (StateGOL) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        int nAliveNeighbors = 0;
        for (var neighbor : neighbors) {
          if (neighbor.getState() == StateGOL.ALIVE) {
            ++nAliveNeighbors;
          }
        }
        boolean updated = false;
        switch (s) {
          case DEAD -> {
            if (nAliveNeighbors == 3) {
              grid.setState(r, c, StateGOL.ALIVE);
              updated = true;
            }
          }
          case ALIVE -> {
            if (nAliveNeighbors < 2 || nAliveNeighbors > 3) {
              grid.setState(r, c, StateGOL.DEAD);
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
