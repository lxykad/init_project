package com.lxy.shop.di.module;

import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.home.SkilModel;
import com.lxy.shop.ui.home.contract.SkilContract;
import com.lxy.shop.ui.usercenter.UserModel;
import com.lxy.shop.ui.usercenter.contract.UserContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxy
 */

@Module
public class ActivityModule {

    private UserContract.View mView;

    public ActivityModule(UserContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public UserContract.View provideView(){

        return mView;
    }

    @Provides
    public UserModel provideModel(ApiService apiService){

        return new UserModel(apiService);
    }
}
