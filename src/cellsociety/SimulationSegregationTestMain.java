package cellsociety;

import model.Simulation;
import model.SimulationSegregation;
import model.StateEnumSegregation;

public class SimulationSegregationTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationSegregation(5, 5);
    sim.setState(0, 0, StateEnumSegregation.X, true);
    sim.setState(0, 1, StateEnumSegregation.X, true);
    sim.setState(0, 2, StateEnumSegregation.O, true);
    sim.setState(0, 3, StateEnumSegregation.X, true);
    sim.setState(0, 4, StateEnumSegregation.O, true);

    sim.setState(1, 1, StateEnumSegregation.O, true);
    sim.setState(1, 2, StateEnumSegregation.O, true);
    sim.setState(1, 3, StateEnumSegregation.O, true);
    sim.setState(1, 4, StateEnumSegregation.O, true);

    sim.setState(2, 0, StateEnumSegregation.X, true);
    sim.setState(2, 1, StateEnumSegregation.X, true);

    sim.setState(3, 0, StateEnumSegregation.X, true);
    sim.setState(3, 1, StateEnumSegregation.O, true);
    sim.setState(3, 2, StateEnumSegregation.X, true);
    sim.setState(3, 3, StateEnumSegregation.X, true);
    sim.setState(3, 4, StateEnumSegregation.X, true);

    sim.setState(4, 0, StateEnumSegregation.X, true);
    sim.setState(4, 1, StateEnumSegregation.O, true);
    sim.setState(4, 2, StateEnumSegregation.O, true);
    sim.setState(4, 4, StateEnumSegregation.O, true);

    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
