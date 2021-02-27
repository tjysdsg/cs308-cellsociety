package model;

/**
 * Edge types
 * <p>
 * FINITE: bounded by the initial size, with locations on the edges having smaller neighborhoods
 * <p>
 * WRAP: (toroidal) bounded by the initial size, with locations on the edges having full size
 * neighborhoods such that the neighbors past the edge are taken from the opposite side of the grid
 * <p>
 * INFINITE: unbounded, as cells become active on the edge the grid is expanded to include them and
 * their neighbors
 */
public enum EdgeType {
  FINITE, WRAP, INFINITE,
}
