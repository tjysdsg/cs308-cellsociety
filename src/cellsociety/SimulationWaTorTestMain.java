package cellsociety;

import model.Simulation;
import model.SimulationWaTor;
import model.StateEnumWaTor;

public class SimulationWaTorTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationWaTor(11, 10);
    sim.setState(4, 4, StateEnumWaTor.FISH(), true);
    sim.setState(0, 0, StateEnumWaTor.SHARK(), true);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
