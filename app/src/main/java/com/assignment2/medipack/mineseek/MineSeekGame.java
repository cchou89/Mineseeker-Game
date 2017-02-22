package com.assignment2.medipack.mineseek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MineSeekGame extends AppCompatActivity {

    private static final int NUM_ROWS = 2;
    private static final int NUM_COLS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_seek_game);
        populateButtons();
    }

    private void populateButtons() {
        TableLayout table = (TableLayout)findViewById(R.id.gameBoard);
        for(int row = 0; row < NUM_ROWS; row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for(int col = 0; col < NUM_COLS; col++) {
                Button buttonMine = new Button(this);
                buttonMine.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                tableRow.addView(buttonMine);
            }
        }
    }
}
