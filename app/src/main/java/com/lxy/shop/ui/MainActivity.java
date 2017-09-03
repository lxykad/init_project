package com.lxy.shop.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseActivity;
import com.lxy.shop.databinding.ActivityMainBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.ui.classify.ClassifyFragment;
import com.lxy.shop.ui.game.GameFragment;
import com.lxy.shop.ui.ranking.RankingFragment;
import com.lxy.shop.ui.recommend.fragment.RecommendFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private final String[] mTitles = {"推荐", "排行", "游戏", "游戏"};
    private ActivityMainBinding mBinding;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();

    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new RecommendFragment());
        mFragments.add(new RankingFragment());
        mFragments.add(new GameFragment());
        mFragments.add(new ClassifyFragment());

        mBinding.slidingTabLayout.setViewPager(mBinding.viewPager, mTitles,this,mFragments);
    }

    @Override
    protected void setActivityComponent(AppComponent appComponent) {

    }


}
