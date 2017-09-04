package com.lxy.shop.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.databinding.ActivityMainBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.classify.MyFragment;
import com.lxy.shop.ui.game.IosFragment;
import com.lxy.shop.ui.ranking.AndroidFragment;
import com.lxy.shop.ui.recommend.fragment.RecommendFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private final String[] mTitles = {"首页", "Android", "iOS", "我的"};
    private ActivityMainBinding mBinding;
    private ArrayList<Fragment> mFragments;
    private int[] mIconUnselectIds = {
            R.drawable.ic_home_unselect, R.drawable.ic_android_unselect,
            R.drawable.ic_phone_unselect, R.drawable.ic_my_unselect};
    private int[] mIconSelectIds = {
            R.drawable.ic_home_select, R.drawable.ic_android_select,
            R.drawable.ic_phone_select, R.drawable.ic_my_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new RecommendFragment());
        mFragments.add(new AndroidFragment());
        mFragments.add(new IosFragment());
        mFragments.add(new MyFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mBinding.tabLayout.setTabData(mTabEntities,this,R.id.framelayout,mFragments);
//        mBinding.tabLayout.setCurrentTab(0);
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }

}
