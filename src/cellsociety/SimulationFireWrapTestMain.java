package cellsociety;

import model.Simulation;
import model.SimulationFire;
import model.StateFire;

public class SimulationFireWrapTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationFire(5, 5);

    sim.setState(0, 0, StateFire.BURNING, true);
    sim.setState(0, 4, StateFire.TREE, true);
    sim.setState(4, 0, StateFire.TREE, true);

    sim.setConfig("wrapAround", true);
    sim.setConfig("probCatch", 1.0);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
