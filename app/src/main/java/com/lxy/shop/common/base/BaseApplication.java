package com.lxy.shop.common.base;

import android.app.Application;

import com.lxy.shop.common.http.Repository;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerAppComponent;
import com.lxy.shop.di.module.AppModule;
import com.lxy.shop.di.module.HttpModule;
import com.orhanobut.hawk.Hawk;

import java.io.File;

/**
 * Created by lxy
 */

public class BaseApplication extends Application {

    private static BaseApplication sInstance;
    private AppComponent mAppComponent;
    private Repository mRepository;

    public static BaseApplication getInstance() {

        return sInstance;
    }
    public Repository getRepository() {

        return mRepository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).httpModule(new HttpModule()).build();
        Hawk.init(this).build();

        mRepository = Repository.init(getCacheDir());

    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }

}
