# Simulation Lab Discussion

## Cell Society

## Names and NetIDs

- Jiyang Tang, jt304
- Andre Wang, jw542
- Tinglong Zhu, tz88

### High Level Design Ideas

### CRC Card Classes

```java
public class Cell {

  public State getState();

  public void setState(State s);

  public Point2D getCoord();

  public void setCoord(Point2D coord);
}
```

```java
public class State {

}
```

```java
public class Grid {

  public Grid(int width, int height);

  public List<Cell> getNeighborsOf(Cell cell);

  public boolean checkIfEdgeCell(Cell cell);

  public List<Cell> getCellsToUpdate();
}
```

```java
public class Simulation {

  public State computeNewState(List<Cell> neighbors, Cell cell);

  public void loadParamsFromFile(String filename);

}
```

```java
public class Game {

  public void setSimulation(Simulation sim);
}
```

### Use Cases

* Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of
  neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)

```java
    Simulation sim=new Simulation();
    Grid grid=new Grid();
    Cell cell=new Cell();

    State s=sim.computeNewState(grid.getNeighbors(cell),cell);
    cell.setState(s);
```

* Apply the rules to an edge cell: set the next state of a cell to live by counting its number of
  neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors
  missing)

```java
    Simulation sim=new Simulation();
    Grid grid=new Grid();
    Cell cell=new Cell();

    State s=sim.computeNewState(grid.getNeighbors(cell),cell);
    cell.setState(s);
```

* Move to the next generation: update all cells in a simulation from their current state to their
  next state and display the result graphically

```java
    List<Cell> cells=grid.getCellsToUpdate();
    for(Cell c:cells){
    State s=sim.computeNewState(grid.getNeighbors(c),c);
    c.setState(s);
    }
```

* Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based
  on the value given in an XML file

```java
    sim.loadParamsFromFile("params.xml");
```

* Switch simulations: use the GUI to change the current simulation from Game of Life to Wa-Tor

```java
class SomeOtherSimulation extends Simulation {
}

  Game game = new Game();
  game.setSimulation(new SomeOtherSimulation());
```
