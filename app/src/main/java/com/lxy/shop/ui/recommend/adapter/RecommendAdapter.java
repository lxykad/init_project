package com.lxy.shop.ui.recommend.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.MediaController;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.ui.recommend.AppBean;
import com.lxy.shop.ui.recommend.SkilBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/9.
 */

public class RecommendAdapter extends BaseQuickAdapter<SkilBean, BaseViewHolder> {

    private List<SkilBean> mList = new ArrayList<>();
    private Context mContext;
    String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

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
        ImageView img = holder.getView(R.id.img_icon);
//        Glide.with(mContext).load(baseImgUrl+bean.icon).into(img);

        holder.addOnClickListener(R.id.btn_dl);

    }
}
