package com.assignment2.medipack.mineseek;

/**
 * Created by Medipack on 2017-02-20.
 */

public class Mine {
    private boolean isMine;
    private int nearbyMines;
    private int rowCoord;
    private int colCoord;

    Mine(){
        isMine = false;
        nearbyMines = 0;
        rowCoord = 0;
        colCoord = 0;
    }

    Mine(boolean mineStatus, int row, int col){
        isMine = mineStatus;
        nearbyMines = 0;
        rowCoord = row;
        colCoord = col;
    }
    //Getters
    public int getNearbyMines() {
        return nearbyMines;
    }

    public boolean getMineStatus() {
        return isMine;
    }

    public int getRowCoord() {
        return rowCoord;
    }

    public int getColCoord() {
        return colCoord;
    }

    //Setters
    public void setMine(boolean mine) {
        isMine = mine;
    }

    public void setNearbyMines(int nearbyMines) {
        this.nearbyMines = nearbyMines;
    }

    public void setColCoord(int colCoord) {
        this.colCoord = colCoord;
    }

    public void setRowCoord(int rowCoord) {
        this.rowCoord = rowCoord;
    }
}


