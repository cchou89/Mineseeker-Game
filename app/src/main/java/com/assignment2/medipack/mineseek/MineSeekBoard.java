package com.assignment2.medipack.mineseek;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Mineseeker board class file
 * for housing all the game data and actions needed for the MineSeeker game
 */

public class MineSeekBoard extends Mine {
    private int rows;
    private int cols;
    private int numMines;
    private int minesFound;
    private int numScans;
    private List<Mine> mineList;
    private Mine[][] gameBoard;

    //Default Constructor
    MineSeekBoard() {
        rows = 4;
        cols = 6;
        numMines = 9;
        minesFound = 0;
        numScans = 0;
        //Handy dandy list of mine locations
        mineList = new ArrayList<Mine>();
        //Build the gameboard
        buildBoard(rows, cols, numMines);
    }

    //Parameterized Constructor
    MineSeekBoard(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        numMines = mines;
        minesFound = 0;
        numScans = 0;
        //Handy dandy list of mine locations
        mineList = new ArrayList<Mine>();
        buildBoard(rows, cols, numMines);

    }

    public void buildBoard(int rows, int cols, int numMines) {
        //Build the gameboard
        gameBoard = new Mine[rows][];
        for (int i = 0; i < rows; i++) {
            gameBoard[i] = new Mine[cols];
            for (int j = 0; j < cols; j++) {
                gameBoard[i][j] = new Mine(false, i, j);
            }
        }
        //set up the mines
        for (int i = 0; i < numMines; i++) {
            int randRow = randomRow();
            int randCol = randomCol();
            placeMines(randRow, randCol);
        }
    }

    //Setters
    public void setRows(int rowNum) {
        rows = rowNum;
        buildBoard(rows, cols, numMines);
    }

    public void setCols(int colNum) {
        cols = colNum;
        buildBoard(rows, cols, numMines);
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
        buildBoard(rows, cols, numMines);
    }

    //Getters
    public Mine[][] getGameBoard() {
        return gameBoard;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getNumMines() {
        return numMines;
    }

    public int getMinesFound() {
        return minesFound;
    }

    public int getNumScans() {
        return numScans;
    }

    //Randomize
        //Random Row
    private int randomRow() {
        Random seed = new Random();
        int row = seed.nextInt(rows);
        return row;
    }
        //Random Column
    private int randomCol() {
        Random seed = new Random();
        int col = seed.nextInt(cols);
        return col;
    }

    //Gameplay Functions
    public void placeMines(int randRow, int randCol) {
        Mine mine = gameBoard[randRow][randCol];
        //check if there's already a mine there
        while (checkSquare(mine)) {
            //place it somewhere else
            int row = randomRow();
            int col = randomCol();
            mine = gameBoard[row][col];
        }
        mine.setMine(true);
        mineList.add(mine);
    }

    public int scanMines(Mine space) {
        int numMines = 0;
        int row = space.getRowCoord();
        int col = space.getColCoord();
        for (int i = 0; i < mineList.size(); i++) {
            //Pull mine records from the list
            Mine mine = mineList.get(i);
            int checkRow = mine.getRowCoord();
            int checkCol = mine.getColCoord();
            //If the space is in the same column as the mine and the mine ISN'T uncovered
            if (mineList.get(i).getColCoord() == col) {
                numMines++;
                //If the space is in the same row and the mine ISN'T uncovered
            } else if (mineList.get(i).getRowCoord() == row) {
                numMines++;
            }
        }
        return numMines;
    }

    public boolean checkSquare(Mine space) {
        boolean isAMine = space.isMine();
        return isAMine;
    }

    public void selectSpace(Mine space) {
        //Check square for mines
        if (checkSquare(space)) {
            minesFound++;
            //update nearby spaces
            int targetRow = space.getRowCoord();
            int targetCol = space.getColCoord();
            //update vertically on nearby mines
            for(int i = 0; i < rows; i++){
                Mine mine = gameBoard[i][targetCol];
                int nearbyMines = mine.getNearbyMines();
                nearbyMines--;
                mine.setNearbyMines(nearbyMines);
            }
            //update horizontally
            for(int i = 0; i < cols; i++){
                Mine mine = gameBoard[targetRow][i];
                int nearbyMines = mine.getNearbyMines();
                nearbyMines--;
                mine.setNearbyMines(nearbyMines);
            }
        }else{
            numScans++;
        }
    }
}

