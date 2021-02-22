package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Simulation model of Langton's Loop.
 * <p>
 * See {@link Simulation#setConfig(String, Object)} for general simulation options
 * <p>
 * No configurable options.
 * <p>
 * See also http://lslwww.epfl.ch/pages/embryonics/thesis/Chapter3.html
 *
 * @author jt304
 */
public class SimulationLangton extends Simulation {

  /**
   * From "ABCDE" to an int F, where A is the current value, B,C,D and E are the neighbors, and F is
   * the next value
   * <p>
   * NOTE: the order of 4 neighbors doesn't matter
   */
  public static Map<String, Integer> RULE_TABLE;

  private Map<Integer, Integer> stateCounts = new HashMap<>();

  public SimulationLangton(int nRows, int nCols) {
    grid = new GridSq4(nRows, nCols, new State(new StateEnumLangton()));
    grid.setEdgeType(EdgeType.INFINITE);
    simType = "Langton's Loop";

    // init state counts to 0s
    for (int s : StateEnumLangton.ALL_VALS) {
      stateCounts.put(s, 0);
    }

    // read rule table if not initialize
    if (RULE_TABLE == null) {
      RULE_TABLE = new HashMap<>();
      String[] ruleStrings = Utils.readResourceTxtToLines("LangtonLoopRuleTable.txt");
      for (String s : Objects.requireNonNull(ruleStrings)) {
        RULE_TABLE.put(s.substring(0, 5), Integer.parseInt(s.substring(5, 6)));
      }
    }
  }

  @Override
  public Map<String, Object> getStatsMap() {
    HashMap<String, Object> ret = new HashMap<>();
    for (int s : StateEnumLangton.ALL_VALS) {
      ret.put("State" + s, stateCounts.get(s));
    }
    return ret;
  }

  @Override
  public List<String> getStatsNames() {
    ArrayList<String> ret = new ArrayList<>();
    for (int s : StateEnumLangton.ALL_VALS) {
      ret.add("State" + s);
    }
    return ret;
  }

  @Override
  protected void updateNextStates() {
    boolean updated = false;

    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        var neighbors = grid.getNeighborsOf(r, c);
        if (neighbors.size() != 4) {
          // TODO: expand grid
          continue;
        }

        int val = grid.getState(r, c).toInteger();
        StringBuilder query = new StringBuilder(Integer.toString(val));
        for (var n : neighbors) {
          query.append(n.getState().toInteger());
        }

        List<String> querys = Utils.permutation(query.toString());
        for (String q : querys) {
          if (!RULE_TABLE.containsKey(q)) {
            continue;
          }
          int newVal = RULE_TABLE.get(q);
          grid.setState(r, c, new State(StateEnumLangton.fromInt(newVal)));
          if (newVal != val) {
            updated = true;
          }
        }

      }
    }

    if (!updated) {
      isOver = true;
    }
  }

  @Override
  protected void updateStats() {
    for (int r = 0; r < grid.getNumRows(); ++r) {
      for (int c = 0; c < grid.getNumCols(); ++c) {
        int s = grid.getState(r, c).toInteger();
        stateCounts.put(s, stateCounts.get(s) + 1);
      }
    }

  }
}
