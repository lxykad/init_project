package com.lxy.shop.common.rx;

import com.lxy.shop.common.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lxy on 2017/5/20.
 * 请求结果预处理 transformer 转换类
 */

public class RxHttpResponse {

    /**
     * rxjava2 写法
     *
     * @param <T>
     * @return
     */

    public static <T> ObservableTransformer<BaseBean<T>, T> handResult() {

        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {

                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> tBaseBean) throws Exception {

                        if (tBaseBean.error.equals("false")) {//请求成功

                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                                    emitter.onNext(tBaseBean.results);
                                    emitter.onComplete();
                                }
                            });

                        } else {//请求失败

                            return Observable.error(new ApiException(-1, "server error"));
                        }

                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
