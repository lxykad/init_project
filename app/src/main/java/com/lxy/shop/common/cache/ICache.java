package com.lxy.shop.common.cache;

import io.reactivex.Observable;

/**
 * 缓存接口，限制泛型必须继承BaseCacheBean
 * Created by lxy
 */

public interface ICache {

    <T extends BaseCacheBean> Observable<T> get(String key, Class<T> cls);

    <T extends BaseCacheBean> void put(String key, T t);
}
