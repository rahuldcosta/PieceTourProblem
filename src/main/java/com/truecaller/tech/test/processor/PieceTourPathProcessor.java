package com.truecaller.tech.test.processor;

import com.truecaller.tech.test.exceptions.TilePositionException;
import com.truecaller.tech.test.modal.Board;
import com.truecaller.tech.test.modal.Tile;

public class PieceTourPathProcessor extends Thread {
    private static final int DIAGONAL_DISTANCE = 2;
    private static final int NON_DIAGONAL_DISTANCE = 3;
    private static int validTileMoves[][] =
            {{-DIAGONAL_DISTANCE, -DIAGONAL_DISTANCE},
                    {-DIAGONAL_DISTANCE, DIAGONAL_DISTANCE},
                    {DIAGONAL_DISTANCE, DIAGONAL_DISTANCE},
                    {DIAGONAL_DISTANCE, -DIAGONAL_DISTANCE},
                    {-NON_DIAGONAL_DISTANCE, 0},
                    {NON_DIAGONAL_DISTANCE, 0},
                    {0, -NON_DIAGONAL_DISTANCE},
                    {0, NON_DIAGONAL_DISTANCE}
            };
    private int maxNumberOfTiles;
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
        this.setMaxNumberOfTiles((board.getNumberOfRows() * board.getNumberOfColumns()) - 1);
    }

    public void setMaxNumberOfTiles(int maxNumberOfTiles) {
        this.maxNumberOfTiles = maxNumberOfTiles;
    }

    public Boolean isPieceTourPathAvailable(int rowNumber, int columnNumber) {

        try {
            if (validateTilePosition(rowNumber, columnNumber)) {
                initializeStartPosition(board.getTiles()[rowNumber][columnNumber]);
                return isPieceTourPathFound(rowNumber, columnNumber, 1);
            }
        } catch (TilePositionException e) {
            System.out.println(e.getMessage());
            System.out.println("Valid Board Limits are" + "\n" +
                    "For row number MIN=1 & MAX=" + board.getNumberOfRows() + "\n" +
                    "For column number MIN=1 & MAX=" + board.getNumberOfColumns() + "\n");
        }
        return false;
    }

    private void initializeStartPosition(Tile tile) {
        tile.setPositionValue(1);
    }

    Boolean validateTilePosition(int rowNumber, int columnNumber) throws TilePositionException {
        if (rowNumber >= 0 && rowNumber < board.getNumberOfRows() && columnNumber >= 0 && columnNumber < board.getNumberOfColumns())
            return true;
        throw new TilePositionException("Error Encountered:- Invalid Tile Location with values [" + rowNumber + "|" + columnNumber + "]");
    }

    public Boolean isPieceTourPathFound(int rowNumber, int columnNumber, int tileCounter) {
        Tile nextValidTile;
        for (int i = 0; i < maxNumberOfTiles; ++i) {
            nextValidTile = performNextValidMove(rowNumber, columnNumber, tileCounter++);
            if (nextValidTile == null)
                return false;
            rowNumber = nextValidTile.getRowNumber();
            columnNumber = nextValidTile.getColumnNumber();
        }

        return true;
    }

    public Tile performNextValidMove(int rowNumber, int columnNumber, int tileCounter) {
        int minimumOnwardMovementTileIndex = -1;
        int minimumOnwardMovementTileCount = (validTileMoves.length + 1);
        int nextRow;
        int nextColumn;

        for (int validTileMoveindex = 0; validTileMoveindex < validTileMoves.length; validTileMoveindex++) {
            nextRow = rowNumber + validTileMoves[validTileMoveindex][0];
            nextColumn = columnNumber + validTileMoves[validTileMoveindex][1];
            if (isAValidMove(nextRow, nextColumn)) {
                int currentEmptyOnwardTileCount = emptyOnwardTilesCount(nextRow, nextColumn);
                if (currentEmptyOnwardTileCount < minimumOnwardMovementTileCount) {
                    minimumOnwardMovementTileIndex = validTileMoveindex;
                    minimumOnwardMovementTileCount = currentEmptyOnwardTileCount;
                }
            }
        }
        if (allTilesAreVisited(minimumOnwardMovementTileIndex)) return null;

        return getNextValidTile(rowNumber, columnNumber, minimumOnwardMovementTileIndex, tileCounter);
    }

    private Tile getNextValidTile(int rowNumber, int columnNumber, int minimumEmptyOnwardTileCountIndex, int tileCounter) {
        int nextRow = rowNumber + validTileMoves[minimumEmptyOnwardTileCountIndex][0];
        int nextColumn = columnNumber + validTileMoves[minimumEmptyOnwardTileCountIndex][1];
        board.getTiles()[nextRow][nextColumn].setPositionValue(tileCounter + 1);
        return board.getTiles()[nextRow][nextColumn];
    }

    private boolean allTilesAreVisited(int minimumEmptyOnwardTileCountIndex) {
        if (minimumEmptyOnwardTileCountIndex == -1)
            return true;
        return false;
    }

    public int emptyOnwardTilesCount(int row, int col) {
        int emptyOnwardTilesCounter = 0;
        for (int validMovesIndex = 0; validMovesIndex < validTileMoves.length; ++validMovesIndex)
            if (isAValidMove(row + validTileMoves[validMovesIndex][0], col + validTileMoves[validMovesIndex][1]))
                emptyOnwardTilesCounter++;

        return emptyOnwardTilesCounter;
    }


    private boolean isAValidMove(int nextRowNumber, int nextColumnNumber) {
        return checkIfMoveIsWithinBoardBoundaries(nextRowNumber, nextColumnNumber) && !board.getTiles()[nextRowNumber][nextColumnNumber].isTileVisited();
    }

    private boolean checkIfMoveIsWithinBoardBoundaries(int rowBoundary, int columnBoundary) {
        return (rowBoundary >= 0 && rowBoundary < board.getNumberOfRows()) && (columnBoundary >= 0 && columnBoundary < board.getNumberOfColumns());
    }
}
