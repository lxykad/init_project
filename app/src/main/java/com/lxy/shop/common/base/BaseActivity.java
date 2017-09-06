package com.lxy.shop.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lxy.shop.R;
import com.lxy.shop.databinding.ContentMultiStatusBinding;
import com.lxy.shop.di.component.AppComponent;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lxy
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    public T mPresenter;
    protected CompositeDisposable mSubsList = new CompositeDisposable();
    private Disposable mDisposable;

    private ContentMultiStatusBinding mBinding;
    protected ViewDataBinding mChildBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.content_multi_status);
        mBinding.emptyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyClick(v);
            }
        });

        setActivityComponent(BaseApplication.getInstance().getAppComponent());
        setChildContentView();
        initChildBinding();

    }

    private void setChildContentView() {

        mChildBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    private void unSubscribrAllRxTasks() {
        if (mSubsList.size() > 0) {
            mSubsList.clear();
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(Disposable disposable) {
        showProgressLayout();
        mDisposable = disposable;
        mSubsList.add(disposable);
    }

    @Override
    public void dismissLoading() {
        showContentView();
        mSubsList.remove(mDisposable);
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
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

    protected abstract void onEmptyClick(View view);
    public abstract int getLayoutId();
    public abstract void initChildBinding();

    protected abstract void setActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unSubscribrAllRxTasks();
    }
}
