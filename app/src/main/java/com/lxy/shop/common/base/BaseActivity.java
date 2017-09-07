package com.lxy.shop.common.base;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.lxy.shop.R;
import com.lxy.shop.utils.DensityUtil;
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
    private LayoutInflater mInflater;

    private ContentMultiStatusBinding mBinding;
    protected ViewDataBinding mChildBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //固定为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        setStatusTranslucent();
        setFitsWindow(true);
        mInflater = LayoutInflater.from(this);

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

        mChildBinding = DataBindingUtil.inflate(mInflater, getLayoutId(), mBinding.contentLayout, true);
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
        System.out.println("baseActivity=======loading");
        showProgressLayout();
        mDisposable = disposable;
        mSubsList.add(disposable);
    }

    @Override
    public void dismissLoading() {
        System.out.println("baseActivity=======dismissloading");
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


    //状态栏
    public void setStatusTranslucent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    public void setFitsWindow(boolean fits) {

        if (!fits) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));

            View viewById = findViewById(android.R.id.content);
            ViewCompat.setFitsSystemWindows(viewById, false);
            viewById.setPadding(0, DensityUtil.getStatusBarH(this), 0, 0);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            //此方法 4.4以上通用
            View viewById = findViewById(android.R.id.content);
            ViewCompat.setFitsSystemWindows(viewById, false);
            viewById.setBackgroundColor(ContextCompat.getColor(this, R.color.status_bar_color));
            viewById.setPadding(0, DensityUtil.getStatusBarH(this), 0, 0);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unSubscribrAllRxTasks();
    }
}
