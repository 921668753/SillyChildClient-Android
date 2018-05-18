package com.yinglan.scc.adapter.homepage.goodslist;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.homepage.goodslist.GoodsListBean.DataBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页——分类商品列表  适配器
 * Created by Administrator on 2017/9/6.
 */

public class GoodsListViewAdapter extends BGARecyclerViewAdapter<DataBean> {

    public GoodsListViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_mallhomepage);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getSmall(), (ImageView) helper.getView(R.id.img_good), R.mipmap.placeholderfigure);
        helper.setText(R.id.tv_goodName, model.getName());
        helper.setText(R.id.tv_goodSynopsis, model.getName());
        helper.setText(R.id.tv_goodMoney, mContext.getString(R.string.renminbi) + model.getPrice());
        helper.setText(R.id.tv_brand, model.getName());
        if (TextUtils.isEmpty(model.getName())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.VISIBLE);
            helper.setVisibility(R.id.img_timedSpecials, View.GONE);
        } else if (TextUtils.isEmpty(model.getName())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.GONE);
            helper.setVisibility(R.id.img_timedSpecials, View.VISIBLE);
        } else if (TextUtils.isEmpty(model.getName())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.VISIBLE);
            helper.setVisibility(R.id.img_timedSpecials, View.VISIBLE);
        } else {
            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
        }
    }

}
