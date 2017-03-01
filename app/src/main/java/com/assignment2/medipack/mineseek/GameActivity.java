package com.assignment2.medipack.mineseek;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "MineSeek";
    Game game = Game.getInstance();
    int rows = game.rowSettings;
    int cols = game.colSettings;
//    Vibrator vibrate = (Vibrator) this.Context.getSystemService(Context.VIBRATOR_SERVICE);
    Button buttonGrid[][] = new Button[rows][cols];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_seek_game);
        //Pull data from Game class
        MineSeekBoard board = game.newGame;
        //Populate the grid with buttons
        populateButtons(rows, cols, board);
        //Set up initial mines found
        updateMinesScore(board);
        //Set up initial scans found
        updateNumberOfScans(board);
        //Set up total number of games;
        TextView gameNum = (TextView) findViewById(R.id.gameNum);
        int num = game.getNumGamesPlayed();
        String gameCount = String.format(getString(R.string.game_d), num);
        gameNum.setText(gameCount);
        checkgameMode();
        setHighScore();
                
    }

    private void checkgameMode() {
    }

    private void setHighScore() {
//        TextView highScore = (TextView);
        
    }


    private void populateButtons(final int rows, final int cols, final MineSeekBoard board) {
        TableLayout table = (TableLayout)findViewById(R.id.gameBoard);
        for(int row = 0; row < rows; row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);
            for(int col = 0; col < cols; col++) {
                Button buttonMine = new Button(this);
                buttonMine.setLayoutParams(new TableRow.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT,
                        1.0f));
                //Make text not clip on small buttons
                buttonMine.setPadding(0, 0, 0, 0);
                final int finalRow = row;
                final int finalCol = col;
                buttonMine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mine space = board.getGameBoard()[finalRow][finalCol];
                        Button button = buttonGrid[finalRow][finalCol];
                        board.selectSpace(space);
                        String msg = String.format("%d", space.getNearbyMines());
                        //Find the mine
                        if(board.checkSquare(space) && !space.isUncovered()){
                            //Indicate it's a mine
                            button.setBackgroundResource(R.drawable.eviljelly);
//                            vibrate.vibrate(250);
                            Log.i(TAG, "Mine");
                            if (!space.isUncovered()){
                                space.setUncovered(true);
                                Log.i(TAG, msg);
                                //update number of mines
                                updateMinesScore(board);
                                updateSurroundings(rows, board, finalCol, cols, finalRow);
                            }
                        //Scan the space
                        }else {
                            button.setText(msg);
                            space.setUncovered(true);
                            updateNumberOfScans(board);
//                            vibrate.vibrate(1000);
                            Log.i(TAG, msg);
                        }

                        //Check Game Over conditions
                        if(board.getMinesFound() == board.getNumMines()){
                            gameOverDialog();
                        }
                    }
                });
                tableRow.addView(buttonMine);
                buttonMine.setBackgroundResource(R.drawable.bush);
                buttonGrid[row][col]= buttonMine;
                Mine spaceLoc = board.getGameBoard()[row][col];
                spaceLoc.setNearbyMines(board.scanMines(spaceLoc));
            }
        }
    }

    public void updateSurroundings(int rows, MineSeekBoard board, int finalCol, int cols, int finalRow) {
        //Update surrounding spaces for nearby Mines
        //Vertically
        for(int i = 0; i < rows; i++){
            Mine target = board.getGameBoard()[i][finalCol];
            Button vertBtn = buttonGrid[i][finalCol];
            if (target.isUncovered() && !target.isMine()){
                int nearbyMines = target.getNearbyMines();
                String newMsg = String.format("%d", nearbyMines);
                vertBtn.setText(newMsg);
            }
        }
        //Horizontally
        for(int i = 0; i < cols; i++){
            Mine target = board.getGameBoard()[finalRow][i];
            Button horizBtn = buttonGrid[finalRow][i];
            if (target.isUncovered() && !target.isMine()){
                int nearbyMines = target.getNearbyMines();
                String newMsg = String.format("%d", nearbyMines);
                horizBtn.setText(newMsg);
            }
        }
    }

    private void updateNumberOfScans(MineSeekBoard board) {
        TextView numScans = (TextView) findViewById(R.id.numScans);
        String scanText = String.format(getString(R.string.scans_used), board.getNumScans());
        numScans.setText(scanText);
    }

    private void updateMinesScore(MineSeekBoard board) {
        TextView mines = (TextView) findViewById(R.id.minesFound);
        String mineText = String.format(getString(R.string.mines_found), board.getMinesFound(), board.getNumMines());
        mines.setText(mineText);
    }

    private void gameOverDialog() {
        FragmentManager manager = getSupportFragmentManager();
        messageFragment dialog = new messageFragment();
        dialog.show(manager, "MessageDialog");
        Log.i("TAG",  "Just showed the dialog");
    }
}
