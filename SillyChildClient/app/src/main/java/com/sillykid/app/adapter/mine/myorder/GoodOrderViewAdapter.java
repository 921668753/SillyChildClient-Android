package com.sillykid.app.adapter.mine.myorder;

import android.content.Context;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.GoodOrderBean.DataBean.ResultBean.OrderItemsBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 订单列表 商品列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodOrderViewAdapter extends BGAAdapterViewAdapter<OrderItemsBean> {


    public GoodOrderViewAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, OrderItemsBean listBean) {
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getImage(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, listBean.getName());
        viewHolderHelper.setText(R.id.tv_goodDescribe, listBean.getSn());
        viewHolderHelper.setText(R.id.tv_number, String.valueOf(listBean.getNum()));
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(listBean.getPrice())));
    }

}