## Refactoring Lab Discussion

### Team

10

### Names

- Jiyang Tang, jt304
- Andre Wang, jw542
- Tinglong Zhu, tz88

### Issues in Current Code

- Longest method: src/view/MainView.java:81
- Intra file duplication: src/controller/xml/SegregationXMLParser.java:49
- Too many nested if statements, flow of control etc
    - src/model/SimulationSegregation.java: 60
    - src/model/SimulationFire.java: 57
    - src/model/SimulationGOL.java: 44
    - src/model/SimulationPercolation.java: 44
    - src/model/SimulationWaTor.java: 145
    - src/model/Grid.java: 72
- Methods should not be empty
    - src/view/MainView.java: 41
    - src/cellsociety/Main.java: 10
    - src/controller/xml/XMLParser.java: 45

#### XMLParser.java

- Not using the latest APIs of simulation
- causing code duplicates

#### MainView.java

- Long methods
- Violates Single Responsibility Principle

### Refactoring Plan

- What are the code's biggest issues?
    - Long methods
    - Some components are not using the latest APIs of other components
- Which issues are easy to fix and which are hard?
    - Long methods are easy
    - Unused imports are easy
    - SRP violation is hard to fix
- What are good ways to implement the changes "in place"?
    - Intellij shows code changes in place, we use that to view the previous code, while refactoring
      the current one.

### Refactoring Work

- Empty methods in Simulation subclasses. We changed the base method from abstract to concrete, and
  removed the method definition in subclasses
