package com.yinglan.scc.adapter.mine.myorder;

import android.content.Context;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.myorder.GoodOrderBean.DataBean.ResultBean.OrderItemsBean;
import com.yinglan.scc.utils.GlideImageLoader;

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
        viewHolderHelper.setText(R.id.tv_number, mContext.getString(R.string.general) + listBean.getNum() + mContext.getString(R.string.items));
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(listBean.getPrice())));
    }

}