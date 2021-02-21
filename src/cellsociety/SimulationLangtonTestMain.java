package cellsociety;

import java.util.Random;
import model.Simulation;
import model.SimulationLangton;
import model.State;
import model.StateEnumLangton;
import model.StateEnumRPS;

public class SimulationLangtonTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationLangton(20, 20);
    Random rand = new Random();
    for (int i = 5; i < 15; ++i) {
      for (int j = 5; j < 15; ++j) {
        StateEnumLangton s = StateEnumLangton.fromInt(StateEnumLangton.ALL_VALS[rand.nextInt(3)]);
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
