package com.lxy.shop.common.cache;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.lxy.shop.utils.FileUtils;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxy
 */
public class DiskCache implements ICache {

    private String CACHE_PATH;

    public DiskCache() {
        CACHE_PATH = FileUtils.getCacheDir();
    }

    @Override
    public <T extends BaseCacheBean> Observable<T> get(final String key, final Class<T> cls) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                System.out.println("cache=========load from disk:" + key);
                String filename = CACHE_PATH + key;
                String result = FileUtils.readTextFromSDcard(filename);

                if (e.isDisposed()) {
                    return;
                }
                if (TextUtils.isEmpty(result)) {

                    e.onNext(null);
                } else {

                    T t = new Gson().fromJson(result, cls);
                    e.onNext(t);
                }

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    @Override
    public <T extends BaseCacheBean> void put(final String key, final T t) {

        Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                System.out.println("cache=========put to disk:" + key);

                String filename = CACHE_PATH + key;
                String result = t.toString();
                FileUtils.saveText2Sdcard(filename, result);
                if (!e.isDisposed()) {

                    e.onNext(t);
                    e.onComplete();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
