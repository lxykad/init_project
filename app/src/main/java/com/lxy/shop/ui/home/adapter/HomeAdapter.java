package com.lxy.shop.ui.home.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.ui.home.SkilBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy
 */

public class HomeAdapter extends BaseQuickAdapter<SkilBean.RealBean, BaseViewHolder> {

    private List<SkilBean.RealBean> mList = new ArrayList<>();
    private Context mContext;

    public void addItems(List<SkilBean.RealBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public HomeAdapter(@LayoutRes int layoutResId, @Nullable List<SkilBean.RealBean> list, Context context) {
        super(layoutResId, list);

        mList = list;
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SkilBean.RealBean bean) {

        holder.setText(R.id.text_title, bean.who)
                .setText(R.id.text_size, bean.desc);

        holder.addOnClickListener(R.id.btn_dl);

    }
}
