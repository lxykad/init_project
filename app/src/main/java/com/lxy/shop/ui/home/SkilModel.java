package com.lxy.shop.ui.home;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.data.api.ApiService;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;


/**
 * Created by lxy
 */

public class SkilModel {

    private ApiService mApiService;


    public SkilModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<Response<SkilBean>> getList(String type, int page, int perPage) {

        return mApiService.getSkilList(type, page, perPage);
    }
}
