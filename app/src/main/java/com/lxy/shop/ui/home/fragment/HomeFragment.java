package com.lxy.shop.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.base.BaseMainFragment;
import com.lxy.shop.common.constant.Constant;
import com.lxy.shop.common.http.HttpHelper;
import com.lxy.shop.common.http.Repository;
import com.lxy.shop.common.rx.RxHttpResponse;
import com.lxy.shop.common.rx.observer.ProgressObserver;
import com.lxy.shop.data.api.ApiService;
import com.lxy.shop.data.api.CacheProviders;
import com.lxy.shop.databinding.FragmentRecommendBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerFragmentComponent;
import com.lxy.shop.di.module.FragmentModule;
import com.lxy.shop.ui.home.AndroidPresenter;
import com.lxy.shop.ui.home.SkilBean;
import com.lxy.shop.ui.home.adapter.HomeAdapter;
import com.lxy.shop.ui.home.contract.SkilContract;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;
import io.rx_cache2.Source;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxy
 */

public class HomeFragment extends BaseMainFragment<AndroidPresenter> implements SkilContract.View {

    private FragmentRecommendBinding mBinding;
    private HomeAdapter mAdapter;
    private List<SkilBean.RealBean> mList;
    private CacheProviders mCacheProviders;
    private ApiService mApiService;

    @Override
    protected void visiableToUser() {
    }

    @Override
    protected void firstVisiableToUser() {
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
                SkilBean.RealBean appBean = mList.get(position);
                Toast.makeText(view.getContext(), appBean.who, Toast.LENGTH_SHORT).show();
            }
        });
        //如果需要rxcache做缓存，实例化下面内容
        File cacheFile = new File(getContext().getCacheDir(), "rxcache");
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        mCacheProviders = new RxCache.Builder()
                .persistence(cacheFile, new GsonSpeaker())
                .using(CacheProviders.class);
        mApiService = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }

    public void LoadData() {
//        mPresenter.getAndroidData();//不缓存
        mPresenter.getAndroidDataWithCache();

//        BaseApplication.getInstance().getRepository()
//                .getSkilList(1,false)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Reply<Response<SkilBean>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Reply<Response<SkilBean>> responseReply) {
//                        System.out.println("reply======="+ responseReply.getSource());//CLOUD
//                        showResust(responseReply.getData().body().results);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }

    @Override
    public void showResust(List<SkilBean.RealBean> list) {
        mAdapter.addItems(list);
    }

    @Override
    public void showNoData() {

    }

}
