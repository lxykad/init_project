package com.lxy.shop.ui.recommend;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.common.rx.ResponseBean;
import com.lxy.shop.data.api.ApiService;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by lxy on 2017/5/11.
 */

public class SkilModel {

    private ApiService mApiService;


    public SkilModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<BaseBean<List<SkilBean>>> getList(String type, int page, int perPage) {

        return mApiService.getSkilList(type, page, perPage);
    }
}
