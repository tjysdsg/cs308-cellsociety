1. Allow users to see two different Views of the simulation: a typical grid or a graph of the populations of all of the types of cells over the time of the simulation (without regard to where they are on the grid). The two views should be able to be shown independently or together (in separate windows is fine) as well as to be opened or closed dynamically while the other view is active. If one view is opened after another was started, then it should start at the current time (i.e., based on the same current model) and it does not have to "catch up".
NOTE: an example is described in the WaTor assignment or several more are shown at the bottom of the SugarScape discussion.


2. Allow users to run multiple simulations at the same time so they can compare the results side by side (i.e., do not use tabs like a browser).
NOTE: there are many UI decisions to be made here, such as: do you put them all in one window or separate (you control the arrangement or does the user)? do they need to be the same simulation type (makes graphing both easier)? can they be run independently or does pause stop all running simulations? etc., — document your decisions in your README


3. Allow users to interact with the simulation dynamically such that each change takes immediate effect on the current simulation:
    * [Done] change the values of the configuration parameters (as described in the WaTor assignment) within either the grid or graph View
NOTE: this requires GUI components like sliders or text fields organized in a separate panel and constructed based on the number of parameters needed by the current simulation
    * [Will do it with new GridPane] create or change a state at any grid location
NOTE: this requires listening to events directly occurring on the GUI component representing the grid


4. Allow users to change the View's appearance while the program is running between at least three different options:
    * [Done] select from different component styles (such as "dark" or "light" colors, Duke or UNC colors, small or larger fonts, etc.)
NOTE: changing the applied stylesheet has an immediate affect on the Scene
    * select from different languages used for the text displayed
NOTE: this is most easily done before a simulation View is created (say on a "splash" or overview scene) because otherwise it requires actively resetting the text of every appropriate Node already created rather than just changing the values in a ResourceBundle instance variable