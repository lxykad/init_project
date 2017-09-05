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
import retrofit2.Response;

/**
 * Created by lxy
 * 请求结果预处理 transformer 转换类
 */

public class RxHttpResponse {

    /**
     * @param <T>
     * @return
     */

    public static <T> ObservableTransformer<Response<T>, T> handResult() {

        return new ObservableTransformer<Response<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<Response<T>> upstream) {

                return upstream.flatMap(new Function<Response<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final Response<T> responseBean) throws Exception {

                        if (responseBean.code() == 200) {//请求成功
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                                    emitter.onNext(responseBean.body());
                                    emitter.onComplete();
                                }
                            });

                        } else {//请求失败

                            return Observable.error(new ApiException(responseBean.code(), responseBean.message()));
                        }

                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
