package com.lxy.shop.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lxy.shop.di.component.AppComponent;

import javax.inject.Inject;

/**
 * Created by lxy on 2017/5/11.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    @Inject
    public T mPresenter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActivityComponent(BaseApplication.getInstance().getAppComponent());

    }

    protected abstract void setActivityComponent(AppComponent appComponent);
}
