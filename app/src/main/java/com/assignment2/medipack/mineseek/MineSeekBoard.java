package com.assignment2.medipack.mineseek;

/**
 * Created by Medipack on 2017-02-20.
 */

public class MineSeekBoard extends Mine{
    private int rows;
    private int cols;
    private int numMines;
    private int score;
    private int numScans;
    Mine mineList[];
    Mine gameBoard[][];

    //Default Constructor
    MineSeekBoard(){
        rows = 4;
        cols = 6;
        numMines = 9;
        score = 0;
        numScans=0;
        //Handy dandy list of mine locations
        mineList = new Mine[numMines];
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
    //Parameterized
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
    }
    
    public int scanMines(){
        //// TODO: 2017-02-22 Implement algorithm to scan mines in the column and row 
        return 0;
    }

    public boolean checkSquare(){
        boolean isAMine = false;
        return isAMine;
    }

    public void selectMine(){

    }

}
