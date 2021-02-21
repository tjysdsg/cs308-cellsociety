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

  private int fishBreedDuration = 2;
  private int sharkBreedDuration = 4;
  private int sharkStarveDuration = 6;

  private int nFish = 0;
  private int nShark = 0;

  public SimulationWaTor(int nRows, int nCols) {
    grid = new GridSq(nRows, nCols, new StateWator(StateEnumWaTor.EMPTY), Neighborhood.Square4());
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
    ret.put("nFish", nFish);
    ret.put("nShark", nShark);
    return ret;
  }

  @Override
  public String getSimType() {
    return "Wa-Tor";
  }

  private boolean starve(int r, int c) {
    StateEnumWaTor s = (StateEnumWaTor) grid.getState(r, c).getStateType();
    if (++s.nDaysStarve > sharkStarveDuration) {
      grid.setState(r, c, new StateWator(StateEnumWaTor.EMPTY));
      return true;
    }
    return false;
  }

  private boolean move(int r, int c) {
    StateEnumWaTor s = (StateEnumWaTor) grid.getState(r, c).getStateType();

    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateEnumWaTor.EMPTY);
    if (emptyNeighbors.size() > 0) {
      int idx = Utils.randomChoose(emptyNeighbors);
      s.setMoved(true);
      emptyNeighbors.get(idx).setState(s, true);
      grid.setState(r, c, StateEnumWaTor.EMPTY(), true);
      return true;
    }
    return false;
  }

  private boolean eat(int r, int c) {
    StateEnumWaTor s = (StateEnumWaTor) grid.getState(r, c).getStateType();
    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> fishNeighbors = sublistWithStateEquals(neighbors, StateEnumWaTor.FISH());

    if (fishNeighbors.size() > 0) {
      Cell cell = fishNeighbors.get(Utils.randomChoose(fishNeighbors));
      cell.setState(StateEnumWaTor.EMPTY(), true);
      s.nDaysStarve = 0;
      return true;
    }
    return false;
  }

  private boolean breed(int r, int c) {
    StateEnumWaTor s = (StateEnumWaTor) grid.getState(r, c);
    // assuming s == MOVED_FISH or MOVED_SHARK
    ++s.nDaysBreed;
    int breedDuration =
        s.equals(StateEnumWaTor.MOVED_FISH()) ? fishBreedDuration : sharkBreedDuration;

    StateEnumWaTor childState =
        s.equals(StateEnumWaTor.MOVED_FISH()) ? StateEnumWaTor.FISH() : StateEnumWaTor.SHARK();

    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateEnumWaTor.EMPTY());
    if (s.nDaysBreed > breedDuration && emptyNeighbors.size() > 0) {
      Cell cell = emptyNeighbors.get(Utils.randomChoose(emptyNeighbors));
      cell.setState(childState, true);

      s.nDaysBreed = 0;
      s.setMoved(false);
      return true;
    } else {
      s.setMoved(false);
    }
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
        if (grid.getState(r, c).equals(StateEnumWaTor.SHARK())) {
          updated |= starve(r, c);
          updated |= move(r, c);
          updated |= eat(r, c);
        }
      }
    }

    // 2. FISH movement
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (grid.getState(r, c).equals(StateEnumWaTor.FISH())) {
          updated |= move(r, c);
        }
      }
    }

    // 3. FISH and SHARK breeding
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (grid.getState(r, c).equals(StateEnumWaTor.MOVED_FISH())
            || grid.getState(r, c).equals(StateEnumWaTor.MOVED_SHARK())) {
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
        if (grid.getState(r, c).equals(StateEnumWaTor.FISH())) {
          ++nFish;
        } else if (grid.getState(r, c).equals(StateEnumWaTor.SHARK())) {
          ++nShark;
        }
      }
    }

  }
}
