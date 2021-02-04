# Simulation Lab Discussion

## Rock Paper Scissors

## Names and NetIDs

- Jiyang Tang, jt304
- Andre Wang, jw542
- Tinglong Zhu, tz88

### High Level Design Ideas

### CRC Card Classes

#### Player

##### Responsibilities

- Score counting
- Ask for input

##### Collaborators

- Game
- Weapon

##### Weapon

##### Responsibilities

- Stores an unique identifier

##### Collaborators

- Player
- Rule

#### Rule

##### Responsibilities

- Load config file
- Compare two weapons and find out which wins
- Add new weapons, update relationships

##### Collaborators

- Weapon

#### Game

##### Responsibilities

- For each round, use player input to update their scores according to rules
- Choose a set of rules

##### Collaborators

- Player
- Rule

### Use Cases

