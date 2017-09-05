package com.lxy.shop.ui.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseMainFragment;
import com.lxy.shop.di.component.AppComponent;

/**
 * Created by lxy
 */

public class MyFragment extends BaseMainFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void visiableToUser() {
        System.out.println("MyFragment======visiableToUser");
    }

    @Override
    protected void firstVisiableToUser() {
        System.out.println("MyFragment======firstVisiableToUser");
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initChildBinding() {

    }

    @Override
    public void onEmptyClick(View view) {

    }
}
