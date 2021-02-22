package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Simulation model of SugarScape.
 * <p>
 * See {@link Simulation#setConfig(String, Object)} for general simulation options
 * <p>
 * Configurable options:
 * <ul>
 *   <li>
 *     TODO:
 *   </li>
 * </ul>
 * <p>
 * See also https://www2.cs.duke.edu/courses/compsci308/spring21/assign/02_simulation/Sugarscape_Leicester.pdf
 *
 * @author jt304
 */
public class SimulationSugar extends Simulation {

  private int sugarGrowBackRate = 3;
  private int sugarGrowBackInterval = 2;

  public SimulationSugar(int nRows, int nCols) {
    grid = new GridSq4(nRows, nCols, new StateSugar(StateEnumSugar.EMPTY, 0));
  }

  @Override
  public <T> void setConfig(String name, T value) {
    super.setConfig(name, value);
    // TODO
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    // TODO
    return ret;
  }

  @Override
  public String getSimType() {
    return "SugarScape";
  }

  @Override
  protected void updateNextStates() {
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateSugar s = (StateSugar) grid.getState(r, c);

        // grow back sugar
        int growTime = s.getSugarGrowTime() + 1;
        int sugar = s.getSugar();
        if (growTime >= sugarGrowBackInterval) {
          sugar += sugarGrowBackRate;
          s.setSugar(sugar);
          s.setSugarGrowTime(0);
        } else {
          s.setSugarGrowTime(growTime);
        }

        // agent
        // TODO: starve to death
        SugarAgent agent = s.getAgent();
        int maxSugar = 0;
        Vec2D currCoord = new Vec2D(r, c);
        Vec2D bestCoord = new Vec2D(r, c);
        // FIXME: don't use hardcoded directions
        Vec2D[] directions = new Vec2D[]{
            new Vec2D(1, 0),
            new Vec2D(-1, 0),
            new Vec2D(0, 1),
            new Vec2D(0, -1),
        };
        if (s.getStateType() == StateEnumSugar.AGENT) {
          for (Vec2D dir : directions) { // check next several tiles in a direction
            for (int i = 0; i < agent.getVision(); ++i) {
              Vec2D dest = dir.mul(i + 1).add(currCoord);

              // wrap coord if wrap is enabled
              dest = grid.wrapAroundCoord(dest);
              if (!grid.isInside(dest.getX(), dest.getY())) {
                continue;
              }

              StateSugar destState = (StateSugar) grid.getState(dest.getX(), dest.getY());
              if (
                // make sure this is >, because we want to find the closest patch if
                // there are patches with the same value
                  destState.getSugar() > maxSugar
                      && destState.getStateType() != StateEnumSugar.AGENT
              ) {
                maxSugar = destState.getSugar();
                bestCoord = dest;
              }
            }
          }

          // move to best patch
          if (!bestCoord.equals(currCoord)) {
            ((StateSugar) grid.getState(currCoord.getX(), currCoord.getY())).removeAgent();
            ((StateSugar) grid.getState(bestCoord.getX(), bestCoord.getY())).setAgent(agent);

            // take sugar
            agent.setSugar(s.getSugar());
            s.setSugar(0);

            // metabolism
            agent.setSugar(agent.getSugar() - agent.getMetabolism());
          }
        }
      }
    }
  }

  @Override
  protected void updateStats() {
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        // TODO
      }
    }
  }

}
