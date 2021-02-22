package cellsociety;

import model.Simulation;
import model.SimulationAnt;
import model.StateEnumAnt;
import model.StateAnt;

public class SimulationAntTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationAnt(9, 9);
    sim.setState(4, 4, new StateAnt(StateEnumAnt.ANTS, 5, false, true), true);
    sim.setState(0, 0, new StateAnt(StateEnumAnt.EMPTY, 0, true, false), true);
    sim.setState(8, 8, new StateAnt(StateEnumAnt.EMPTY, 0, true, false), true);

    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
