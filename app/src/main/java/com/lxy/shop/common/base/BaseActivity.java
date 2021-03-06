package com.lxy.shop.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lxy.shop.di.component.AppComponent;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lxy on 2017/5/11.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    @Inject
    public T mPresenter;
    protected CompositeDisposable mSubsList = new CompositeDisposable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActivityComponent(BaseApplication.getInstance().getAppComponent());

    }

    private void unSubscribrAllRxTasks() {
        System.out.println("mSubsList=======" + mSubsList.size());
        if (mSubsList.size() > 0) {
            mSubsList.clear();
        }
    }

    //添加RxJava订阅到队列管理
    public void sub(Disposable task) {
        mSubsList.add(task);
    }

    //从队列中移除RxJava订阅
    public void remove(Disposable task) {
        mSubsList.remove(task);
    }

    protected abstract void setActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unSubscribrAllRxTasks();
    }
}
