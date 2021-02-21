package cellsociety;

import model.PheromoneType;
import model.Simulation;
import model.SimulationAnt;
import model.SimulationAnt;
import model.StateEnumAnt;
import model.StateAnt;

public class SimulationAntTestMain {

  private static StateAnt makeStateWithPheromone(PheromoneType type, int val) {
    var ret = new StateAnt(StateEnumAnt.EMPTY, 0, false, false);
    for (int i = 0; i < val; ++i) {
      ret.incrementPheromone(type);
    }
    return ret;
  }

  public static void main(String[] args) {
    Simulation sim = new SimulationAnt(9, 9);
    sim.setState(4, 4, new StateAnt(StateEnumAnt.EMPTY, 0, false, true), true);
    sim.setState(0, 0, new StateAnt(StateEnumAnt.EMPTY, 0, true, false), true);
    sim.setState(8, 8, new StateAnt(StateEnumAnt.ANTS, 1, false, false), true);
    sim.setState(2, 2, new StateAnt(StateEnumAnt.ANTS, 1, false, false), true);

    sim.setState(7, 7, makeStateWithPheromone(PheromoneType.HOME, 1), true);
    sim.setState(6, 7, makeStateWithPheromone(PheromoneType.HOME, 1), true);
    sim.setState(6, 6, makeStateWithPheromone(PheromoneType.HOME, 1), true);
    sim.setState(5, 5, makeStateWithPheromone(PheromoneType.HOME, 1), true);

    sim.setState(3, 3, makeStateWithPheromone(PheromoneType.HOME, 1), true);
    sim.setState(4, 4, makeStateWithPheromone(PheromoneType.HOME, 1), true);

    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
