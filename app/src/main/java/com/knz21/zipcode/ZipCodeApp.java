package com.knz21.zipcode;

import android.app.Application;
import com.knz21.zipcode.di.ApplicationComponent;
import com.knz21.zipcode.di.ApplicationModule;
import com.knz21.zipcode.di.DaggerApplicationComponent;

public class ZipCodeApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
