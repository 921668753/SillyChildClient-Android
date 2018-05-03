package com.yinglan.scc.adapter.trip;

import android.content.Context;
import android.widget.ImageView;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.VisaBean.ResultBean.ListBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 签证/地区 item 适配器
 * Created by Admin on 2017/8/15.
 */

public class ItemVisaViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public ItemVisaViewAdapter(Context context) {
        super(context, R.layout.item_itemvisa);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 图片
         */
        GlideImageLoader.glideLoader(mContext, listBean.getArea_code(), (ImageView) viewHolderHelper.getView(R.id.img_visa), 1, R.mipmap.placeholderfigure);

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_country, listBean.getName());
    }

}