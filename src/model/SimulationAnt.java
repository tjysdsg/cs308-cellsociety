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

  private static final String NEST_FOOD_KEY = "nestFood";

  private int nestFood = 0;

  private int maxNumAntsPerCell = 10;

  private static final Vec2D INVALID_COORD = new Vec2D(-1, -1);

  public SimulationAnt(int nRows, int nCols) {
    grid = new GridSq8(nRows, nCols, new StateAnt(StateEnumAnt.EMPTY, 0, false, false));
    simType = "Foraging Ants";
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
    ret.put(NEST_FOOD_KEY, nestFood);
    return ret;
  }

  @Override
  public List<String> getStatsNames() {
    ArrayList<String> ret = new ArrayList<>();
    ret.add(NEST_FOOD_KEY);
    return ret;
  }

  /**
   * Returns INVALID_COORD if not found
   */
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
    if (maxPheromone == 0) {
      return INVALID_COORD;
    }
    return neighbors.get(maxIdx);
  }

  /**
   * Returns INVALID_COORD if not found
   */
  private Vec2D findMaxPheromoneNeighbor(int r, int c, PheromoneType type) {
    List<Vec2D> neighbors = grid.getNeighborsCoord(r, c);
    return findMaxPheromoneFromList(type, neighbors);
  }

  /**
   * Returns INVALID_COORD if not found
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
    ant.setOrientation(dest.minus(src)); // update orientation

    return destState.addAnt(ant);
  }

  private void returnNest(int r, int c, int antIdx) {
    StateAnt s = (StateAnt) grid.getState(r, c);
    Vec2D currCoord = new Vec2D(r, c);
    // do nothing if already at nest
    if (s.isNest()) {
      return;
    }

    Vec2D maxPheromoneNeighborCoord = findMaxPheromoneNeighbor(r, c, PheromoneType.HOME);
    Ant ant = s.getAnt(antIdx);

    // can't find home, do nothing
    if (maxPheromoneNeighborCoord.equals(INVALID_COORD)) {
      return;
    }

    // if at food source, set orientation to any neighbor with max home pheromone
    if (s.isFoodSource()) {
      ant.setOrientation(maxPheromoneNeighborCoord.minus(currCoord));
    }

    // find next cell to move to
    Vec2D dest = findMaxPheromoneForwardNeighbor(
        r, c,
        PheromoneType.HOME, s.getAnt(antIdx).getOrientation()
    );
    if (dest.equals(INVALID_COORD)) {
      dest = maxPheromoneNeighborCoord;
    }

    // drop food pheromone, and move to the destination
    dropPheromone(r, c, PheromoneType.FOOD);
    moveAntTo(currCoord, dest, antIdx);

    // if destination is nest, all ants here drop their food (if has one)
    StateAnt destState = (StateAnt) grid.getState(dest.getX(), dest.getY());
    if (destState.isNest()) {
      if (ant.hasFood()) {
        ++nestFood;
      }
      ant.setHasFood(false);
    }
  }

  private void findFood(int r, int c, int antIdx) {
    StateAnt s = (StateAnt) grid.getState(r, c);
    Vec2D maxPheromoneNeighborCoord = findMaxPheromoneNeighbor(r, c, PheromoneType.FOOD);
    Ant ant = s.getAnt(antIdx);

    // there is food pheromone around, so head towards that direction
    if (!maxPheromoneNeighborCoord.equals(INVALID_COORD)) {
      if (s.isNest()) {
        ant.setOrientation(maxPheromoneNeighborCoord.minus(new Vec2D(r, c)));
      }

      Vec2D dest = findMaxPheromoneForwardNeighbor(
          r, c,
          PheromoneType.FOOD, s.getAnt(antIdx).getOrientation()
      );
      if (dest.equals(INVALID_COORD)) {
        dest = maxPheromoneNeighborCoord;
      }
      dropPheromone(r, c, PheromoneType.HOME);
      moveAntTo(new Vec2D(r, c), dest, antIdx);
    } else { // no food pheromone around, go towards a random direction
      var neighbors = grid.getNeighborsCoord(r, c);

      for (int i = neighbors.size() - 1; i >= 0; --i) {
        // check if too crowded
        var n = neighbors.get(i);
        if (((StateAnt) grid.getState(n.getX(), n.getY())).getNAnts() >= maxNumAntsPerCell) {
          neighbors.remove(i);
        }
      }
      int idx = Utils.randomChoose(neighbors);
      Vec2D dest = neighbors.get(idx);
      moveAntTo(new Vec2D(r, c), dest, antIdx);
    }
  }

  private void forage(int r, int c) {
    StateAnt s = (StateAnt) grid.getState(r, c);
    for (int i = s.getNAnts() - 1; i >= 0; --i) {
      // iterate in reverse order because ants might get deleted
      Ant ant = s.getAnt(i);
      if (ant.hasFood()) {
        returnNest(r, c, i);
      } else {
        findFood(r, c, i);
      }
    }

    if (s.isFoodSource()) { // pick up food at food source
      for (int i = s.getNAnts() - 1; i >= 0; --i) {
        s.getAnt(i).setHasFood(true);
      }
    } else { // loop every ant and let them forage
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
}
