package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simulation model of Wa-Tor.
 * <p>
 * See {@link Simulation#setConfig(String, Object)} for general simulation options
 * <p>
 * Configurable options:
 * <ul>
 *   <li>
 *     "fishBreedDuration" (int): Number of days needed for a fish to successfully reproduce, default 2
 *   </li>
 *   <li>
 *     "sharkBreedDuration" (int): Number of days needed for a shark to successfully reproduce, default 4
 *   </li>
 *   <li>
 *     "sharkStarveDuration" (int): Max number of days of starvation before a shark dies, default 6
 *   </li>
 * </ul>
 * <p>
 * See also https://www2.cs.duke.edu/courses/compsci308/spring21/assign/02_simulation/nifty/scott-wator-world/WatorWorld.htm
 *
 * @author jt304
 */
public class SimulationWaTor extends Simulation {

  private static final String N_FISH_KEY = "nFish";
  private static final String N_SHARK_KEY = "nShark";

  private int fishBreedDuration = 2;
  private int sharkBreedDuration = 4;
  private int sharkStarveDuration = 6;

  private int nFish = 0;
  private int nShark = 0;

  public SimulationWaTor(int nRows, int nCols) {
    grid = new GridSq4(nRows, nCols, new StateWaTor(StateEnumWaTor.EMPTY));
    simType = "Wa-Tor";
  }

  private List<Cell> sublistWithStateEquals(List<Cell> list, StateEnumWaTor s) {
    ArrayList<Cell> ret = new ArrayList<>();
    for (var neighbor : list) {
      if (neighbor.getState().getStateType() == s) {
        ret.add(neighbor);
      }
    }
    return ret;
  }

  @Override
  public <T> void setConfig(String name, T value) {
    super.setConfig(name, value);
    switch (name) {
      case "fishBreedDuration" -> fishBreedDuration = (int) value;
      case "sharkBreedDuration" -> sharkBreedDuration = (int) value;
      case "sharkStarveDuration" -> sharkStarveDuration = (int) value;
    }
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    ret.put(N_SHARK_KEY, nShark);
    ret.put(N_FISH_KEY, nFish);
    return ret;
  }

  @Override
  public List<String> getStatsNames() {
    ArrayList<String> ret = new ArrayList<>();
    ret.add(N_SHARK_KEY);
    ret.add(N_FISH_KEY);
    return ret;
  }

  private boolean starve(int r, int c) {
    StateWaTor s = (StateWaTor) grid.getState(r, c);

    s.incrementNDaysStarve();
    if (s.getNDaysStarve() > sharkStarveDuration) {
      grid.setState(r, c, new StateWaTor(StateEnumWaTor.EMPTY));
      return true;
    }
    return false;
  }

  private boolean move(int r, int c) {
    StateWaTor s = (StateWaTor) grid.getState(r, c);

    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateEnumWaTor.EMPTY);
    if (emptyNeighbors.size() > 0) {
      int idx = Utils.randomChoose(emptyNeighbors);
      s.setMoved(true);
      emptyNeighbors.get(idx).setState(s, true);
      grid.setState(r, c, new StateWaTor(StateEnumWaTor.EMPTY), true);
      return true;
    }
    return false;
  }

  private boolean eat(int r, int c) {
    StateWaTor s = (StateWaTor) grid.getState(r, c);
    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> fishNeighbors = sublistWithStateEquals(neighbors, StateEnumWaTor.FISH);

    if (fishNeighbors.size() > 0) {
      Cell cell = fishNeighbors.get(Utils.randomChoose(fishNeighbors));
      cell.setState(new StateWaTor(StateEnumWaTor.EMPTY), true);
      s.resetNDaysStarve();
      return true;
    }
    return false;
  }

  private boolean breed(int r, int c) {
    StateWaTor s = (StateWaTor) grid.getState(r, c);
    // assuming s is moved
    s.incrementNDaysBreed();
    int breedDuration =
        s.getStateType() == StateEnumWaTor.FISH ? fishBreedDuration : sharkBreedDuration;

    StateEnumWaTor childState = (StateEnumWaTor) s.getStateType();

    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateEnumWaTor.EMPTY);
    if (s.getNDaysBreed() > breedDuration && emptyNeighbors.size() > 0) {
      Cell cell = emptyNeighbors.get(Utils.randomChoose(emptyNeighbors));
      cell.setState(new StateWaTor(childState), true);

      s.resetNDaysBreed();
      s.setMoved(false);
      return true;
    }
    s.setMoved(false);
    return false;
  }

  /**
   * In this method, all the updates are applied immediately, since modifications of other cells can
   * directly affect how current cell updates.
   */
  @Override
  protected void updateNextStates() {
    boolean updated = false;

    // three passes
    // 1. SHARK movement and eating
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateWaTor s = (StateWaTor) grid.getState(r, c);
        if (!s.isMoved() && s.getStateType() == StateEnumWaTor.SHARK) {
          updated |= starve(r, c);
          updated |= move(r, c);
          updated |= eat(r, c);
        }
      }
    }

    // 2. FISH movement
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateWaTor s = (StateWaTor) grid.getState(r, c);
        if (!s.isMoved() && s.getStateType() == StateEnumWaTor.FISH) {
          updated |= move(r, c);
        }
      }
    }

    // 3. FISH and SHARK breeding
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (((StateWaTor) grid.getState(r, c)).isMoved()) {
          updated |= breed(r, c);
        }
      }
    }

    if (!updated) {
      isOver = true;
    }
  }

  @Override
  protected void updateStats() {
    nFish = nShark = 0;
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (grid.getState(r, c).getStateType() == StateEnumWaTor.FISH) {
          ++nFish;
        } else if (grid.getState(r, c).getStateType() == StateEnumWaTor.SHARK) {
          ++nShark;
        }
      }
    }

  }
}
