package com.lxy.shop.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseMainFragment;
import com.lxy.shop.databinding.FragmentRecommendBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerFragmentComponent;
import com.lxy.shop.di.module.FragmentModule;
import com.lxy.shop.ui.home.AndroidPresenter;
import com.lxy.shop.ui.home.SkilBean;
import com.lxy.shop.ui.home.adapter.HomeAdapter;
import com.lxy.shop.ui.home.contract.SkilContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/8.
 */

public class HomeFragment extends BaseMainFragment<AndroidPresenter> implements SkilContract.View {

    private FragmentRecommendBinding mBinding;
    private HomeAdapter mAdapter;
    private List<SkilBean> mList;

    @Override
    protected void visiableToUser() {
        System.out.println("HomeFragment======visiableToUser" );
    }

    @Override
    protected void firstVisiableToUser() {
        System.out.println("HomeFragment======firstVisiableToUser" );
        init();
        LoadData();
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent)
                .fragmentModule(new FragmentModule(this)).build().inject(this);
    }

    @Override
    public int getLayoutId() {

        return R.layout.fragment_recommend;
    }

    @Override
    public void initChildBinding() {
        mBinding = (FragmentRecommendBinding) mChildBinding;
    }

    @Override
    public void onEmptyClick(View view) {
        LoadData();
    }


    public void init() {
        mList = new ArrayList<>();
        mAdapter = new HomeAdapter(R.layout.list_item_recommend_fragment, mList,getContext());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SkilBean appBean = mList.get(position);
                Toast.makeText(view.getContext(), appBean.who, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LoadData() {
        mPresenter.getAndroidData();
    }

    @Override
    public void showResust(List<SkilBean> list) {

        mAdapter.addItems(list);
        System.out.println("HomeFragment======count:" + list.size());
    }

    @Override
    public void showNoData() {

    }

}
