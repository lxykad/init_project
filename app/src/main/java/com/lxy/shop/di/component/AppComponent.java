package com.lxy.shop.di.component;

import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.rx.RxErrorHandler;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.di.module.AppModule;
import com.lxy.shop.di.module.HttpModule;
import com.lxy.shop.di.module.RepositoryModule;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by lxy
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    BaseApplication getBaseApplication();
    ApiService getApiService();
    RxErrorHandler getErrorHander();

}
