package com.knz21.zipcode.di;

import com.knz21.zipcode.MainActivity;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}
