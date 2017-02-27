package com.assignment2.medipack.mineseek;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionsMenu extends AppCompatActivity {

    static int DEFAULT_ROW = 4;
    static int DEFAULT_COL = 6;
    static int DEFAULT_MINES = 9;
    static int ROW_MED = 5;
    static int COL_MED = 10;
    static int ROW_LRG = 6;
    static int COL_LRG = 15;
    int row;
    int col;
    int mines;
    Game game = Game.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        final RadioGroup sizes = (RadioGroup) findViewById(R.id.boardSize);
        sizes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup sizes, int checkedId) {
                View radioButton = sizes.findViewById(checkedId);
                int index = sizes.indexOfChild(radioButton);

                switch (index) {
                    case 0: // small
                        row = DEFAULT_ROW;
                        col = DEFAULT_COL;
                        break;
                    case 1: // medium
                        row = ROW_MED;
                        col = COL_MED;
                        break;

                    case 2: // large
                        row = ROW_LRG;
                        col = COL_LRG;
                        break;
                }
            }
        });

        Button cancelBtn = (Button) findViewById(R.id.options_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button okBtn = (Button) findViewById(R.id.options_confirm);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.rowSettings = row;
                game.colSettings = col;
                game.startGame();
                finish();
            }
        });
    }
}
