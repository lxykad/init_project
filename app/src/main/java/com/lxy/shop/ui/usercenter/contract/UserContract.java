package com.lxy.shop.ui.usercenter.contract;

import com.lxy.shop.common.User;
import com.lxy.shop.common.base.BaseView;

/**
 * Created by lxy
 */

public interface UserContract {

    interface View extends BaseView{

        void showResust(User user);
        void showNoData();
        void showError(String msg);
    }

}
