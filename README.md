Cell Society
====

This project implements a cellular automata simulator.

Names:

- Jiyang Tang, jt304
- Andre Wang, jw542
- Tinglong Zhu, tz88

### Timeline

Start Date: Feb 18th 2021 for the complete implementation part basic implementation was done
earlier.

Finish Date: Feb 22th 2021

Hours Spent: 50 combined man hours

### Primary Roles

- jt304: Model/Simulation, some UI fixes, some XML files
- jw542: UI/ View , some `controller` and `Main` functionality.
- tz88 : `Controller`, `XML Parser`, Exception Handler, XML generation.

### Resources Used

oracle docs for javafx.

### Running the Program

Main class: `Main` in the `CellSocieity` directory

Data files needed: files in data. Especially, cssfiles, gameconfigs, and propoerties files.

Features implemented:

1. Simulation
    * Types of simulation:
        * Fire
        * Game of Life
        * Percolation
        * Wa-Tor
        * Segregation
        * Rock, Paper, Scissors
        * SugarScape
        * Foraging Ant
        * Langton's Loop
    * Allow different kinds of grid edge types:
        * finite: bounded by the initial size
        * toroidal: edge with wrapping around
    * Allow different kinds of grid location shapes:
        * square: with 8 neighbors max
        * triangular: with 12 neighbors max
        * hexagonal: with 6 neighbors max


2. UI
    * Splash screen for different language options
    * All buttons (save progress button, hide/show Population Graph button), control panel buttons.
    * DropDownLists for color, css, different xml configurations.
    * Slider to control speed
    * Display Status, such as current number of fish
    * Population Graph, multiple lined graph each track the progression of a species. Make sure it
      doesn't go over a certain range.
    * Ability to create new windows of simulation
    * Spinners for users to dynamically control certain inherent properties during the simulation,
      such as fish breed time.
    * etc.

3. Configuration:
    * Allow random cell initialization.
    * Allow cell initialization by locations.
    * Connect the backend model and front end view.
    * Output current state
    * XML expert (including initialization and xml r/w)
    * check invalid xml files(including all kinds of invalid values, forms, etc.)
    * provide some APIs needed by UI part

### Notes/Assumptions

Assumptions or Simplifications:

Interesting data files:

We like the italian.properties file, which we translated on our own with using google

Known Bugs:

Ants simulation does not work properly.

Extra credit:

Some code is based on StackOverflow, the links are in the comments

### Impressions

