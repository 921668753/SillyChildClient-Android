package com.yinglan.scc.adapter;

import android.content.Context;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.IndexCityBean.ResultBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 地址poi搜索  适配器
 * Created by Admin on 2017/9/8.
 */

public class AddressPoiSearchViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public AddressPoiSearchViewAdapter(Context context) {
        super(context, R.layout.item_addresssearch);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         * 类别
         */
        helper.setText(R.id.tv_addressName, model.getName());

    }
}
