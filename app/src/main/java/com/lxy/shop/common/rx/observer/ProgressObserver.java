package com.lxy.shop.common.rx.observer;

import android.content.Context;
import android.widget.MediaController;
import android.widget.Toast;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.common.exception.BaseException;

import io.reactivex.disposables.Disposable;

/**
 * Created by lxy on 2017/6/9.
 */

public abstract class ProgressObserver<T> extends ErrorHandObserver<T> {

    private BaseView mView;

    public ProgressObserver(Context context, BaseView baseView) {
        super(context);
        mView = baseView;
    }

    public boolean isShowProgress() {

        return true;
    }

    public ProgressObserver(Context context) {
        super(context);
    }

    @Override
    public void onSubscribe(Disposable d) {

        if (isShowProgress()) {
            mView.showLoading();
        }
    }

    @Override
    public void onComplete() {
        mView.dismissLoading();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);

        BaseException baseException = mErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());
    }
}
