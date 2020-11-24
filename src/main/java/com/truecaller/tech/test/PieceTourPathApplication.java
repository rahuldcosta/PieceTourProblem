package com.truecaller.tech.test;

import com.truecaller.tech.test.modal.Board;
import com.truecaller.tech.test.processor.PieceTourPathProcessor;

public class PieceTourPathApplication {


    public static void main(String args[]) {
        int numberOfRows = 10;
        int numberOfColumns = 10;
        Board board;
        board = new Board(numberOfRows, numberOfColumns);
        PieceTourPathProcessor boardProcessor = new PieceTourPathProcessor();
        boardProcessor.setBoard(board);
        if (boardProcessor.isPieceTourPathAvailable(0, 0)) {
            board.printBoard();
        } else {
            System.out.println("Path Note Found for Tile Position row:- " + 0 + " | column:- " + 0);
        }

    }

}
