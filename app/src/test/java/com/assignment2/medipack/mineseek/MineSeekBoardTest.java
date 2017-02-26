package com.assignment2.medipack.mineseek;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A test to make sure that the MineSeekBoard works
 */
public class MineSeekBoardTest {
    @Test
    public void testBoardExists() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard();
        assertNotNull(testBoard.getGameBoard());
    }

    @Test
    public void setRows() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard();
        int testRow = 7;
        testBoard.setRows(testRow);
        assertEquals(testRow, testBoard.getGameBoard().length);
    }

    @Test
    public void setCols() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard();
        int testCol = 7;
        testBoard.setCols(testCol);
        assertEquals(testCol, testBoard.getGameBoard()[0].length);
    }

    @Test
    public void setNumMines() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard();
        int numMines = 5;
        testBoard.setNumMines(numMines);
        assertEquals(numMines, testBoard.getNumMines());
    }

    @Test
    public void getRows() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard();
        int defaultVal = 4;
        assertEquals(defaultVal, testBoard.getRows());
    }

    @Test
    public void getCols() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard();
        int defaultVal = 6;
        assertEquals(defaultVal, testBoard.getCols());
    }

    @Test
    public void scanMines() throws Exception {
        int testMines = 4;
        MineSeekBoard testBoard = new MineSeekBoard(2, 1, 1);
        int scan = testBoard.scanMines(testBoard.getGameBoard()[0][0]);
        assertEquals(1, scan);
    }

    @Test
    public void checkSquare() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard();
        testBoard.placeMines(0,0);
        Mine test = testBoard.getGameBoard()[0][0];
        assertEquals(true, testBoard.checkSquare(test));
    }

    @Test
    public void selectSpace() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard(2, 1, 2);
        Mine test = testBoard.getGameBoard()[0][0];
        assertEquals(0, test.getNearbyMines());
        testBoard.selectSpace(test);
        assertEquals(2, test.getNearbyMines());
        assertEquals(1, testBoard.getScore());
        assertEquals(1, testBoard.getNumScans());
    }

    @Test
    public void placement() throws Exception {
        MineSeekBoard testBoard = new MineSeekBoard(3, 3, 0);
        testBoard.placeMines(0,1);
        testBoard.placeMines(0,2);
        Mine test = testBoard.getGameBoard()[0][1];
        int count = testBoard.scanMines(test);
        assertEquals(2, count);
    }

}