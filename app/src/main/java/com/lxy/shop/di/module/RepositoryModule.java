package com.lxy.shop.di.module;

import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.http.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxy
 */

@Module
public class RepositoryModule {

    @Provides
    public Repository provideRepository(){
      return  Repository.init(BaseApplication.getInstance().getCacheDir());
    }

}
