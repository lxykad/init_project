package com.lxy.shop.di.component;

import com.lxy.shop.di.module.RepositoryModule;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.TestActivity;

import dagger.Component;

/**
 * Created by lxy
 */

@ActivityScope
@Component(modules = RepositoryModule.class, dependencies = AppComponent.class)
public interface RepositoryComponent {
    void injectActivity(TestActivity activity);
//    void injectFragment(BaseFragment fragment);
}
