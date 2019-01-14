package com.diachenko.pokergame.di.modules;

import android.arch.lifecycle.ViewModelProvider;

import com.diachenko.pokergame.data.network.BaseNetworkRequest;
import com.diachenko.pokergame.utils.appmode.ModeCheckerImpl;
import com.diachenko.pokergame.data.network.NetWorkConnectionImpl;
import com.diachenko.pokergame.data.network.NetworkConnection;
import com.diachenko.pokergame.data.network.NetworkRequest;
import com.diachenko.pokergame.utils.appmode.ModeChecker;
import com.diachenko.pokergame.di.ActivityScope;
import com.diachenko.pokergame.ui.viewmodels.MainScreenViewModel;
import com.diachenko.pokergame.ui.viewmodels.ViewModelProviderFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    @ActivityScope
    @Provides
    Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @ActivityScope
    MainScreenViewModel provideMainScreenViewModel(Executor executor,ModeChecker modeChecker){
        return new MainScreenViewModel(modeChecker,executor);
    }

    @Provides
    @ActivityScope
    ViewModelProvider.Factory providesMainViewModelProvider(MainScreenViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    @ActivityScope
    ModeChecker provideModeChecker(NetworkConnection connection,NetworkRequest connector ){
        return new ModeCheckerImpl(connection,connector);
    }

    @Provides
    @ActivityScope
    NetworkConnection provideNetworkConnection( ){
        return new NetWorkConnectionImpl();
    }

    @Provides
    @ActivityScope
    NetworkRequest provideUrlConnector( ){
        return new BaseNetworkRequest();
    }
}

