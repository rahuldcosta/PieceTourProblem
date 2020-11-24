package com.truecaller.tech.test.modal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TileTest {

    Tile tile;

    @Before
    public void setUp() throws Exception {
        tile = new Tile(1, 1);
        tile.setPositionValue(-1);
    }


    @Test
    public void displayTile() {
        assertEquals("[-1]", tile.displayTile());
    }

    @Test
    public void getPositionValue() {
        assertEquals(-1, tile.getPositionValue());
    }

    @Test
    public void isTileVisited() {
        assertFalse(tile.isTileVisited());
    }

    @Test
    public void getRowNumber() {
        assertEquals(1, tile.getRowNumber());
    }

    @Test
    public void getColumnNumber() {
        assertEquals(1, tile.getColumnNumber());
    }
}