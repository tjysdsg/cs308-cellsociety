package cellsociety;

import model.Simulation;
import model.SimulationFire;
import model.State;
import model.StateEnumFire;

public class SimulationFireTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationFire(20, 18);
    for (int i = 0; i < 20; ++i) {
      for (int j = 0; j < 18; ++j) {
        sim.setState(i, j, new State(StateEnumFire.TREE), true);
      }
    }
    sim.setState(9, 10, new State(StateEnumFire.BURNING), true);
    sim.setState(10, 10, new State(StateEnumFire.BURNING), true);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
