package com.sillykid.app.adapter.mine.myorder.orderdetails;

import android.content.Context;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.OrderDetailBean.DataBean.ItemListBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 订单详情 适配器
 * Created by Admin on 2017/8/15.
 */

public class OrderDetailGoodViewAdapter extends BGAAdapterViewAdapter<ItemListBean> {

    public OrderDetailGoodViewAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ItemListBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getImage(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, model.getName());
        viewHolderHelper.setText(R.id.tv_goodDescribe, model.getSpecs());
        viewHolderHelper.setText(R.id.tv_number, model.getNum());
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));
    }

}