package com.sillykid.app.adapter.homepage.goodslist.goodsdetails.dialog;


import android.content.Context;

import com.sillykid.app.R;
import com.sillykid.app.entity.HomePageBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商品详情--------规格弹框
 */
public class SpecificationsBouncedViewAdapter extends BGAAdapterViewAdapter<HomePageBean.ResultBean.ActionBean.HotBean> {


    public SpecificationsBouncedViewAdapter(Context context) {
        super(context, R.layout.dialog_item_specificationsbounced);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, HomePageBean.ResultBean.ActionBean.HotBean model) {


    }
}
