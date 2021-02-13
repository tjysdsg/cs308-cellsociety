package cellsociety;

import Model.Simulation;
import Model.SimulationGOL;
import Model.StateGOL;

public class SimulationGOLTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationGOL(20);
    // glider pattern
    sim.setState(10, 10, StateGOL.ALIVE, true);
    sim.setState(11, 10, StateGOL.ALIVE, true);
    sim.setState(12, 10, StateGOL.ALIVE, true);
    sim.setState(12, 9, StateGOL.ALIVE, true);
    sim.setState(11, 8, StateGOL.ALIVE, true);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
