package com.yinglan.scc.adapter.trip;

import android.content.Context;
import android.widget.ImageView;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.HotStrategyBean.ResultBean.ListBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 出行---超赞房源 适配器
 * Created by Admin on 2017/8/15.
 */

public class TripGreatHousesViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public TripGreatHousesViewAdapter(Context context) {
        super(context, R.layout.item_regionalstrategy);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_hotStrategy), R.mipmap.placeholderfigure);

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getTitle());

        /**
         * 描述
         */
        viewHolderHelper.setText(R.id.tv_describe, listBean.getContent());


        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_greatNumber, String.valueOf(listBean.getGood_num()));

    }

}