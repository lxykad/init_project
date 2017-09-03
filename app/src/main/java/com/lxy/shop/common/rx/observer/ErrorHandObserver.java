package com.lxy.shop.common.rx.observer;

import android.content.Context;
import android.util.Log;

import com.lxy.shop.common.exception.BaseException;
import com.lxy.shop.common.rx.RxErrorHandler;

/**
 * Created by lxy on 2017/6/7.
 */

public abstract class ErrorHandObserver<T> extends BaseObserver<T> {

    protected RxErrorHandler mErrorHandler = null;

    public ErrorHandObserver(Context context) {
       mErrorHandler = new RxErrorHandler(context);
    }

    @Override
    public void onError(Throwable e) {

        BaseException baseException = mErrorHandler.handleError(e);

        if (baseException == null) {

            e.printStackTrace();

        } else {
            mErrorHandler.showErrorMessage(baseException);
        }

    }
}
