package com.assignment2.medipack.mineseek;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Medipack on 2017-02-20.
 */

public class MineSeekBoard extends Mine{
    private int rows;
    private int cols;
    private int numMines;
    private int score;
    private int numScans;
    List<Mine> mineList;
    Mine gameBoard[][];

    //Default Constructor
    MineSeekBoard(){
        rows = 4;
        cols = 6;
        numMines = 9;
        score = 0;
        numScans=0;
        //Handy dandy list of mine locations
        mineList = new ArrayList<Mine>();
        //Build the gameboard
        for (int i = 0; i< cols; i++){
            gameBoard [i] = new Mine[rows];
        }
        //set up the mines
        for (int i = 0; i < numMines; i++){
            int randRow = randomRow();
            int randCol = randomCol();
            placeMines(randRow, randCol);
        }
    }

    //Parameterized Constructor
    MineSeekBoard(int rows, int cols, int mines){
        setRows(rows);
        setCols(cols);
        setNumMines(mines);
        score = 0;
        numScans=0;


        Mine gameBoard[][]= new Mine [rows][cols];

        for (int i = 0; i < numMines; i++){
            int randRow = randomRow();
            int randCol = randomCol();
            placeMines(randRow, randCol);
        }
    }

    //Setters
    public void setRows(int rowNum){
        rows = rowNum;
    }

    public void setCols(int colNum){
        cols = colNum;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }

    //Getters
    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public int getNumMines() {
        return numMines;
    }

    //Randomize
    private int randomRow(){
        //TODO implement row randomizing algorithm
        return 0;
    }

    private int randomCol(){
        //TODO implement column randomizing algorithm
        return 0;
    }

    //Gameplay Functions
    private void placeMines(int randRow, int randCol) {
        //TODO: places mines according to coordinates entered into arguments
        Mine mine = gameBoard[randRow][randCol];
        mine.setMine(true);
        mineList.add(mine);
    }
    
    public int scanMines(Mine space){
        //// TODO: 2017-02-22 Implement algorithm to scan mines in the column and row
        int numMines = 0;
        int row = space.getRowCoord();
        int col = space.getColCoord();
//        for (int i = 0; i < boardSizeRows; i++){
//            if (gameBoard[i][col].getMineStatus()){
//                numMines++;
//            }
//        }
        for (int i = 0; i < mineList.size() ; i++){
            if(mineList.get(i).getColCoord()==col){
                numMines++;
            }else if (mineList.get(i).getRowCoord() == row){
                numMines++;
            }
        }
        return numMines;
    }

    public boolean checkSquare(Mine space){
        boolean isAMine = space.getMineStatus();
        return isAMine;
    }

    public void selectMine(Mine space){
        int numMines = scanMines(space);
        if(checkSquare(space)){
            /*
            Code for indicating you found mine
             */
            score++;
        }
        //set the number of number of mines in this square
        space.setNearbyMines(numMines);
        numScans++;
    }

    public int getScore() {
        return score;
    }

    public int getNumScans() {
        return numScans;
    }
}
