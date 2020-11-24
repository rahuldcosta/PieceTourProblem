package com.truecaller.tech.test.processor;

import com.truecaller.tech.test.exceptions.TilePositionException;
import com.truecaller.tech.test.modal.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PieceTourPathProcessorTest {
    PieceTourPathProcessor pieceTourPathProcessor;
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board(10, 10);
        pieceTourPathProcessor = new PieceTourPathProcessor();
        pieceTourPathProcessor.setBoard(board);
    }

    @Test
    public void isPieceTourPathAvailable() {
        assertTrue(pieceTourPathProcessor.isPieceTourPathAvailable(0, 0));
    }

    @Test
    public void isPieceTourPathAvailableWithAllAreVisiteTileAndNoPathFound() {
        pieceTourPathProcessor.setBoard(new Board(2,2));
        assertFalse(pieceTourPathProcessor.isPieceTourPathAvailable(0, 0));
    }

    @Test
    public void testisPieceTourPathAvailableWithException() {
        try {
            pieceTourPathProcessor.validateTilePosition(0, -1);
        } catch (TilePositionException e) {
            assertEquals("Error Encountered:- Invalid Tile Location with values [0|-1]", e.getMessage());
        }

    }
    @Test
    public void testisPieceTourPathAvailableWithExceptionCaught() {
            pieceTourPathProcessor.isPieceTourPathAvailable(0, -1);
    }


}