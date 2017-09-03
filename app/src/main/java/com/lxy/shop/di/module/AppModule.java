package com.lxy.shop.di.module;

import com.lxy.shop.common.base.BaseApplication;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by lxy on 2017/5/11.
 */

@Module
public class AppModule {

    private BaseApplication mApplication;

    public AppModule(BaseApplication application) {
        mApplication = application;
    }

    @Provides
    public BaseApplication getBaseApplication(Retrofit retrofit){

        return mApplication;
    }


}
