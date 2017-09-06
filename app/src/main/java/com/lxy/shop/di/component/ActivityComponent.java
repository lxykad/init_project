package com.lxy.shop.di.component;

import com.lxy.shop.di.module.ActivityModule;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.usercenter.UsercenterActivity;

import dagger.Component;

/**
 * Created by lxy
 */

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(UsercenterActivity activity);
}
