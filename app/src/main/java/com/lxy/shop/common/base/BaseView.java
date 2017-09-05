package com.lxy.shop.common.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by lxy
 */

public interface BaseView {

    void showLoading(Disposable disposable);
    void  showError(String msg);
    void  dismissLoading();
}
