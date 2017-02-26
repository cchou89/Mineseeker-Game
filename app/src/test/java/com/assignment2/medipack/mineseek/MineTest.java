package com.assignment2.medipack.mineseek;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A test to make sure Mine works
 */
public class MineTest {

    @Test
    public void parameterizedConstruct() throws Exception{
        int row = 4;
        int col = 2;
        boolean isMine = true;
        Mine testMine = new Mine(isMine, row, col);
        getRowCoord();
        getColCoord();
    }

    @Test
    public void getNearbyMines() throws Exception {
        Mine testMine = new Mine();
        assertEquals(0, testMine.getNearbyMines());
    }

    @Test
    public void getMineStatus() throws Exception {
        Mine testMine = new Mine();
        assertEquals(false, testMine.getMineStatus());
    }

    @Test
    public void getRowCoord() throws Exception {
        Mine testMine = new Mine();
        assertEquals(0, testMine.getRowCoord());
    }

    @Test
    public void getColCoord() throws Exception {
        Mine testMine = new Mine();
        assertEquals(0, testMine.getColCoord());
    }

    @Test
    public void setMine() throws Exception {
        boolean isMine = true;
        Mine testMine = new Mine();
        testMine.setMine(isMine);
        assertEquals(isMine, testMine.getMineStatus());
    }

    @Test
    public void setNearbyMines() throws Exception {
        int nearbyMines = 10;
        Mine testMine = new Mine();
        testMine.setNearbyMines(nearbyMines);
        assertEquals(nearbyMines, testMine.getNearbyMines());
    }

    @Test
    public void setColCoord() throws Exception {
        int col = 10;
        Mine testMine = new Mine();
        testMine.setColCoord(col);
        assertEquals(col, testMine.getColCoord());
    }

    @Test
    public void setRowCoord() throws Exception {
        int col = 10;
        Mine testMine = new Mine();
        testMine.setRowCoord(col);
        assertEquals(col, testMine.getRowCoord());
    }

}