package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationWaTor extends Simulation {

  private int fishBreedDuration = 2;
  private int sharkBreedDuration = 4;
  private int sharkStarveDuration = 6;

  public SimulationWaTor(int nRows, int nCols) {
    grid = new Grid(nRows, nCols, StateWaTor.EMPTY(), Neighborhood.Preset4());
  }

  public List<Cell> sublistWithStateEquals(List<Cell> list, StateWaTor s) {
    ArrayList<Cell> ret = new ArrayList<>();
    for (var neighbor : list) {
      if (neighbor.getState().equals(s)) {
        ret.add(neighbor);
      }
    }
    return ret;
  }

  @Override
  public <T> void setConfig(String name, T value) {
  }

  private boolean starve(int r, int c) {
    StateWaTor s = (StateWaTor) grid.getState(r, c);
    if (++s.nDaysStarve > sharkStarveDuration) {
      grid.setState(r, c, StateWaTor.EMPTY());
      return true;
    }
    return false;
  }

  private boolean move(int r, int c) {
    StateWaTor s = (StateWaTor) grid.getState(r, c);

    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateWaTor.EMPTY());
    if (emptyNeighbors.size() > 0) {
      int idx = Utils.randomChoose(emptyNeighbors);
      s.setMoved(true);
      emptyNeighbors.get(idx).setState(s, true);
      grid.setState(r, c, StateWaTor.EMPTY(), true);
      return true;
    }
    return false;
  }

  private boolean eat(int r, int c) {
    StateWaTor s = (StateWaTor) grid.getState(r, c);
    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> fishNeighbors = sublistWithStateEquals(neighbors, StateWaTor.FISH());

    if (fishNeighbors.size() > 0) {
      Cell cell = fishNeighbors.get(Utils.randomChoose(fishNeighbors));
      cell.setState(StateWaTor.EMPTY(), true);
      s.nDaysStarve = 0;
      return true;
    }
    return false;
  }

  private boolean breed(int r, int c) {
    StateWaTor s = (StateWaTor) grid.getState(r, c);
    // assuming s == MOVED_FISH or MOVED_SHARK
    ++s.nDaysBreed;
    int breedDuration = s.equals(StateWaTor.MOVED_FISH()) ? fishBreedDuration : sharkBreedDuration;

    StateWaTor childState =
        s.equals(StateWaTor.MOVED_FISH()) ? StateWaTor.FISH() : StateWaTor.SHARK();

    List<Cell> neighbors = grid.getNeighborsOf(r, c);
    List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateWaTor.EMPTY());
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
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nCols; ++c) {
        if (grid.getState(r, c).equals(StateWaTor.SHARK())) {
          updated |= starve(r, c);
          updated |= move(r, c);
          updated |= eat(r, c);
        }
      }
    }

    // 2. FISH movement
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nCols; ++c) {
        if (grid.getState(r, c).equals(StateWaTor.FISH())) {
          updated |= move(r, c);
        }
      }
    }

    // 3. FISH and SHARK breeding
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nCols; ++c) {
        if (grid.getState(r, c).equals(StateWaTor.MOVED_FISH())
            || grid.getState(r, c).equals(StateWaTor.MOVED_SHARK())) {
          updated |= breed(r, c);
        }
      }
    }

    if (!updated) {
      isOver = true;
    }
  }
}
