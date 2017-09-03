package com.lxy.shop.di.component;

import com.lxy.shop.di.module.FragmentModule;
import com.lxy.shop.di.scope.ActivityScope;
import com.lxy.shop.ui.recommend.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by lxy on 2017/5/11.
 */
@ActivityScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    //void inject(MainActivity activity);
    void inject(RecommendFragment fragment);
}
