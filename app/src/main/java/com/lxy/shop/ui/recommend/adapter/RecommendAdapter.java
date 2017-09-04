package com.lxy.shop.ui.recommend.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.ui.recommend.SkilBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/9.
 */

public class RecommendAdapter extends BaseQuickAdapter<SkilBean, BaseViewHolder> {

    private List<SkilBean> mList = new ArrayList<>();
    private Context mContext;

    public void addItems(List<SkilBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public RecommendAdapter(@LayoutRes int layoutResId, @Nullable List<SkilBean> list, Context context) {
        super(layoutResId, list);

        mList = list;
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SkilBean bean) {

        holder.setText(R.id.text_title, bean.desc)
                .setText(R.id.text_size, bean.who);

        holder.addOnClickListener(R.id.btn_dl);

    }
}
