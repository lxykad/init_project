package com.lxy.shop.common.cache;


import io.reactivex.Observable;

/**
 * Created by lxy
 */
public abstract class NetworkCache<T extends BaseCacheBean> {

    public abstract Observable<T> get(String key, final Class<T> cls);
}