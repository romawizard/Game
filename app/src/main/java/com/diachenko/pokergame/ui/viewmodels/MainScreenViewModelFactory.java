package com.diachenko.pokergame.ui.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.diachenko.pokergame.data.InternetConnector;
import com.diachenko.pokergame.data.ModeChecker;

import java.util.concurrent.Executor;

public class MainScreenViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Executor executor;
    private ModeChecker modeChecker;
    private InternetConnector connector;

    public MainScreenViewModelFactory(Executor executor, ModeChecker modeChecker, InternetConnector connector) {
        this.executor = executor;
        this.modeChecker = modeChecker;
        this.connector = connector;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainScreenViewModel.class){
            return (T) new MainScreenViewModel(modeChecker, executor,connector);
        }
        return  null;
    }
}
