package com.lxy.shop.common.cache;

import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Created by lxy
 */
public class MemoryCache implements ICache {

    private LruCache<String, String> mCache;

    public MemoryCache() {
        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M
        mCache = new LruCache<String, String>(mCacheSize) {
            @Override
            protected int sizeOf(String key, String value) {
                try {
                    return value.getBytes("UTF-8").length;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return value.getBytes().length;
                }
            }
        };
    }

    @Override
    public <T extends BaseCacheBean> Observable<T> get(final String key, final Class<T> cls) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                System.out.println("cache=========load from memory:" + key);
                String result = mCache.get(key);

                if (e.isDisposed()) {
                    return;
                }
                if (TextUtils.isEmpty(result)) {

                    e.onNext(null);
                } else {

                    T t = new Gson().fromJson(result, cls);
                    e.onNext(t);
                }
                e.onComplete();
            }
        });
    }

    @Override
    public <T extends BaseCacheBean> void put(String key, T t) {

        if (null != t) {
            System.out.println("cache=========save to memory:" + key);

            mCache.put(key, t.toString());
        }
    }
}