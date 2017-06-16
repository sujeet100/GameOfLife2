package com.gameoflife;

/**
 * Created by sujit on 6/15/17.
 */
public class Position implements Comparable<Position> {

    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return col == position.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }



    public Position getNeighbor(int rowDistance, int colDistance) {
        return new Position(this.row + rowDistance, this.col + colDistance);
    }

    public boolean isValid() {
        return this.row >=0 && this.col >=0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public int compareTo(Position o) {
        if(this.row == o.row) {
            if(this.col == o.col) {
                return 0;
            } else if(this.col > o.col) {
                return 1;
            } else {
                return -1;
            }
        } else if(this.row > o.row) {
            return 1;
        } else {
            return -1;
        }
    }
}
