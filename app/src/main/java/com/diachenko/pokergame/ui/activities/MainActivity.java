package com.diachenko.pokergame.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diachenko.pokergame.R;
import com.diachenko.pokergame.data.ApplicationMode;
import com.diachenko.pokergame.data.InternetConnector;
import com.diachenko.pokergame.data.ModeChecker;
import com.diachenko.pokergame.ui.viewmodels.MainScreenViewModel;
import com.diachenko.pokergame.ui.viewmodels.MainScreenViewModelFactory;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    Executor executor;
    InternetConnector connector;
    ModeChecker modeChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainScreenViewModel viewModel = ViewModelProviders.of(this
                ,new MainScreenViewModelFactory(executor,modeChecker,connector))
                .get(MainScreenViewModel.class);
        viewModel.checkMode(this).observe(this, new Observer<ApplicationMode>() {
            @Override
            public void onChanged(ApplicationMode applicationMode) {
                switch (applicationMode.getMode()){
                    case LINK:
                        WebViewActivity.startActivity(MainActivity.this,
                                applicationMode.getUrl());
                        return;
                    case APPLICATION:
                    case OTHER:
                        GameActivity.startActivity(MainActivity.this);
                }
            }
        });
    }
}
