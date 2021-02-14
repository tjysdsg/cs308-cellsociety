package cellsociety;

import Model.Simulation;
import Model.SimulationWaTor;
import Model.StateWaTor;

public class SimulationWaTorTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationWaTor(20);
    // glider pattern
    sim.setState(10, 10, StateWaTor.FISH, true);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}