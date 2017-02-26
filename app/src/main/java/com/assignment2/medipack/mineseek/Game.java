package com.assignment2.medipack.mineseek;

/**
 * Created by Medipack on 2017-02-24.
 */

public class Game {
    private static Game instance = new Game();
    public int rowSettings;
    public int colSettings;
    public int mineSettings;
    public int numGamesPlayed;
    public MineSeekBoard newGame;

    public static Game getInstance(){
        return instance;
    }

    Game(){
        numGamesPlayed = 0;
        rowSettings = 4;
        colSettings = 6;
        mineSettings = 9;
        startGame(rowSettings, colSettings, mineSettings);
        numGamesPlayed++;
    }

    public void startGame(int rows, int cols, int numMines){
        newGame = new MineSeekBoard(rows, cols, numMines);
    }

    public void resetGameCount(){
        numGamesPlayed = 0;
    }
}
