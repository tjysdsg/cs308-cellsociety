package cellsociety;

import java.util.Random;
import model.Simulation;
import model.SimulationRPS;
import model.StateRPS;

public class SimulationRPSTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationRPS(20, 20);
    Random rand = new Random();
    StateRPS[] allStates = new StateRPS[]{StateRPS.ROCK, StateRPS.PAPER, StateRPS.SCISSORS};
    for (int i = 0; i < 20; ++i) {
      for (int j = 0; j < 20; ++j) {
        StateRPS s = allStates[rand.nextInt(3)];
        sim.setState(i, j, s, true);
      }
    }
    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
