package com.lxy.shop.ui.home;

import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.data.api.CacheProviders;
import com.lxy.shop.ui.home.contract.SkilContract;
import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import retrofit2.Response;

/**
 * Created by lxy
 */

public class AndroidPresenter extends BasePresenter<SkilModel, SkilContract.View> {

    @Inject
    public AndroidPresenter(SkilModel mModel, SkilContract.View mView) {
        super(mModel, mView);
    }

    public void getAndroidData() {

        mModel.getList("Android", 15, 1)
                .compose(RxHttpResponse.<SkilBean>handResult())
                .subscribe(new ProgressObserver<SkilBean>(mContext,mView) {
                    @Override
                    public void onNext(SkilBean skilBean) {
                        mView.showResust(skilBean.results);
                    }
                });

    }

    public void getAndroidDataWithCache() {

//        CacheProviders cacheProviders = new RxCache.Builder()
//                .persistence(mContext.getCacheDir(), new GsonSpeaker())
//                .using(CacheProviders.class);
//        cacheProviders.getSkilList(mModel.getList("Android", 15, 1),new DynamicKey(1),new EvictDynamicKey(false))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Reply<Response<SkilBean>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Reply<Response<SkilBean>> responseReply) {
//                        System.out.println("reply2======="+ responseReply.getSource());//CLOUD
//                        mView.showResust(responseReply.getData().body().results);
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


        BaseApplication.getInstance().getRepository()
                .getSkilList(1,false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Reply<Response<SkilBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Reply<Response<SkilBean>> responseReply) {
                        System.out.println("reply2======="+ responseReply.getSource());//CLOUD
                        mView.showResust(responseReply.getData().body().results);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
