package com.lxy.shop.common.http;

import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.data.api.CacheProviders;
import com.lxy.shop.ui.home.SkilBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxy
 */

public class Repository {

    private final CacheProviders cacheProviders;
    private final ApiService mApiService;

    public Repository(File cacheDir) {
        cacheProviders = new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker())
                .using(CacheProviders.class);
        mApiService = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }

    public static Repository init(File cacheDir) {
        return new Repository(cacheDir);
    }

    public Observable<Reply<Response<SkilBean>>> getSkilList(int page, final boolean isCache) {

        return cacheProviders.getSkilList(mApiService.getSkilList("Android", 15, page),new DynamicKey(page),new EvictDynamicKey(isCache));
    }


}
