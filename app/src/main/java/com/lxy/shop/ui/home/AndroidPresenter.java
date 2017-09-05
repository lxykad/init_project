package com.lxy.shop.ui.home;

import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.home.contract.SkilContract;

import javax.inject.Inject;

/**
 * Created by lxy
 */

public class AndroidPresenter extends BasePresenter<SkilModel, SkilContract.View> {

    @Inject
    public AndroidPresenter(SkilModel mModel, SkilContract.View mView) {
        super(mModel, mView);
    }

    public void getAndroidData() {

        mModel.getList("Android", 2, 1)
                .compose(RxHttpResponse.<SkilBean>handResult())
                .subscribe(new ProgressObserver<SkilBean>(mContext,mView) {
                    @Override
                    public void onNext(SkilBean skilBean) {
                        mView.showResust(skilBean.results);
                    }
                });

    }

}
