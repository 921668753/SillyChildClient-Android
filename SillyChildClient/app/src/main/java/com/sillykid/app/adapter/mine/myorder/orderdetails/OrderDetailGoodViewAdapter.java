package com.sillykid.app.adapter.mine.myorder.orderdetails;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.OrderDetailBean.DataBean.ItemListBean;
import com.sillykid.app.mine.myorder.goodorder.aftersalesdetails.AfterSalesDetailsActivity;
import com.sillykid.app.mine.myorder.goodorder.aftersalesdetails.ApplyAfterSalesActivity;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 订单详情 适配器
 * Created by Admin on 2017/8/15.
 */

public class OrderDetailGoodViewAdapter extends BGAAdapterViewAdapter<ItemListBean> {

    private int status = 0;

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

        if (status == 4 || status == 5) {
            viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.GONE);
            viewHolderHelper.getTextView(R.id.tv_checkAfterSale).setOnClickListener(null);
            viewHolderHelper.getTextView(R.id.tv_applyAfterSales).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ApplyAfterSalesActivity.class);
                    intent.putExtra("order_id", String.valueOf(model.getOrder_id()));
                    intent.putExtra("good_id", String.valueOf(model.getGoods_id()));
                    mContext.startActivity(intent);
                }
            });
        } else if (status == 7) {
            viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.VISIBLE);
            viewHolderHelper.getTextView(R.id.tv_applyAfterSales).setOnClickListener(null);
            viewHolderHelper.getTextView(R.id.tv_checkAfterSale).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, AfterSalesDetailsActivity.class);
                    intent.putExtra("order_id", String.valueOf(model.getOrder_id()));
                    intent.putExtra("good_id", String.valueOf(model.getGoods_id()));
                    mContext.startActivity(intent);
                }
            });
        } else {
            viewHolderHelper.setVisibility(R.id.tv_applyAfterSales, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkAfterSale, View.GONE);
            viewHolderHelper.getTextView(R.id.tv_applyAfterSales).setOnClickListener(null);
            viewHolderHelper.getTextView(R.id.tv_checkAfterSale).setOnClickListener(null);
        }

    }

    public void setStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }
}