package cellsociety;

import model.Simulation;
import model.SimulationSegregation;
import model.State;
import model.StateEnumSegregation;

public class SimulationSegregationTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationSegregation(5, 5);
    sim.setState(0, 0, new State(StateEnumSegregation.X), true);
    sim.setState(0, 1, new State(StateEnumSegregation.X), true);
    sim.setState(0, 2, new State(StateEnumSegregation.O), true);
    sim.setState(0, 3, new State(StateEnumSegregation.X), true);
    sim.setState(0, 4, new State(StateEnumSegregation.O), true);

    sim.setState(1, 1, new State(StateEnumSegregation.O), true);
    sim.setState(1, 2, new State(StateEnumSegregation.O), true);
    sim.setState(1, 3, new State(StateEnumSegregation.O), true);
    sim.setState(1, 4, new State(StateEnumSegregation.O), true);

    sim.setState(2, 0, new State(StateEnumSegregation.X), true);
    sim.setState(2, 1, new State(StateEnumSegregation.X), true);

    sim.setState(3, 0, new State(StateEnumSegregation.X), true);
    sim.setState(3, 1, new State(StateEnumSegregation.O), true);
    sim.setState(3, 2, new State(StateEnumSegregation.X), true);
    sim.setState(3, 3, new State(StateEnumSegregation.X), true);
    sim.setState(3, 4, new State(StateEnumSegregation.X), true);

    sim.setState(4, 0, new State(StateEnumSegregation.X), true);
    sim.setState(4, 1, new State(StateEnumSegregation.O), true);
    sim.setState(4, 2, new State(StateEnumSegregation.O), true);
    sim.setState(4, 4, new State(StateEnumSegregation.O), true);

    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
