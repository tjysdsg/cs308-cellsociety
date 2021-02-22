package cellsociety;

import java.util.Random;
import model.Simulation;
import model.SimulationSugar;
import model.StateSugar;
import model.StateEnumSugar;

public class SimulationSugarTestMain {

  public static void main(String[] args) {
    Simulation sim = new SimulationSugar(9, 9);

    Random rand = new Random();
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        // 5 to 25 sugars
        sim.setState(i, j, new StateSugar(StateEnumSugar.EMPTY, rand.nextInt(20) + 5), true);
      }
    }

    sim.setState(8, 8, new StateSugar(StateEnumSugar.AGENT, 0), true);
    sim.setState(2, 2, new StateSugar(StateEnumSugar.AGENT, 0), true);

    System.out.println(sim.toString());
    while (!sim.isOver()) {
      sim.update();
      System.out.println(sim.toString());
    }
  }
}
