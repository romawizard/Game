package com.diachenko.pokergame.di.components;

import com.diachenko.pokergame.di.ActivityScope;
import com.diachenko.pokergame.di.modules.MainActivityModule;
import com.diachenko.pokergame.ui.activities.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
