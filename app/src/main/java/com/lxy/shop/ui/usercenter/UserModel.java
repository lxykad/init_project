package com.lxy.shop.ui.usercenter;

import com.lxy.shop.common.User;
import com.lxy.shop.data.api.ApiService;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by lxy on
 */

public class UserModel {
    private ApiService mApiService;

    public UserModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<Response<User>> getUserInfo(){

//         return mApiService.getUserInfo("token");//get
        return mApiService.getUserInfo();
    }
}
