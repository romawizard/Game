package com.diachenko.pokergame.di.modules;


import com.diachenko.pokergame.di.components.MainActivitySubcomponent;
import com.diachenko.pokergame.ui.activities.MainActivity;

import dagger.Binds;
import dagger.Module;

import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;


@Module(subcomponents = MainActivitySubcomponent.class)
public abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity.class)
    abstract AndroidInjector.Factory<?>
    bindMainActivityInjectorFactory(MainActivitySubcomponent.Builder builder);

}
