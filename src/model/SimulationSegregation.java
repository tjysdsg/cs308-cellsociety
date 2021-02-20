package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simulation model of segregation.
 * <p>
 * Configurable options:
 * <ul>
 *   <li>
 *     "threshold" (double): The threshold of an agent being satisfied, default 0.3
 *   </li>
 * </ul>
 * <p>
 * See also https://www2.cs.duke.edu/courses/compsci308/spring21/assign/02_simulation/nifty/mccown-schelling-model-segregation/
 *
 * @author jt304
 */
public class SimulationSegregation extends Simulation {

  private double threshold = 0.3;
  private int nSatisfied = 0;
  private int nDissatisfied = 0;

  public SimulationSegregation(int nRows, int nCols) {
    grid = new GridSq(nRows, nCols, StateSegregation.EMPTY, Neighborhood.Square8());
  }

  @Override
  public <T> void setConfig(String name, T value) {
    if (name.equals("threshold")) {
      threshold = (double) value;
    }
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    ret.put("nSatisfied", nSatisfied);
    ret.put("nDissatisfied", nDissatisfied);
    return ret;
  }

  @Override
  public String getSimType() {
    return "Segregation";
  }

  @Override
  protected void updateNextStates() {
    boolean updated = false;
    nSatisfied = nDissatisfied = 0; // FIXME: move this to updateStats

    ArrayList<Cell> dissatisfiedAgents = new ArrayList<>();
    LinkedList<int[]> emptySpots = new LinkedList<>();
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        StateSegregation s = (StateSegregation) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);

        if (s.equals(StateSegregation.EMPTY)) {
          emptySpots.add(new int[]{r, c});
        } else { // agents
          int nSimilarNeighbors = 0;
          for (var neighbor : neighbors) {
            if (neighbor.getState().equals(s)) {
              ++nSimilarNeighbors;
            }
          }
          if ((double) nSimilarNeighbors / neighbors.size() < threshold) {
            // save to list, wait for emptySpots to be filled before moving them
            dissatisfiedAgents.add(grid.getCell(r, c));
            ++nDissatisfied; // FIXME: move this to updateStats
          } else {
            ++nSatisfied; // FIXME: move this to updateStats
          }
        }
      }
    }

    // move all dissatisfied agents
    for (Cell da : dissatisfiedAgents) {
      int emptyIdx = Utils.randomChoose(emptySpots);
      if (emptyIdx != -1) {
        int[] dest = emptySpots.get(emptyIdx);
        grid.setState(dest[0], dest[1], da.getState());
        da.setState(StateSegregation.EMPTY, false);
        emptySpots.remove(emptyIdx);
        updated = true;
      }
    }
    if (!updated) {
      isOver = true;
    }
  }
}
