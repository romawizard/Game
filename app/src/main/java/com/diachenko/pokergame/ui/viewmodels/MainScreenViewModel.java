package com.diachenko.pokergame.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.diachenko.pokergame.utils.appmode.ApplicationMode;
import com.diachenko.pokergame.utils.appmode.ModeChecker;

import java.util.concurrent.Executor;

public class MainScreenViewModel extends ViewModel {

    private ModeChecker modeChecker;
    private Executor executor;

    private MutableLiveData<ApplicationMode> liveData = new MutableLiveData<>();

    public MainScreenViewModel(ModeChecker modeChecker, Executor executor){
        this.modeChecker = modeChecker;
        this.executor = executor;

    }

    public LiveData<ApplicationMode> checkMode(final Context context){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                liveData.postValue(modeChecker.chooseMode(context));
            }
        });
        return liveData;
    }
}
