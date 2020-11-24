package com.truecaller.tech.test.modal;

public class Board {
    private int numberOfRows;

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    private int numberOfColumns;
    private Tile[][] tiles;

    public Board(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        initializeEmptyBoard();
    }

    private void initializeEmptyBoard() {
        tiles = new Tile[numberOfRows][numberOfColumns];
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            tiles[rowIndex] = new Tile[numberOfColumns];
            for (int columnIndex = 0; columnIndex < numberOfRows; columnIndex++) {
                tiles[rowIndex][columnIndex] = new Tile(rowIndex, columnIndex);
            }
        }
    }

    public void printBoard() {

        System.out.println("Printing The Board");
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < numberOfRows; columnIndex++) {
                System.out.print(tiles[rowIndex][columnIndex].displayTile() + "  ");
            }
            System.out.println();
        }
    }

}
