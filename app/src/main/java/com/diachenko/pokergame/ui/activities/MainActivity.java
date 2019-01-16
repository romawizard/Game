package com.diachenko.pokergame.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.diachenko.pokergame.R;
import com.diachenko.pokergame.utils.appmode.ApplicationMode;
import com.diachenko.pokergame.ui.viewmodels.MainScreenViewModel;
import com.snatik.matches.MatchesGameActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        MainScreenViewModel viewModel = ViewModelProviders.of(this,viewModelProvider)
                .get(MainScreenViewModel.class);
        viewModel.checkMode(this).observe(this, new Observer<ApplicationMode>() {
            @Override
            public void onChanged(ApplicationMode applicationMode) {
                switch (applicationMode.getMode()){
                    case LINK:
                        WebViewActivity.startActivity(MainActivity.this,
                                applicationMode.getUrl());
                        finish();
                        return;
                    case APPLICATION:
                    case OTHER:
                        MatchesGameActivity.startActivity(MainActivity.this);
                        finish();
                }
            }
        });
    }
}
