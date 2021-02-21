package cellsociety;

import java.util.Random;
import model.Simulation;
import model.SimulationRPS;
import model.State;
import model.StateEnumRPS;

public class SimulationRPSTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationRPS(20, 20);
    Random rand = new Random();
    StateEnumRPS[] allStates = new StateEnumRPS[]{
        StateEnumRPS.ROCK,
        StateEnumRPS.PAPER,
        StateEnumRPS.SCISSORS
    };
    for (int i = 0; i < 20; ++i) {
      for (int j = 0; j < 20; ++j) {
        StateEnumRPS s = allStates[rand.nextInt(3)];
        sim.setState(i, j, new State(s), true);
      }
    }
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
