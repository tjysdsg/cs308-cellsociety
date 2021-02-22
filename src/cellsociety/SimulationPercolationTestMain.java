package cellsociety;

import model.Simulation;
import model.SimulationPercolation;
import model.State;
import model.StateEnumPercolation;

public class SimulationPercolationTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationPercolation(8, 8);
    sim.setState(0, 1, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(0, 4, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(0, 5, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(0, 7, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(1, 2, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(1, 5, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(2, 0, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(2, 1, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(2, 3, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(2, 6, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(2, 7, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(3, 0, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(3, 3, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(3, 6, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(4, 0, new State(StateEnumPercolation.PERCOLATED), true);
    sim.setState(4, 4, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(4, 7, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(5, 0, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(5, 2, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(5, 4, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(5, 5, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(6, 0, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(6, 4, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(6, 5, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(6, 6, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(7, 2, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(7, 3, new State(StateEnumPercolation.BLOCKED), true);
    sim.setState(7, 4, new State(StateEnumPercolation.BLOCKED), true);
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
