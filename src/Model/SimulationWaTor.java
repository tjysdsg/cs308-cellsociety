package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO: customize stop condition
public class SimulationWaTor extends Simulation {

  private int fishBreedDuration = 2;
  private int sharkBreedDuration = 5;

  public SimulationWaTor(int n) {
    grid = new Grid(n, n, StateWaTor.EMPTY, Neighborhood.Preset4());
  }

  public List<Cell> sublistWithStateEquals(List<Cell> list, StateWaTor s) {
    ArrayList<Cell> ret = new ArrayList<>();
    for (var neighbor : list) {
      if (neighbor.getState() == s) {
        ret.add(neighbor);
      }
    }
    return ret;
  }

  public int randomChoose(List<Cell> list) {
    Random rand = new Random();
    return rand.nextInt(list.size());
  }

  @Override
  public <T> void setConfig(String name, T value) {
  }

  @Override
  protected void updateNextStates() {
    // two pass
    // first pass process SHARK
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nRows; ++c) {
        StateWaTor s = (StateWaTor) grid.getState(r, c);
        ++s.nDaysAlive;
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        if (s == StateWaTor.SHARK) {
          // breed
          List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateWaTor.EMPTY);
          if (s.nDaysAlive > sharkBreedDuration && emptyNeighbors.size() > 0) {
            Cell cell = emptyNeighbors.get(randomChoose(emptyNeighbors));
            cell.setState(StateWaTor.SHARK, true);
          }

          // eat
          List<Cell> fishNeighbors = sublistWithStateEquals(neighbors, StateWaTor.FISH);
          if (fishNeighbors.size() > 0) {
            Cell cell = fishNeighbors.get(randomChoose(fishNeighbors));
            cell.setState(StateWaTor.EMPTY, true);
          }
        }
      }
    }

    // second pass process FISH, so we can make sure that fish eaten by SHARK cannot move
    for (int r = 0; r < grid.nRows; ++r) {
      for (int c = 0; c < grid.nRows; ++c) {
        StateWaTor s = (StateWaTor) grid.getState(r, c);
        List<Cell> neighbors = grid.getNeighborsOf(r, c);
        if (s == StateWaTor.FISH) {
          // move
          List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateWaTor.EMPTY);
          if (emptyNeighbors.size() > 0) {
            int idx = randomChoose(emptyNeighbors);
            emptyNeighbors.get(idx).setState(StateWaTor.MOVED_FISH, true);
            grid.setState(r, c, StateWaTor.EMPTY, true);
          }
        } else if (s == StateWaTor.MOVED_FISH) {
          // breed
          List<Cell> emptyNeighbors = sublistWithStateEquals(neighbors, StateWaTor.EMPTY);
          if (s.nDaysAlive > fishBreedDuration && emptyNeighbors.size() > 0) {
            Cell cell = emptyNeighbors.get(randomChoose(emptyNeighbors));
            cell.setState(StateWaTor.FISH, true);
          }
        }
      }
    }
  }
}
