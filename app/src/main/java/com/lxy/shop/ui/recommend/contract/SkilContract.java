package com.lxy.shop.ui.recommend.contract;

import com.lxy.shop.common.base.BaseView;
import com.lxy.shop.common.rx.ResponseBean;
import com.lxy.shop.ui.recommend.SkilBean;
import com.lxy.shop.ui.recommend.bean.RecommendBean;

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
