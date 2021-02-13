package cellsociety;

import Model.Simulation;
import Model.SimulationFire;
import Model.StateFire;

public class SimulationFireTestMain {

  public static void main(String[] args) {
    boolean isRunning = true;
    Simulation sim = new SimulationFire(20);
    for (int i = 0; i < 20; ++i) {
      for (int j = 0; j < 20; ++j) {
        sim.setState(i, j, StateFire.TREE, true);
      }
    }
    sim.setState(9, 10, StateFire.BURNING, true);
    sim.setState(10, 10, StateFire.BURNING, true);
    System.out.println(sim.toString());
    while (isRunning) {
      isRunning = sim.update();
      System.out.println(sim.toString());
    }
  }
}
