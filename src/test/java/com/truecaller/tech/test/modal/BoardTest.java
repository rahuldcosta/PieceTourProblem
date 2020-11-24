package com.truecaller.tech.test.modal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Before
    public void setUp() throws Exception {
        board= new Board(10,10);
    }

    @Test
    public void getNumberOfRows() {
        assertEquals(10,board.getNumberOfRows());
    }

    @Test
    public void getNumberOfColumns() {
        assertEquals(10,board.getNumberOfColumns());
    }

    @Test
    public void getTiles() {
        assertEquals(10,board.getTiles().length);
    }

    @Test
    public void printBoard() {
        board.printBoard();
    }
}