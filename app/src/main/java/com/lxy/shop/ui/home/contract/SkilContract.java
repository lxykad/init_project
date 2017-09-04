package com.lxy.shop.ui.home.contract;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.ui.home.SkilBean;

import java.util.List;

/**
 * Created by lxy on 2017/5/11.
 */

public interface SkilContract {

    interface View extends BaseView{

        void showResust(List<SkilBean> list);
        void showNoData();
        void showError(String msg);
    }
}
