package cellsociety;

import model.Simulation;
import model.SimulationGOL;
import model.StateEnumGOL;

public class SimulationGOLTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationGOL(20, 21);
    // glider pattern
    sim.setState(10, 10, StateEnumGOL.ALIVE, true);
    sim.setState(11, 10, StateEnumGOL.ALIVE, true);
    sim.setState(12, 10, StateEnumGOL.ALIVE, true);
    sim.setState(12, 9, StateEnumGOL.ALIVE, true);
    sim.setState(11, 8, StateEnumGOL.ALIVE, true);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
