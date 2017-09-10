package com.lxy.shop.common.base;

import android.app.Application;

import com.lxy.shop.R;
import com.lxy.shop.common.http.Repository;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerAppComponent;
import com.lxy.shop.di.module.AppModule;
import com.lxy.shop.di.module.HttpModule;
import com.lxy.shop.utils.Utils;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

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
        Utils.init(this);

        mRepository = Repository.init(getCacheDir());
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {

               return getResources().getBoolean(R.bool.DEBUG);
            }
        });

    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }

}
