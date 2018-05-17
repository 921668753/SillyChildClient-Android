package com.yinglan.scc.adapter.mine.mycollection;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.mycollection.MyCollectionBean.DataBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的收藏中的商品 适配器
 * Created by Admin on 2017/8/15.
 */

public class MyCollectionViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public MyCollectionViewAdapter(Context context) {
        super(context, R.layout.item_good);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        helper.setItemChildClickListener(R.id.img_delete);
        helper.setItemChildClickListener(R.id.img_shoppingCart);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {

        /**
         *商品图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getSmall(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);

        /**
         *商品名字
         */
        viewHolderHelper.setText(R.id.tv_goodName, listBean.getName());

        /**
         *商品简介
         */
        viewHolderHelper.setText(R.id.tv_goodSynopsis, listBean.getBrief());

        /**
         *商品价格
         */
        viewHolderHelper.setText(R.id.tv_money, mContext.getString(R.string.renminbi) + listBean.getPrice());

        /**
         *是否自营
         */
        viewHolderHelper.setVisibility(R.id.tv_proprietary, View.GONE);

    }

}