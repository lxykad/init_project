package com.lxy.shop.di.module;

import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.home.SkilModel;
import com.lxy.shop.ui.home.contract.SkilContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxy
 */

@Module
public class FragmentModule {

    private SkilContract.View mView;

    public FragmentModule(SkilContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public SkilContract.View provideView(){

        return mView;
    }

    @Provides
    public SkilModel provideModel(ApiService apiService){

        return new SkilModel(apiService);
    }

}
