package com.lxy.shop.common.base;

/**
 * Created by lxy on 2017/5/11.
 */

public interface BaseView {

    void showLoading();
    void  showError(String msg);
    void  dismissLoading();
}
