package com.truecaller.tech.test.modal;

public class Tile {

    private int rowNumber;
    private int columnNumber;
    private int positionValue;


    public Tile(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.positionValue = -1;
    }

    public String displayTile() {
        return "[" + positionValue + "]";
    }


    public int getPositionValue() {
        return positionValue;
    }

    public void setPositionValue(int positionValue) {
        this.positionValue = positionValue;
    }

    public Boolean isTileVisited() {
        return this.positionValue != -1;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

}
