package fr.ua.heugue_ydee.pcg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Cellular automate based on forest fire rule
 */
public class ErosionAutomata {
    List<List<Integer>> grid;

    private static final int GRASS_TILE = 0;
    private static final int EARTH_TILE = 1;
    private static final int WATER_TILE = 2;

    private static final float EARTH_APPARITION_RATE = 0.2f;
    private static final float ERROSION_RATE = 0.25f;

    /**
     * Build the base grid for the cellular automate
     *
     * @param width  The width of the grid
     * @param height The height of the grid
     */
    public ErosionAutomata(final int width, final int height) {
        this.grid = new ArrayList<List<Integer>>();
        Random generator = new Random();

        for (int i = 0; i < width; ++i) {
            this.grid.add(new ArrayList<Integer>());
            for (int j = 0; j < height; ++j) {
                this.grid.get(i).add((generator.nextFloat() < EARTH_APPARITION_RATE ? EARTH_TILE : GRASS_TILE));
            }
        }
    }

    /**
     * Get the mutation value for a cell value
     *
     * @param cellValue The cell value
     * @return The value of the cell after mutation
     */
    private static int transitionValue(final int cellValue) {
        if (cellValue == GRASS_TILE) {
            return EARTH_TILE;
        }
        return WATER_TILE;
    }

    /**
     * Get the mutation rate of a cell, given its differents neighbors
     *
     * @param value The value of the cell
     * @param nbDifferentNeighbors The number of differents neighbors
     * @return The transition rate of the cell
     */
    private static float getTransitionRate(final int value, final int nbDifferentNeighbors) {
        if (value == WATER_TILE) {
            return ERROSION_RATE * nbDifferentNeighbors;
        }
        return 2 - nbDifferentNeighbors;
    }

    /**
     * Compute the next state of the cell
     *
     * @param grid The grid to use for computation
     * @param x    The abscissa position of the cell to compute
     * @param y    The ordinate position of the cell to compute
     * @return The next value of the cell
     */
    private static int nextCellStep(final List<List<Integer>> grid, final int x, final int y) {

        Integer cellValue = grid.get(x).get(y);
        int nbDifferentNeighbor = 0;

        if (cellValue.equals(WATER_TILE)) {
            return WATER_TILE;
        }

        if (x != 0) {
            if (!grid.get(x - 1).get(y).equals(cellValue) && (cellValue == EARTH_TILE
                    || (grid.get(x - 1).get(y).equals(EARTH_TILE) && cellValue.equals(GRASS_TILE)))) {
                nbDifferentNeighbor += 1;
            }
            if (y != 0) {
                if (!grid.get(x - 1).get(y - 1).equals(cellValue) && (cellValue == EARTH_TILE
                        || (grid.get(x - 1).get(y - 1).equals(EARTH_TILE) && cellValue.equals(GRASS_TILE)))) {
                    nbDifferentNeighbor += 1;
                }
            }

            if (y != grid.get(x).size() - 1) {
                if (!grid.get(x - 1).get(y + 1).equals(cellValue) && (cellValue == EARTH_TILE
                        || (grid.get(x - 1).get(y + 1).equals(EARTH_TILE) && cellValue.equals(GRASS_TILE)))) {
                    nbDifferentNeighbor += 1;
                }
            }
        }


        if (x != grid.size() - 1) {
            if (!grid.get(x + 1).get(y).equals(cellValue) && (cellValue == EARTH_TILE
                    || (grid.get(x + 1).get(y).equals(EARTH_TILE) && cellValue.equals(GRASS_TILE)))) {
                nbDifferentNeighbor += 1;
            }
            if (y != 0) {
                if (!grid.get(x + 1).get(y - 1).equals(cellValue) && (cellValue == EARTH_TILE
                        || (grid.get(x + 1).get(y - 1).equals(EARTH_TILE) && cellValue.equals(GRASS_TILE)))) {
                    nbDifferentNeighbor += 1;
                }
            }

            if (y != grid.get(x).size() - 1) {
                if (!grid.get(x + 1).get(y + 1).equals(cellValue) && (cellValue == EARTH_TILE
                        || (grid.get(x + 1).get(y + 1).equals(EARTH_TILE) && cellValue.equals(GRASS_TILE)))) {
                    nbDifferentNeighbor += 1;
                }
            }
        }

        if (y != 0) {
            if (!grid.get(x).get(y - 1).equals(cellValue) && (cellValue == EARTH_TILE
                    || (grid.get(x).get(y - 1).equals(EARTH_TILE) && cellValue.equals(GRASS_TILE)))) {
                nbDifferentNeighbor += 1;
            }
        }

        if (y != grid.get(x).size() - 1) {
            if (!grid.get(x).get(y + 1).equals(cellValue) && (cellValue == EARTH_TILE
                    || (grid.get(x).get(y + 1).equals(EARTH_TILE) && cellValue.equals(GRASS_TILE)))) {
                nbDifferentNeighbor += 1;
            }
        }


        Random generator = new Random();

        return (generator.nextFloat() < getTransitionRate(cellValue, nbDifferentNeighbor) ? transitionValue(cellValue) : cellValue);
    }

    /**
     * Return the next step of the grid
     *
     * @return The grid which is the result of the life cycle
     */
    private static List<List<Integer>> nextStep(final List<List<Integer>> grid) {
        List<List<Integer>> cycleResult = new ArrayList<List<Integer>>();

        for (int i = 0; i < grid.size(); ++i) {
            cycleResult.add(new ArrayList<Integer>());
            for (int j = 0; j < grid.get(i).size(); ++j) {
                cycleResult.get(i).add(ErosionAutomata.nextCellStep(grid, i, j));
            }
        }

        return cycleResult;
    }

    /**
     * Launch the simulation of the cellular automate for n cycle
     *
     * @param n The number of the simulation cycles
     * @return The result of the simulation
     */
    public List<List<Integer>> simulate(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The number of simulation must be positive or zero");
        }

        List<List<Integer>> newGrid = new ArrayList<List<Integer>>(grid);

        for (int i = 0; i < n; ++i) {
            newGrid = nextStep(newGrid);
        }

        return newGrid;
    }
}
