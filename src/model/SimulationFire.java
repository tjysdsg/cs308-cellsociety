package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Simulation model of Fire.
 * <p>
 * See {@link Simulation#setConfig(String, Object)} for general simulation options
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

  private static final String N_TREES_KEY = "nTrees";
  private static final String N_BURNING_KEY = "nBurning";

  private double probCatch = 0.5;
  private int nTrees = 0;
  private int nBurning = 0;

  public SimulationFire(int nRows, int nCols) {
    grid = new GridSq4(nRows, nCols, new State(StateEnumFire.EMPTY));
    simType = "Fire";
  }

  @Override
  public <T> void setConfig(String name, T value) {
    super.setConfig(name, value);
    if (name.equals("probCatch")) {
      assert value instanceof Double;
      probCatch = (double) value;
    }
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    ret.put(N_TREES_KEY, nTrees);
    ret.put(N_BURNING_KEY, nBurning);
    return ret;
  }

  @Override
  public List<String> getStatsNames() {
    ArrayList<String> ret = new ArrayList<>();
    ret.add(N_TREES_KEY);
    ret.add(N_BURNING_KEY);
    return ret;
  }

  @Override
  protected void updateNextStates() {
    int nBurning = 0;
    // calculate next state
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateEnumFire s = (StateEnumFire) grid.getState(r, c).getStateType();
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        if (StateEnumFire.TREE == s) {
          for (Cell neighbor : neighbors) {
            if (neighbor.getState().getStateType() == StateEnumFire.BURNING) {
              Random rand = new Random();
              if (rand.nextDouble() <= probCatch) {
                grid.setState(r, c, new State(StateEnumFire.BURNING));
                break;
              }
            }
          }
        } else if (StateEnumFire.BURNING == s) {
          ++nBurning;
          grid.setState(r, c, new State(StateEnumFire.EMPTY));
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
        if (grid.getState(r, c).getStateType() == StateEnumFire.TREE) {
          ++nTrees;
        } else if (grid.getState(r, c).getStateType() == StateEnumFire.BURNING) {
          ++nBurning;
        }
      }
    }
  }

}
