# Cell Society Design Final

### Names

- Andre Wang, jw542
- Jiyang Tang, jt304
- Tinglong Zhu, tz88

## Team Roles and Responsibilities

* Team Member #1

Andre Wang: Views

* Team Member #2

Jiyang Tang: Models

* Team Member #3

Tinglong Zhu: Controllers

## Design goals

The project is designed to have several cell automata simulation and different grid configurations,
and it should be easy for users to modify the simulation. Also, new kinds of simulation should be
added with ease.

#### What Features are Easy to Add

- Neighborhood is easy to switch
- Easy to add new XML parser
- Simulation parameters are easy to add (`ControllableParam`)

## High-level Design

For the models, `Simulation` is responsible for storing, updating the simulation data, and
coordinating `Grid`, `Cell`, and `State`.

For views, `MainView` renders the final visualization seen by users. `MainView` also passes the
received user inputs to `Controller`. `MainView` also contains a display of current simulation
status, and provides interactive UI widgets for users to control game settings.

In terms of controllers, `Controller` handles user input events from View and passes relevant
commands to `Simulation`. `XMLParser` is used to read configuration files.
`Controller` applies the configuration to the models at start, update the game according to user
inputs by calling relevant methods of models and telling views to render the models.

#### Core Classes

- XMLParser
- XMLWriter
- XMLException
- Controller
- SettingReader
- Simulation
- Neighborhood
- Grid
- Cell
- State
- StateEnum
- EdgeType
- MainView
- ControllableParam
- LabelResource
- ActionButton
- PopulationGraph

## Assumptions that Affect the Design

- It's assumed that a cell can always be access using `(r, c)` where `r` is the row index, and `c`
  is the column index

- The error checking of adjusting the params of the simulation,
    * Checking in view, model and controller are proposed.
    * If the model take the responsibility, the controller has to add other exceptions from model to
      prevent the program from crashing. If the controller take the responsibility, then the View
      has to implement more error windows. And the controller class would be complicated. If the
      View and Controller work together. It makes the user more difficult to change the params, also
      with less choices. But easier for us to implement. As an extra file for restriction is all we
      need.
    * We choose the View and Controller work together version. As it is the easiest implementation,
    * We are satisfied with it. If we have time, we would try to put it into model or controller to
      see whether it has better effects.

#### Features Affected by Assumptions

- We cannot implement general tilings
- Users can only increase/decrease parameters by only a predefined step value

## Significant differences from Original Plan

- View classes are stuffed into one main class, containing many complex methods
- User input is passed from View to Controller, instead of directly accessed by Controller

## New Features HowTo

### How to add a new simulation

To add a new simulation,

First, create an enum implementing `StateEnum` interface, and optionally, create a subclass
of `State`. Whether to create a subclass of `State` depends on whether you want to store custom data
in `State`. The enum should implement the methods declared in the interface, they are fairly
straightforward, so you can read the example:

Second, create a subclass of `Simulation` (in src/model/Simulation.java), and implement the
following method:

1. Constructor. Initialize `grid` (`Grid` instance representing the grid) and `simType` (name of the
   simulation) in it.

2. `getStatsMap` and `getStatsName`. You should return a map containing the name of a statistics and
   the value of it in `getStatsMap`, and return a list containing the name of a statistics, with the
   order you want them to be shown in on the window, in `getStatsName`. Technically, the key of the
   statistic map is the key of the string defined in language property files, so you also need to
   add an entry to the file.

3. `updateNextStates` and `updateStats`. The former method update the whole grid based on the
   current generation of states, while the latter updates the statistics of the simulation.

Third, add a subclass of `XMLParser` (in src/controller/XMLParser)
for reading the certain parameters and error-checking, and implement the following methods:

- Add simulation-specific parameters in `initSimulation` method
- Initialize the parameters used by the simulation in `initStateArray`
- Add the parameter's name and range to the `data/simulationControl` for the error checking
- Add valid/invalid XML file to the `data/gameconfig` directory for initialization
- Add a new case branch to the `setXMLParser` method in the `Controller` and set the parser to the
  newly created one

#### Other Features not yet Done

- Foraging Ant is not working
- Langton's Loop example XML doesn't have many empty spots, so the self-replicating feature is not
  presented correclty
- Infinite edge type
- Hexagon/triangle grid partially implemented
- Some XML files (cell init methods) not implemented
- Whether grid locations should be outlined is not customizable
- Some error checking is not comprehensive, for example, pressing Start button without reseting the
  previous game is broken, but no warnings is raised

