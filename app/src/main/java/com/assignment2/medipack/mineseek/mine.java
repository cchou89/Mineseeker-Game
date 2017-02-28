package com.assignment2.medipack.mineseek;

/**
 * Created by Medipack on 2017-02-20.
 */

public class Mine {
    private boolean isMine;
    private boolean isUncovered;
    private int nearbyMines;
    private int rowCoord;
    private int colCoord;

    Mine(){
        isMine = false;
        isUncovered = false;
        nearbyMines = 0;
        rowCoord = 0;
        colCoord = 0;
    }

    Mine(boolean mineStatus, int row, int col){
        isMine = mineStatus;
        isUncovered = false;
        nearbyMines = 0;
        rowCoord = row;
        colCoord = col;
    }
    //Getters
    public boolean isMine() {
        return isMine;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public int getNearbyMines() {
        return nearbyMines;
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

    public void setUncovered(boolean uncovered) {
        isUncovered = uncovered;
    }

    public void setNearbyMines(int nearbyMines) {
        try{
            if(nearbyMines < 0){
                throw new IllegalArgumentException();
            }else{
                this.nearbyMines = nearbyMines;
            }
        }catch(IllegalArgumentException e){
            this.nearbyMines = 0;
        }
    }

    public void setColCoord(int colCoord) {
        this.colCoord = colCoord;
    }

    public void setRowCoord(int rowCoord) {
        this.rowCoord = rowCoord;
    }
}


