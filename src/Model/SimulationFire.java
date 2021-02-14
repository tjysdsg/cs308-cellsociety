package Model;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Simulation model of Fire.
 * <p>
 * Configurable options:
 * <ul>
 *   <li>
 *     "probCatch" (double): The probability of a tree catching fire, default 0.5
 *   </li>
 * </ul>
 * <p>
 * See also https://www2.cs.duke.edu/courses/compsci308/spring21/assign/02_simulation/nifty/shiflet-fire/
 *
 * @author jt304
 */
public class SimulationFire extends Simulation {

  private double probCatch = 0.5;
  private int nTrees = 0;
  private int nBurning = 0;

  public SimulationFire(int nRows, int nCols) {
    grid = new Grid(nRows, nCols, StateFire.EMPTY, Neighborhood.Preset4());
  }

  @Override
  public <T> void setConfig(String name, T value) {
    if (name.equals("probCatch")) {
      assert value instanceof Double;
      probCatch = (double) value;
    }
  }

  @Override
  public Map<String, Number> getStatsMap() {
    return Map.of("nTrees", nTrees, "nBurning", nBurning);
  }

  @Override
  public String getSimType() {
    return "Fire";
  }

  @Override
  protected void updateNextStates() {
    int nBurning = 0;
    // calculate next state
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateFire s = (StateFire) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        if (StateFire.TREE == s) {
          for (Cell neighbor : neighbors) {
            if ((neighbor.getState()) == StateFire.BURNING) {
              Random rand = new Random();
              if (rand.nextDouble() <= probCatch) {
                grid.setState(r, c, StateFire.BURNING);
                break;
              }
            }
          }
        } else if (StateFire.BURNING == s) {
          ++nBurning;
          grid.setState(r, c, StateFire.EMPTY);
        }
      }
    }
    if (nBurning == 0) {
      isOver = true;
    }
  }

  @Override
  protected void updateStats() {
    nTrees = nBurning = 0;
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (grid.getState(r, c).equals(StateFire.TREE)) {
          ++nTrees;
        } else if (grid.getState(r, c).equals(StateFire.BURNING)) {
          ++nBurning;
        }
      }
    }
  }

}
