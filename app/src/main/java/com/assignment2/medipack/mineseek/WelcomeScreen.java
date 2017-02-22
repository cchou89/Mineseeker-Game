package com.assignment2.medipack.mineseek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WelcomeScreen extends AppCompatActivity {

    public static final int MILLIS = 10000;
    private boolean skipped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button skipBtn = getButtonId(R.id.welcome_skip);
        skipBtn.setOnClickListener(getListener());
        Thread welcomeLaunch = getThread();
        welcomeLaunch.start();
    }

    private Button getButtonId(int id) {
        return (Button) findViewById(id);
    }

    @NonNull
    private View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipped = true;
                skipWelcome();
            }
        };
    }

    @NonNull
    private Thread getThread() {
        return new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(MILLIS);
                    if (!skipped) {
                        skipWelcome();
                    } else {
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void skipWelcome() {
        Intent mainMenu = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(mainMenu);
        finish();
    }
}
