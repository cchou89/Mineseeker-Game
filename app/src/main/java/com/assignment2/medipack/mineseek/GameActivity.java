package com.assignment2.medipack.mineseek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "MineSeek";
    Game game = Game.getInstance();
    int rows = game.rowSettings;
    int cols = game.colSettings;
    Button buttonGrid[][] = new Button[rows][cols];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_seek_game);
        //Pull data from Game class
        MineSeekBoard board = game.newGame;
        //Populate the grid with buttons
        populateButtons(rows, cols, board);
        TextView score = (TextView) findViewById(R.id.score);
        TextView scans = (TextView) findViewById(R.id.numScans);
    }

    private void populateButtons(int rows, int cols, final MineSeekBoard board) {
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
                        Log.i(TAG, msg);
                        if(space.getMineStatus()){
                            button.setText("Mine");
                        }   else {
                            button.setText(msg);
                        }
                    }
                });
                tableRow.addView(buttonMine);
                buttonMine.setBackgroundResource(R.drawable.bush);
                buttonGrid[row][col]= buttonMine;
            }
        }
    }
}
