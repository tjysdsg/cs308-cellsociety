package cellsociety;

import Model.Simulation;
import Model.SimulationPercolation;
import Model.StatePercolation;

public class SimulationPercolationTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationPercolation(8, 8);
    sim.setState(0, 1, StatePercolation.BLOCKED, true);
    sim.setState(0, 4, StatePercolation.BLOCKED, true);
    sim.setState(0, 5, StatePercolation.BLOCKED, true);
    sim.setState(0, 7, StatePercolation.BLOCKED, true);
    sim.setState(1, 2, StatePercolation.BLOCKED, true);
    sim.setState(1, 5, StatePercolation.BLOCKED, true);
    sim.setState(2, 0, StatePercolation.BLOCKED, true);
    sim.setState(2, 1, StatePercolation.BLOCKED, true);
    sim.setState(2, 3, StatePercolation.BLOCKED, true);
    sim.setState(2, 6, StatePercolation.BLOCKED, true);
    sim.setState(2, 7, StatePercolation.BLOCKED, true);
    sim.setState(3, 0, StatePercolation.BLOCKED, true);
    sim.setState(3, 3, StatePercolation.BLOCKED, true);
    sim.setState(3, 6, StatePercolation.BLOCKED, true);
    sim.setState(4, 0, StatePercolation.PERCOLATED, true);
    sim.setState(4, 4, StatePercolation.BLOCKED, true);
    sim.setState(4, 7, StatePercolation.BLOCKED, true);
    sim.setState(5, 0, StatePercolation.BLOCKED, true);
    sim.setState(5, 2, StatePercolation.BLOCKED, true);
    sim.setState(5, 4, StatePercolation.BLOCKED, true);
    sim.setState(5, 5, StatePercolation.BLOCKED, true);
    sim.setState(6, 0, StatePercolation.BLOCKED, true);
    sim.setState(6, 4, StatePercolation.BLOCKED, true);
    sim.setState(6, 5, StatePercolation.BLOCKED, true);
    sim.setState(6, 6, StatePercolation.BLOCKED, true);
    sim.setState(7, 2, StatePercolation.BLOCKED, true);
    sim.setState(7, 3, StatePercolation.BLOCKED, true);
    sim.setState(7, 4, StatePercolation.BLOCKED, true);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
