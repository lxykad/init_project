package com.lxy.shop.common.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.shop.R;
import com.lxy.shop.databinding.ContentMultiStatusBinding;
import com.lxy.shop.di.component.AppComponent;

import javax.inject.Inject;

/**
 * 结合viewpager 实现fragment的懒加载
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private ContentMultiStatusBinding mBinding;
    private LayoutInflater mInflater;

    private boolean mIsViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean mHasFetchData; // 标识已经触发过懒加载数据

    @Inject
    public T mPresenter;
    protected ViewDataBinding mChildBinding;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mInflater = inflater;
        mBinding = DataBindingUtil.inflate(inflater, R.layout.content_multi_status, container, false);
        mBinding.emptyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyClick(v);
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupFragmentComponent(BaseApplication.getInstance().getAppComponent());

        setChildContentView();
        initChildBinding();

        mIsViewPrepared = true;
        lazyFetchDataIfPrepared();

    }

    private void setChildContentView() {

        mChildBinding = DataBindingUtil.inflate(mInflater, getLayoutId(), mBinding.contentLayout, true);

    }

    public void showProgressLayout() {
        showView(R.id.progress_layout);
    }

    public void showContentView() {

        showView(R.id.content_layout);
    }

    public void showEmptyView() {

        showView(R.id.empty_layout);
    }

    public void showEmptyView(int resId) {

        showEmptyView();
        mBinding.textTip.setText(resId);
    }

    public void showEmptyView(String msg) {

        showEmptyView();
        mBinding.textTip.setText(msg);
    }


    public void showView(int viewId) {

        for (int i = 0; i < mBinding.rootLayout.getChildCount(); i++) {

            if (mBinding.rootLayout.getChildAt(i).getId() == viewId) {
                mBinding.rootLayout.getChildAt(i).setVisibility(View.VISIBLE);
            } else {
                mBinding.rootLayout.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mHasFetchData = false;
        mIsViewPrepared = false;
    }

    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !mHasFetchData && mIsViewPrepared) {
            mHasFetchData = true; //已加载过数据
            firstVisiableToUser();
        }
        if (getUserVisibleHint() && mIsViewPrepared) {

            visiableToUser();
        }
    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected abstract void visiableToUser();

    protected abstract void firstVisiableToUser();

    protected abstract void setupFragmentComponent(AppComponent appComponent);

    public abstract int getLayoutId();

    public abstract void initChildBinding();

    public abstract void onEmptyClick(View view);


    @Override
    public void showLoading() {
        System.out.println("RecommendFragment======showLoading" );
        showProgressLayout();
    }

    @Override
    public void dismissLoading() {
        System.out.println("RecommendFragment======dismissLoading" );
        showContentView();
    }

    @Override
    public void showError(String msg) {
        System.out.println("RecommendFragment======showError" );
        showEmptyView(msg);
    }
}
