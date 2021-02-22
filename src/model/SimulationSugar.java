package model;

import java.util.ArrayList;
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

  private static final String N_AGENTS_KEY = "nAgents";

  private int sugarGrowBackRate = 1;
  private int sugarGrowBackInterval = 1;
  private int maxSugarPerPatch = 30;

  private int nAgents = 0;

  public SimulationSugar(int nRows, int nCols) {
    grid = new GridSq4(nRows, nCols, new StateSugar(StateEnumSugar.EMPTY, 0, null));
  }

  @Override
  public <T> void setConfig(String name, T value) {
    super.setConfig(name, value);
    switch (name) {
      case "sugarGrowBackRate" -> sugarGrowBackRate = (int) value;
      case "sugarGrowBackInterval" -> sugarGrowBackInterval = (int) value;
      case "maxSugarPerPatch" -> maxSugarPerPatch = (int) value;
      default -> {
      }
    }
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    ret.put(N_AGENTS_KEY, nAgents);
    return ret;
  }

  @Override
  public List<String> getStatsNames() {
    ArrayList<String> ret = new ArrayList<>();
    ret.add(N_AGENTS_KEY);
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
          if (sugar > maxSugarPerPatch) {
            sugar = maxSugarPerPatch;
          }
          s.setSugar(sugar);
          s.setSugarGrowTime(0);
        } else {
          s.setSugarGrowTime(growTime);
        }

        // agent
        if (s.getStateType() == StateEnumSugar.AGENT) {
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

          if (!bestCoord.equals(currCoord)) {
            // remove agent in current patch
            StateSugar newState = new StateSugar(s);
            newState.removeAgent();
            grid.setState(currCoord.getX(), currCoord.getY(), newState);

            // move agent to destination
            s = (StateSugar) grid.getState(bestCoord.getX(), bestCoord.getY());
            newState = new StateSugar(s);
            newState.setAgent(agent);
            grid.setState(bestCoord.getX(), bestCoord.getY(), newState);

            // take sugar
            agent.setSugar(newState.getSugar());
            newState.setSugar(0);

            // metabolism
            agent.setSugar(agent.getSugar() - agent.getMetabolism());

            // starve to death
            if (agent.getSugar() < 0) {
              newState.removeAgent();
            }
          }
        }
      }
    }
  }

  @Override
  protected void updateStats() {
    nAgents = 0;
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        if (grid.getState(r, c).getStateType() == StateEnumSugar.AGENT) {
          ++nAgents;
        }
      }
    }
  }

}
