package com.diachenko.pokergame.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diachenko.pokergame.R;

public class GameActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context,GameActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
