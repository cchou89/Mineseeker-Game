package com.assignment2.medipack.mineseek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private MineSeekBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //Buttons
        Button playGameBtn = getButtonId(R.id.main_play_game);
        Button optionsBtn = getButtonId(R.id.main_options);
        Button helpBtn = getButtonId(R.id.main_help);
        //Assigning Button functions
        playGameBtn.setOnClickListener(getListener(MineSeekGame.class));
        optionsBtn.setOnClickListener(getListener(OptionsMenu.class));
        helpBtn.setOnClickListener(getListener(HelpMenu.class));
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
