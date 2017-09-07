package com.lxy.shop.ui.usercenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lxy.shop.BuildConfig;
import com.lxy.shop.R;
import com.lxy.shop.common.User;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.databinding.ActivityUsercenterBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerActivityComponent;
import com.lxy.shop.di.module.ActivityModule;
import com.lxy.shop.ui.usercenter.contract.UserContract;
import com.lxy.shop.ui.usercenter.presenter.UserPresenter;

public class UsercenterActivity extends BaseActivity<UserPresenter> implements UserContract.View {

    private ActivityUsercenterBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // mPresenter.getUserInfo();
    }

    @Override
    protected void onEmptyClick(View view) {
        mPresenter.getUserInfo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_usercenter;
    }

    @Override
    public void initChildBinding() {
        mBinding = (ActivityUsercenterBinding) mChildBinding;
    }

    public void clickTest(View view) {
//        mPresenter.getUserInfo();
        showToast(BuildConfig.DEBUG + "");
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
    public void showResust(User user) {

    }

    @Override
    public void showNoData() {

    }
}
