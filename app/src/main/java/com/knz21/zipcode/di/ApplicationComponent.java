package com.knz21.zipcode.di;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    ActivityComponent newActivityComponent(ActivityModule module);
}