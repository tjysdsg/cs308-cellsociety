package cellsociety;

import model.Simulation;
import model.SimulationSegregation;
import model.StateSegregation;

public class SimulationSegregationTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationSegregation(5, 5);
    sim.setState(0, 0, StateSegregation.X, true);
    sim.setState(0, 1, StateSegregation.X, true);
    sim.setState(0, 2, StateSegregation.O, true);
    sim.setState(0, 3, StateSegregation.X, true);
    sim.setState(0, 4, StateSegregation.O, true);

    sim.setState(1, 1, StateSegregation.O, true);
    sim.setState(1, 2, StateSegregation.O, true);
    sim.setState(1, 3, StateSegregation.O, true);
    sim.setState(1, 4, StateSegregation.O, true);

    sim.setState(2, 0, StateSegregation.X, true);
    sim.setState(2, 1, StateSegregation.X, true);

    sim.setState(3, 0, StateSegregation.X, true);
    sim.setState(3, 1, StateSegregation.O, true);
    sim.setState(3, 2, StateSegregation.X, true);
    sim.setState(3, 3, StateSegregation.X, true);
    sim.setState(3, 4, StateSegregation.X, true);

    sim.setState(4, 0, StateSegregation.X, true);
    sim.setState(4, 1, StateSegregation.O, true);
    sim.setState(4, 2, StateSegregation.O, true);
    sim.setState(4, 4, StateSegregation.O, true);

    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
