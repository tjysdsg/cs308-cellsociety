package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simulation model of Foraging Ants.
 * <p>
 * See {@link Simulation#setConfig(String, Object)} for general simulation options
 * <p>
 * Configurable options:
 * <ul>
 *   <li>
 *     TODO "" (int):
 *   </li>
 * </ul>
 * <p>
 * See also https://cs.gmu.edu/~eclab/projects/mason/publications/alife04ant.pdf
 *
 * @author jt304
 */
public class SimulationAnt extends Simulation {

  private int nestFood = 0;

  public SimulationAnt(int nRows, int nCols) {
    grid = new GridSq8(nRows, nCols, new StateWaTor(StateEnumWaTor.EMPTY));
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
      // TODO
    }
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    // TODO
    return ret;
  }

  @Override
  public String getSimType() {
    return "Foraging Ants";
  }

  private Vec2D findMaxPheromoneFromList(PheromoneType type, List<Vec2D> neighbors) {
    int maxPheromone = 0;
    int maxIdx = 0;
    for (int i = 0; i < neighbors.size(); ++i) {
      Vec2D neighborCoord = neighbors.get(i);
      StateAnt s = (StateAnt) grid.getState(neighborCoord.getX(), neighborCoord.getY());

      int ph = s.getPheromone(type);
      if (maxPheromone < ph) {
        maxPheromone = ph;
        maxIdx = i;
      }
    }
    return neighbors.get(maxIdx);
  }

  /**
   * Returns (0, 0) if not found
   */
  private Vec2D findMaxPheromoneNeighbor(int r, int c, PheromoneType type) {
    List<Vec2D> neighbors = grid.getNeighborsCoord(r, c);
    return findMaxPheromoneFromList(type, neighbors);
  }

  /**
   * Returns (0, 0) if not found
   * <p>
   * Assumes forwardDirection is not (0, 0)
   */
  private Vec2D findMaxPheromoneForwardNeighbor(
      int r, int c,
      PheromoneType type, Vec2D forwardDirection
  ) {
    List<Vec2D> neighbors = grid.getForwardNeighborsCoord(r, c, forwardDirection);
    return findMaxPheromoneFromList(type, neighbors);
  }

  private void dropPheromone(int r, int c, PheromoneType type) {
    StateAnt s = (StateAnt) grid.getState(r, c);
    s.incrementPheromone(type);
  }

  /**
   * Returns the index of the ant after moving
   */
  private int moveAntTo(Vec2D src, Vec2D dest, int antIdx) {
    StateAnt srcState = (StateAnt) grid.getState(src.getX(), src.getY());
    StateAnt destState = (StateAnt) grid.getState(dest.getX(), dest.getY());
    Ant ant = srcState.removeAntAt(antIdx);
    return destState.addAnt(ant);
  }

  private void returnNest(int r, int c, int antIdx) {
    StateAnt s = (StateAnt) grid.getState(r, c);
    Vec2D maxPheromoneNeighborCoord = findMaxPheromoneNeighbor(r, c, PheromoneType.HOME);
    Ant ant = s.getAnt(antIdx);

    if (!maxPheromoneNeighborCoord.equals(new Vec2D(0, 0))) {
      if (s.isFoodSource()) {
        ant.setOrientation(maxPheromoneNeighborCoord.minus(new Vec2D(r, c)));
      }

      Vec2D dest = findMaxPheromoneForwardNeighbor(
          r, c,
          PheromoneType.HOME, s.getAnt(antIdx).getOrientation()
      );
      if (dest.equals(new Vec2D(0, 0))) {
        dest = maxPheromoneNeighborCoord;
        dropPheromone(r, c, PheromoneType.FOOD);
        moveAntTo(new Vec2D(r, c), dest, antIdx);
      }
    }
  }

  private void findFood(int r, int c, int antIdx) {
  }

  private void forage(int r, int c) {
    StateAnt s = (StateAnt) grid.getState(r, c);
    if (s.isNest()) { // is nest, all ants here drop their food (if has one)
      for (int i = s.getNAnts() - 1; i >= 0; --i) {
        Ant ant = s.getAnt(i);
        if (ant.hasFood()) {
          ++nestFood;
        }
        ant.setHasFood(false);
      }
    } else { // loop every ant and let them forage
      for (int i = s.getNAnts() - 1; i >= 0; --i) {
        // iterate in reverse order because ants might get deleted
        Ant ant = s.getAnt(i);
        if (ant.hasFood()) {
          returnNest(r, c, i);
        } else {
          findFood(r, c, i);
        }
      }
    }
  }

  /**
   * In this simulation, all the updates are applied immediately for simplicity
   */
  @Override
  protected void updateNextStates() {
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        forage(r, c);
      }
    }
  }

  @Override
  protected void updateStats() {
    // TODO
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
      }
    }

  }
}
