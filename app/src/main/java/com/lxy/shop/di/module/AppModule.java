package com.lxy.shop.di.module;

import com.lxy.shop.common.base.BaseApplication;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by lxy
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
