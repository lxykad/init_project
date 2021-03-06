package com.lxy.shop.ui.home;

import android.Manifest;
import android.app.Activity;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.home.contract.SkilContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by lxy on 2017/5/11.
 */

public class AndroidPresenter extends BasePresenter<SkilModel, SkilContract.View> {

    @Inject
    public AndroidPresenter(SkilModel mModel, SkilContract.View mView) {
        super(mModel, mView);
    }
    public void getAndroidData() {

        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {

                            mModel.getList("Android", 1000, 1)
                                    .compose(RxHttpResponse.<List<SkilBean>>handResult())
                                    .subscribe(new ProgressObserver<List<SkilBean>>(mContext, mView) {
                                        @Override
                                        public void onNext(List<SkilBean> list) {
                                            mView.showResust(list);
                                        }
                                    });

                        } else {
                            System.out.println("AndroidPresenter=======拒绝");
                        }
                    }
                });

    }

}
