package cellsociety;

import model.Simulation;
import model.SimulationPercolation;
import model.StateEnumPercolation;

public class SimulationPercolationTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationPercolation(8, 8);
    sim.setState(0, 1, StateEnumPercolation.BLOCKED, true);
    sim.setState(0, 4, StateEnumPercolation.BLOCKED, true);
    sim.setState(0, 5, StateEnumPercolation.BLOCKED, true);
    sim.setState(0, 7, StateEnumPercolation.BLOCKED, true);
    sim.setState(1, 2, StateEnumPercolation.BLOCKED, true);
    sim.setState(1, 5, StateEnumPercolation.BLOCKED, true);
    sim.setState(2, 0, StateEnumPercolation.BLOCKED, true);
    sim.setState(2, 1, StateEnumPercolation.BLOCKED, true);
    sim.setState(2, 3, StateEnumPercolation.BLOCKED, true);
    sim.setState(2, 6, StateEnumPercolation.BLOCKED, true);
    sim.setState(2, 7, StateEnumPercolation.BLOCKED, true);
    sim.setState(3, 0, StateEnumPercolation.BLOCKED, true);
    sim.setState(3, 3, StateEnumPercolation.BLOCKED, true);
    sim.setState(3, 6, StateEnumPercolation.BLOCKED, true);
    sim.setState(4, 0, StateEnumPercolation.PERCOLATED, true);
    sim.setState(4, 4, StateEnumPercolation.BLOCKED, true);
    sim.setState(4, 7, StateEnumPercolation.BLOCKED, true);
    sim.setState(5, 0, StateEnumPercolation.BLOCKED, true);
    sim.setState(5, 2, StateEnumPercolation.BLOCKED, true);
    sim.setState(5, 4, StateEnumPercolation.BLOCKED, true);
    sim.setState(5, 5, StateEnumPercolation.BLOCKED, true);
    sim.setState(6, 0, StateEnumPercolation.BLOCKED, true);
    sim.setState(6, 4, StateEnumPercolation.BLOCKED, true);
    sim.setState(6, 5, StateEnumPercolation.BLOCKED, true);
    sim.setState(6, 6, StateEnumPercolation.BLOCKED, true);
    sim.setState(7, 2, StateEnumPercolation.BLOCKED, true);
    sim.setState(7, 3, StateEnumPercolation.BLOCKED, true);
    sim.setState(7, 4, StateEnumPercolation.BLOCKED, true);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
