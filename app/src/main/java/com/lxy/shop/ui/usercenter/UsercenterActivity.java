package com.lxy.shop.ui.usercenter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.lxy.shop.R;
import com.lxy.shop.common.User;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.databinding.ActivityUsercenterBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerActivityComponent;
import com.lxy.shop.di.module.ActivityModule;
import com.lxy.shop.ui.usercenter.contract.UserContract;
import com.lxy.shop.ui.usercenter.presenter.UserPresenter;

import io.reactivex.disposables.Disposable;

public class UsercenterActivity extends BaseActivity<UserPresenter> implements UserContract.View{

    private ActivityUsercenterBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_usercenter);
    }

    public void clickTest(View view) {
//        showToast(mPresenter+"");
        mPresenter.getUserInfo();
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void showLoading(Disposable disposable) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showResust(User user) {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError(String msg) {

    }
}
