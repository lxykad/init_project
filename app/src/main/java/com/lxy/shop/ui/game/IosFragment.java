package com.lxy.shop.ui.game;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.di.component.AppComponent;

/**
 * Created by lxy on 2017/6/8.
 */

public class IosFragment extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void visiableToUser() {
        System.out.println("IosFragment======visiableToUser");
    }

    @Override
    protected void firstVisiableToUser() {
        System.out.println("IosFragment======firstVisiableToUser");
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game;
    }

    @Override
    public void initChildBinding() {

    }

    @Override
    public void onEmptyClick(View view) {

    }
}
