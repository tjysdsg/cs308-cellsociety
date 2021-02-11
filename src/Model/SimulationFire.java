package Model;

import java.util.List;
import java.util.Random;

public class SimulationFire extends Simulation {

  private double probCatch = 0.5;

  public SimulationFire(int n) {
    grid = new Grid(n, n, Neighborhood.Preset4());
  }

  @Override
  public <T> void setConfig(String name, T value) {
    if (name.equals("probCatch")) {
      assert value instanceof Double;
      probCatch = (double) value;
    }
  }

  @Override
  protected void updateNextStates() {
    // calculate next state
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nRows; ++c) {
        StateFire s = (StateFire) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        switch (s) {
          case TREE -> {
            for (Cell neighbor : neighbors) {
              if ((neighbor.getState()) == StateFire.BURNING) {
                Random rand = new Random();
                if (rand.nextDouble() <= probCatch) {
                  grid.setState(r, c, StateFire.BURNING);
                  break;
                }
              }
            }
          }
          case BURNING -> {
            grid.setState(r, c, StateFire.EMPTY);
          }
          default -> {
          }
        }
      }
    }
  }

  @Override
  public List<List<State>> getGrid() {
    return null;
  }

}
