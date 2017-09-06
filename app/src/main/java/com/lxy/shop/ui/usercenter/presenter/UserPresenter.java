package com.lxy.shop.ui.usercenter.presenter;

import com.lxy.shop.common.User;
import com.lxy.shop.common.base.BasePresenter;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.ui.usercenter.UserModel;
import com.lxy.shop.ui.usercenter.contract.UserContract;

import javax.inject.Inject;

/**
 * Created by lxy
 */

public class UserPresenter extends BasePresenter<UserModel,UserContract.View> {

    @Inject
    public UserPresenter(UserModel mModel, UserContract.View mView) {
        super(mModel, mView);
    }

    public void getUserInfo(){
        mModel.getUserInfo()
                .compose(RxHttpResponse.<User>handResult())
                .subscribe(new ProgressObserver<User>(mContext,mView) {
                    @Override
                    public void onNext(User user) {

                    }
                });
    }

}
