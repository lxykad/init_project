package com.lxy.shop.ui.home;

import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.home.contract.SkilContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.Reply;
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

    public void getAndroidDataWithCache(String type,int count,int page,boolean loadServer) {

        BaseApplication.getInstance().getRepository()
                .getSkilList(type,count,page,loadServer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Reply<Response<SkilBean>>>() {
                    @Override
                    public void accept(@NonNull Reply<Response<SkilBean>> responseReply) throws Exception {
                        System.out.println("reply2======="+ responseReply.getSource());//CLOUD
                        mView.showResust(responseReply.getData().body().results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });






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




    }

}
