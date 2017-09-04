package com.lxy.shop.ui.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.di.component.AppComponent;

/**
 * Created by lxy on 2017/6/8.
 */

public class AndroidFragment extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void visiableToUser() {
        System.out.println("AndroidFragment======visiableToUser");
    }

    @Override
    protected void firstVisiableToUser() {
        System.out.println("AndroidFragment======firstVisiableToUser");
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void initChildBinding() {

    }

    @Override
    public void onEmptyClick(View view) {

    }
}
