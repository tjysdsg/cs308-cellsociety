# Cell Society Design Plan

### Team Number

### Names

## Introduction

The project structure is similar to an MVC application, `View` renders `Model` using
javafx, `Controller` handles user inputs (or file inputs), interprets the inputs, and passes
commands to `Model`, `Model` perform actions on its the data according to the received commands.

## Overview

![overview](overview.png)

For the models, `Simulation` is responsible for storing, updating the simulation data, and
coordinating `Grid`, `Cell`, and `State`.

For views, `MainView` combines `SimulationView`, `UIView` together and renders the final
visualization seen by users. `SimulationView` is the visual representation of the cellular
simulation. `StatusView` and `SettingsView` are both `UIView`, the former serves as a display of
current simulation status, while the latter provides interactive UI widgets for users to control
game settings.

In terms of controllers, `InputController` handles user input events and passes them
to `MainController`. `ConfigController` is used to read configuration files.
`MainController` apply the configuration to the models at start, update the game according to user
inputs by calling relevant methods of models and telling views to render the models.

## User Interface

## Configuration File Format

## Design Details

## Design Considerations

## Team Responsibilities

* Team Member #1

* Team Member #2

* Team Member #3
