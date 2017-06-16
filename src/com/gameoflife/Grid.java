package com.gameoflife;


import java.util.*;

/**
 * Created by sujit on 6/15/17.
 */
public class Grid {

    private Set<Position> grid = new TreeSet<>();

    public void setLive(int row, int col) {
        grid.add(new Position(row, col));
    }

    public boolean isLive(int row, int col) {
        Optional<Position> cell = grid.stream().filter(c -> c.equals(new Position(row, col))).findFirst();
        return cell.isPresent();
    }

    public void setDead(int row, int col) {
        grid.remove(new Position(row, col));
    }

    public static Grid withSeed(int[][] seed) {
        Grid grid = new Grid();
        for (int[] cell : seed) {
            grid.setLive(cell[0], cell[1]);
        }
        return grid;
    }

    public void nextGeneration() {
        final Set<Position> newGrid = new TreeSet<>();
        grid.forEach(cell -> {
            long liveNeighboursCount = getLiveNeighboursCount(cell);
            if (liveNeighboursCount >= 2 && liveNeighboursCount <= 3) {
                newGrid.add(cell);
            }
            List<Position> neighbors = getNeighbors(cell);
            neighbors.forEach(n -> {
                if (!grid.contains(n)) {
                    long liveCount = getLiveNeighboursCount(n);
                    if (liveCount == 3) {
                        newGrid.add(n);
                    }
                }
            });
        });

        grid = newGrid;

    }

    private long getLiveNeighboursCount(Position cell) {
        List<Position> neighbors = getNeighbors(cell);
        return neighbors.stream().filter(grid::contains).count();
    }

    private List<Position> getNeighbors(Position cell) {
        int[] cellDistance = {0, -1, 1};
        List<Position> neighbors = new ArrayList<>();
        Arrays.stream(cellDistance).forEach(d1 -> Arrays.stream(cellDistance).forEach(d2 -> {
            if (!(d1 == 0 && d2 == 0)) {
                Position neighbor = cell.getNeighbor(d1, d2);
                if (neighbor.isValid()) {
                    neighbors.add(neighbor);
                }
            }
        }));
        return neighbors;
    }

    public void print() {
        int previousRow = 0;
        int previousCol = 0;
        for (Position cell : grid) {
            if (previousRow != cell.getRow()) {
                System.out.println();
                previousCol = 0;
            }
            for (int i = 0; i < cell.getCol() - previousCol - 1; i++)
                System.out.print("  ");
            System.out.print(new String(Character.toChars(0x25A0)));
            System.out.print("  ");

            previousRow = cell.getRow();
            previousCol = cell.getCol();
        }
        System.out.println("\n\n");
    }
}
