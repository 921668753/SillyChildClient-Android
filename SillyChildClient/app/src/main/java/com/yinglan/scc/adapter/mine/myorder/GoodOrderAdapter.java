package com.yinglan.scc.adapter.mine.myorder;

import android.content.Context;
import android.widget.ImageView;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.MyOrderPicturesBean.ResultBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商品列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodOrderAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public GoodOrderAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getOrderString(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, listBean.getOrderString());
        viewHolderHelper.setText(R.id.tv_number, listBean.getOrderString());
        viewHolderHelper.setText(R.id.tv_goodDescribe, mContext.getString(R.string.general) + 2 + mContext.getString(R.string.items));
        viewHolderHelper.setText(R.id.tv_money, listBean.getOrderString());
    }

}