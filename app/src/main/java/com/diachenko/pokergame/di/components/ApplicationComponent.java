package com.diachenko.pokergame.di.components;

import com.diachenko.pokergame.PokerApplication;
import com.diachenko.pokergame.di.modules.ActivityBuilderModule;
import com.diachenko.pokergame.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityBuilderModule.class,
        ApplicationModule.class})
public interface ApplicationComponent {

    void inject(PokerApplication application);


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(PokerApplication application);

        ApplicationComponent build();
    }
}
