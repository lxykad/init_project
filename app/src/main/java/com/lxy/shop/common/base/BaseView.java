package com.lxy.shop.common.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by lxy on 2017/5/11.
 */

public interface BaseView {

    void showLoading(Disposable disposable);
    void  showError(String msg);
    void  dismissLoading();
}
