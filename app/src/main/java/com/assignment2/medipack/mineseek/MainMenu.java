package com.assignment2.medipack.mineseek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainMenu extends AppCompatActivity {
    private static final String TAG = "MineSeek";
    //get Game instance
    Game game = Game.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //Linear Layout
        LinearLayout mainMenuBtns = (LinearLayout) findViewById(R.id.main_menu_buttons);

        //Buttons
        Button playGameBtn = getButtonId(R.id.main_play_game);
        Button optionsBtn = getButtonId(R.id.main_options);
        Button helpBtn = getButtonId(R.id.main_help);


        //Assigning Button functions
        playGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent(GameActivity.class);
                game.gameCount = getSharedPreferences("Number Games", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = game.gameCount.edit();
                editor.putInt("Number Games", game.getNumGamesPlayed());
                editor.apply();
                game.startGame();
                startActivity(intent);
            }
        });
        optionsBtn.setOnClickListener(getListener(OptionsMenu.class));
        helpBtn.setOnClickListener(getListener(HelpMenu.class));
        loadCount();
    }

    private void loadCount() {
        SharedPreferences numGamesShared = getSharedPreferences("Number Games", MODE_PRIVATE);
        game.numGamesPlayed = numGamesShared.getInt("Number Games", 1);
    }

    @NonNull
    private View.OnClickListener getListener(final Class cls) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent(cls);
                startActivity(intent);
            }
        };
    }

    @NonNull
    private Intent getIntent(Class cls) {
        return new Intent(getApplicationContext(), cls);
    }

    private Button getButtonId(int id) {
        return (Button) findViewById(id);
    }
}
