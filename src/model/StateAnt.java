package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateAnt extends State {

  private List<Ant> ants;
  private Map<PheromoneType, Integer> pheromones;
  private boolean isFoodSource;
  private boolean isNest;

  /**
   * Copy constructor
   */
  public StateAnt(StateAnt other) {
    super(other.stateType);
    ants = other.ants;
    pheromones = other.pheromones;
    isFoodSource = other.isFoodSource;
    isNest = other.isNest;
  }

  /**
   * Constructor
   *
   * @param stateType    see {@link State#State(StateEnum)}
   * @param nAnts        the number of ants in this cell
   * @param isFoodSource is this a food source
   * @param isNest       is this a nest
   */
  public StateAnt(StateEnumAnt stateType, int nAnts, boolean isFoodSource, boolean isNest) {
    super(stateType);
    pheromones = new HashMap<>();
    pheromones.put(PheromoneType.FOOD, 0);
    pheromones.put(PheromoneType.HOME, 0);
    this.isFoodSource = isFoodSource;
    this.isNest = isNest;
    ants = new ArrayList<>();
    if (nAnts > 0 && stateType == StateEnumAnt.ANTS) {
      for (int i = 0; i < nAnts; ++i) {
        ants.add(new Ant());
      }
    }
  }

  /**
   * Get current pheromone level
   *
   * @param type Type of the pheromone
   */
  public int getPheromone(PheromoneType type) {
    if (type == PheromoneType.HOME && isNest) {
      return Integer.MAX_VALUE;
    }
    if (type == PheromoneType.FOOD && isFoodSource) {
      return Integer.MAX_VALUE;
    }
    return pheromones.get(type);
  }

  /**
   * Increase current pheromone level by 1
   *
   * @param type Type of the pheromone
   */
  public void incrementPheromone(PheromoneType type) {
    pheromones.put(type, pheromones.get(type) + 1);
  }

  /**
   * Decrease current pheromone level by 1
   *
   * @param type Type of the pheromone
   */
  public void decrementPheromone(PheromoneType type) {
    pheromones.put(type, pheromones.get(type) - 1);
  }

  /**
   * Add a new ant in current cell
   *
   * @param ant see {@link Ant}
   */
  public int addAnt(Ant ant) {
    ants.add(ant);
    stateType = StateEnumAnt.ANTS;
    return ants.size() - 1;
  }

  /**
   * Remove an existing ant in this cell
   *
   * @param idx the index of the ant
   * @return Ant object that is removed
   */
  public Ant removeAntAt(int idx) {
    Ant ret = ants.remove(idx);
    if (ants.isEmpty()) {
      stateType = StateEnumAnt.EMPTY;
    }
    return ret;
  }

  /**
   * Get an existing ant in this cell
   *
   * @param idx the index of the ant
   */
  public Ant getAnt(int idx) {
    return ants.get(idx);
  }

  /**
   * Get how many ants are there
   */
  public int getNAnts() {
    return ants.size();
  }

  /**
   * Is this a food source
   */
  public boolean isFoodSource() {
    return isFoodSource;
  }

  /**
   * Is this a nest
   */
  public boolean isNest() {
    return isNest;
  }

  /**
   * @see State#toString()
   */
  @Override
  public String toString() {
    if (isFoodSource) {
      return "F";
    }
    if (isNest) {
      return "N";
    }
    return "" + stateType;
  }
}
