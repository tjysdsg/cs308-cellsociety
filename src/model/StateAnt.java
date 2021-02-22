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

  public int getPheromone(PheromoneType type) {
    if (type == PheromoneType.HOME && isNest) {
      return Integer.MAX_VALUE;
    }
    if (type == PheromoneType.FOOD && isFoodSource) {
      return Integer.MAX_VALUE;
    }
    return pheromones.get(type);
  }

  public void incrementPheromone(PheromoneType type) {
    pheromones.put(type, pheromones.get(type) + 1);
  }

  public void decrementPheromone(PheromoneType type) {
    pheromones.put(type, pheromones.get(type) - 1);
  }

  public int addAnt(Ant ant) {
    ants.add(ant);
    stateType = StateEnumAnt.ANTS;
    return ants.size() - 1;
  }

  public Ant removeAntAt(int idx) {
    Ant ret = ants.remove(idx);
    if (ants.isEmpty()) {
      stateType = StateEnumAnt.EMPTY;
    }
    return ret;
  }

  public Ant getAnt(int idx) {
    return ants.get(idx);
  }

  public int getNAnts() {
    return ants.size();
  }

  public boolean isFoodSource() {
    return isFoodSource;
  }

  public boolean isNest() {
    return isNest;
  }
}
