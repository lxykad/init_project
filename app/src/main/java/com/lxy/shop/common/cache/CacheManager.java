package com.lxy.shop.common.cache;

import android.util.Log;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;


/**
 * Created by lxy
 */
public class CacheManager {

    private ICache mMemoryCache, mDiskCache;

    private CacheManager() {

        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache();
    }

    public static final CacheManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public <T extends BaseCacheBean> Observable<T> load(String key, Class<T> cls, NetworkCache<T> networkCache) {

//        Observable observable = Observable.concat(
//                loadFromMemory(key, cls),
//                loadFromDisk(key, cls),
//                loadFromNetwork(key, cls, networkCache))
//                .first(new Func1<T, Boolean>() {
//                    @Override
//                    public Boolean call(T t) {
//
//                        String result = t == null ? "not exist" :
//                                t.isExpire() ? "exist but expired" : "exist and not expired";
//                        Log.v("cache", "result: " + result);
//
//                        return t != null && !t.isExpire();
//                    }
//                });
//        return observable;

        return Observable.concat(
                loadFromMemory(key, cls),
                loadFromDisk(key, cls),
                loadFromNetwork(key, cls, networkCache)
        ).filter(new Predicate<T>() {
            @Override
            public boolean test(@NonNull T t) throws Exception {

                String result = t == null ? "not exist" :
                        t.isExpire() ? "exist but expired" : "exist and not expired";
                Log.v("cache", "result: " + result);

                return t != null && !t.isExpire();
            }
        }).firstElement().toObservable().publish();

    }

    private <T extends BaseCacheBean> Observable<T> loadFromMemory(String key, Class<T> cls) {

        return mMemoryCache
                .get(key, cls);
    }

    private <T extends BaseCacheBean> Observable<T> loadFromDisk(final String key, Class<T> cls) {

        return mDiskCache.get(key, cls)
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        if (null != t) {

                            mMemoryCache.put(key, t);
                        }
                    }
                });
    }

    private <T extends BaseCacheBean> Observable<T> loadFromNetwork(final String key, Class<T> cls
            , NetworkCache<T> networkCache) {

        return networkCache.get(key, cls)
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        if (null != t) {
                            mDiskCache.put(key, t);
                            mMemoryCache.put(key, t);
                        }
                    }
                });
    }

    private <T extends BaseCacheBean> ObservableTransformer<T, T> log(final String msg) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return Observable.switchOnNext(new ObservableSource<ObservableSource<? extends T>>() {
                    @Override
                    public void subscribe(Observer<? super ObservableSource<? extends T>> observer) {
                        Logger.d("cache", msg);
                    }
                });

            }
        };
    }

    private static final class LazyHolder {
        public static final CacheManager INSTANCE = new CacheManager();
    }
}