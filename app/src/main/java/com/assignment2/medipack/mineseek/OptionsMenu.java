package com.assignment2.medipack.mineseek;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionsMenu extends AppCompatActivity {

    public static final int MED_MINES = 10;
    public static final int LRG_MINES = 15;
    public static final int XLRG_MINES = 20;
    static int DEFAULT_ROW = 4;
    static int DEFAULT_COL = 6;
    static int DEFAULT_MINES = 6;
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
        //Get Board Size Radio Button ids
        final RadioGroup sizes = (RadioGroup) findViewById(R.id.boardSize);
        RadioButton smallBoard = (RadioButton) findViewById(R.id.boardSmall);
        RadioButton medBoard = (RadioButton) findViewById(R.id.boardMedium);
        RadioButton LrgBoard = (RadioButton) findViewById(R.id.boardLarge);
        //set RadioGroup Listener
        sizes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup sizes, int checkedId) {
                boardSizeListener(sizes, checkedId);
            }
        });
        //Get Board Size Radio Button ids
        final RadioGroup numbers = (RadioGroup) findViewById(R.id.mineNumbers);
        RadioButton mineSmall = (RadioButton) findViewById(R.id.sixMines);
        RadioButton mineMed = (RadioButton) findViewById(R.id.tenMines);
        RadioButton mineLrg = (RadioButton) findViewById(R.id.fifteenMines);
        RadioButton mineXlrg = (RadioButton) findViewById(R.id.twentyMines);
        //set RadioGroup Listener
        numbers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup numbers, int checkedId) {
                mineSizeListener(numbers, checkedId);
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
                game.mineSettings = mines;
                game.startGame();

                SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Check boardSize
                int sizeID = sizes.getCheckedRadioButtonId();
                int mineNumID = numbers.getCheckedRadioButtonId();
                editor.putInt("sizeID", sizeID);
                editor.putInt("mineNumID", mineNumID);
                editor.apply();
                finish();
            }
        });
        loadSettings(sizes, numbers);
    }

    private void loadSettings(RadioGroup sizes, RadioGroup numbers) {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        if(sharedPreferences!= null){
            int numMineID = sharedPreferences.getInt("mineNumID", R.id.sixMines);
            int boardSizeID = sharedPreferences.getInt("sizeID", R.id.boardSmall);
            sizes.check(boardSizeID);
            numbers.check(numMineID);
        }else{
            sizes.check(R.id.boardSmall);
            numbers.check(R.id.sixMines);
        }
    }

    public void boardSizeListener(RadioGroup sizes, int checkedId) {
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

    public void mineSizeListener(RadioGroup numbers, int checkedId) {
        View radioButton = numbers.findViewById(checkedId);
        int index = numbers.indexOfChild(radioButton);

        switch (index) {
            case 0: // 6 mines
                mines = DEFAULT_MINES;
                break;
            case 1: // 10 mines
                mines = MED_MINES;
                break;

            case 2: // 15 mines
                mines = LRG_MINES;
                break;

            case 3: //20 mines
                mines = XLRG_MINES;
                break;
        }
    }
}
