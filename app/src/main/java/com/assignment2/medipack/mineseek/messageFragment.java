package com.assignment2.medipack.mineseek;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Calvin on 2017-02-27.
 */

public class messageFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //View to show
        View v = LayoutInflater
                .from(getActivity())
                .inflate(R.layout.congratulations_layout, null);
        //Button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TAG","Done");
                getActivity().finish();
            }
        };
        //built the alert dialog
        String gameOver = getString(R.string.game_over);
        return new AlertDialog.Builder(getActivity())
                .setTitle(gameOver)
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
