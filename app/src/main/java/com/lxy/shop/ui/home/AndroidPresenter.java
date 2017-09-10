package com.lxy.shop.ui.home;

import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.cache.CacheManager;
import com.lxy.shop.common.cache.NetworkCache;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.ui.home.contract.SkilContract;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import io.rx_cache2.Reply;
import retrofit2.Response;

/**
 * Created by lxy
 */

public class AndroidPresenter extends BasePresenter<SkilModel, SkilContract.View> {

    private String TAG = "AndroidPresenter";

    @Inject
    public AndroidPresenter(SkilModel mModel, SkilContract.View mView) {
        super(mModel, mView);
    }

    public void getAndroidData() {

//        mModel.getList("Android", 15, 1)
//                .compose(RxHttpResponse.<SkilBean>handResult())
//                .subscribe(new ProgressObserver<SkilBean>(mContext, mView) {
//                    @Override
//                    public void onNext(SkilBean skilBean) {
//                        mView.showResust(skilBean.results);
//                    }
//                });

        String cacheKey = String.format(ApiService.BASE_URL, 15, 1);
        NetworkCache<SkilBean> networkCache = new NetworkCache<SkilBean>() {
            @Override
            public Observable<SkilBean> get(String key, Class<SkilBean> cls) {

                return mModel.mApiService.getList("Android", 15, 1);
            }
        };

        Observable<SkilBean> observable = CacheManager.getInstance().load(cacheKey, SkilBean.class, networkCache);
        observable.subscribe(new Observer<SkilBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("cache=========onSubscribe:");
            }

            @Override
            public void onNext(SkilBean skilBean) {
                mView.showResust(skilBean.results);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("cache=========err:" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });


//        mModel.getList("Android", 15, 1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response<SkilBean>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Response<SkilBean> bean) {
//                        mView.showResust(bean.body().results);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


    }

    public void getAndroidDataWithCache(String type, int count, int page, boolean loadServer) {

        BaseApplication.getInstance().getRepository()
                .getSkilList(type, count, page, loadServer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Reply<Response<SkilBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Reply<Response<SkilBean>> responseReply) {
                        Logger.d(TAG + "===suc====" + responseReply.getSource());//CLOUD
                        mView.showResust(responseReply.getData().body().results);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(TAG + "===err====" + e.toString());//CLOUD
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
