package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateAnt extends State {

  private List<Ant> ants;
  private Map<PheromoneType, Integer> pheromones;
  private boolean isFoodSource;
  private boolean isNest;

  public StateAnt(StateEnumAnt stateType, boolean isFoodSource, boolean isNest) {
    super(stateType);
    pheromones = new HashMap<>();
    pheromones.put(PheromoneType.FOOD, 0);
    pheromones.put(PheromoneType.HOME, 0);
    this.isFoodSource = isFoodSource;
    this.isNest = isNest;
  }

  public int getPheromone(PheromoneType type) {
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
    return ants.size() - 1;
  }

  public Ant removeAntAt(int idx) {
    return ants.remove(idx);
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
