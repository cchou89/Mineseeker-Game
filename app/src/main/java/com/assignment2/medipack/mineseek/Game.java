package com.assignment2.medipack.mineseek;

import android.content.SharedPreferences;

/**
 * Game class
 */

public class Game {
    private static Game instance = new Game();
    public int rowSettings;
    public int colSettings;
    public int mineSettings;
    public int numGamesPlayed;
    public MineSeekBoard newGame;
    public SharedPreferences sharedSettings;
    public SharedPreferences gameCount;

    public static Game getInstance(){
        return instance;
    }

    Game(){
        numGamesPlayed = 0;
        rowSettings = 4;
        colSettings = 6;
        mineSettings = 9;
        newGame = new MineSeekBoard(rowSettings, colSettings, mineSettings);
        numGamesPlayed++;
    }

    public void startGame(){
        newGame = new MineSeekBoard(rowSettings, colSettings, mineSettings);
        numGamesPlayed++;
    }

    public void resetGameCount(){
        numGamesPlayed = 0;
    }

    public int getNumGamesPlayed() {
        return numGamesPlayed;
    }
}
