package com.knz21.zipcode.di;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Named;

@Module
public class ActivityModule {

    @Provides
    @ActivityScope
    @Named("executeScheduler")
    public Scheduler provideExecutionScheduler() {
        return Schedulers.newThread();
    }

    @Provides
    @ActivityScope
    @Named("postScheduler")
    public Scheduler providePostScheduler() {
        return AndroidSchedulers.mainThread();
    }
}