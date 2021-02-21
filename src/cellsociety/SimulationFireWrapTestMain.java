package cellsociety;

import model.Simulation;
import model.SimulationFire;
import model.State;
import model.StateEnumFire;

public class SimulationFireWrapTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationFire(5, 5);

    sim.setState(0, 0, new State(StateEnumFire.BURNING), true);
    sim.setState(0, 4, new State(StateEnumFire.TREE), true);
    sim.setState(4, 0, new State(StateEnumFire.TREE), true);

    sim.setConfig("wrapAround", true);
    sim.setConfig("probCatch", 1.0);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
