package com.gameoflife;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sujit on 6/15/17.
 */
public class GridTest {

    @Test
    public void shouldBeAbleToCrateAGridWithALiveCell() {
       Grid grid = new Grid();
       grid.setLive(1, 1);
       assertTrue(grid.isLive(1, 1));
    }

    @Test
    public void shouldBeAbleToCrateAGridWithADeadCell() {
        Grid grid = new Grid();
        grid.setDead(1, 1);
        assertFalse(grid.isLive(1, 1));
    }

    @Test
    public void shouldBeAbleToCrateAGridWithASeed() {
        Grid grid = Grid.withSeed(new int[][]{
                {1, 1},
                {1, 2},
        });
        assertTrue(grid.isLive(1, 1));
        assertTrue(grid.isLive(1, 2));
        assertFalse(grid.isLive(2, 2));
    }

    @Test
    public void cellWithFewerThanTwoNeighborsDiesInNextGeneration() {
        Grid grid = Grid.withSeed(new int[][]{
                {1, 1}
        });

        grid.nextGeneration();

        assertFalse(grid.isLive(1, 1));
    }

    @Test
    public void cellWithTwoNeighborsDoeNotDieInNextGeneration() {
        Grid grid = Grid.withSeed(new int[][]{
                {1, 1},
                {1, 2},
                {2, 2}
        });

        grid.nextGeneration();

        assertTrue(grid.isLive(1, 1));
        assertTrue(grid.isLive(1, 2));
        assertTrue(grid.isLive(2, 2));
    }

    @Test
    public void cellWithThreeNeighborsDoeNotDieInNextGeneration() {
        Grid grid = Grid.withSeed(new int[][]{
                {1, 1},
                {1, 2},
                {2, 1},
                {2, 2}
        });

        grid.nextGeneration();

        assertTrue(grid.isLive(1, 1));
        assertTrue(grid.isLive(1, 2));
        assertTrue(grid.isLive(2, 1));
        assertTrue(grid.isLive(2, 2));
    }

    @Test
    public void cellWithMoreThanThreeNeighborsDiesInNextGeneration() {
        Grid grid = Grid.withSeed(new int[][]{
                {1, 1},
                {1, 2},
                {2, 1},
                {2, 2},
                {3, 2}
        });

        grid.nextGeneration();

        assertFalse(grid.isLive(2, 2));
    }

    @Test
    public void deadCellWithThreeLiveNeighborsComesToLifeInNextGeneration() {
        Grid grid = Grid.withSeed(new int[][]{
                {1, 1},
                {1, 2},
                {2, 2}
        });

        grid.nextGeneration();

        assertTrue(grid.isLive(2, 1));
    }

    @Test
    public void deadCellWithLessThanThreeLiveNeighborsShouldStayDeadInNextGeneration() {
        Grid grid = Grid.withSeed(new int[][]{
                {1, 1},
                {1, 2}
        });

        grid.nextGeneration();

        assertFalse(grid.isLive(2, 1));
    }

    @Test
    public void blinkerPatternOscillator() {
        Grid grid = Grid.withSeed(new int[][]{
                {1, 1},
                {1, 0},
                {1, 2}
        });

        grid.nextGeneration();
        assertTrue(grid.isLive(1, 1));
        assertTrue(grid.isLive(0, 1));
        assertTrue(grid.isLive(2, 1));

        grid.nextGeneration();
        assertTrue(grid.isLive(1, 1));
        assertTrue(grid.isLive(1, 0));
        assertTrue(grid.isLive(1, 2));
    }

}
