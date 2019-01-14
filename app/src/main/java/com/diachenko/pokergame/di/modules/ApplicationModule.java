package com.diachenko.pokergame.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module()
public class ApplicationModule {
    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application;
    }
}
