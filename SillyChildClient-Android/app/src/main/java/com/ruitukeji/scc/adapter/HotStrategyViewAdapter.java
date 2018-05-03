package com.ruitukeji.scc.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.HotStrategyBean.ResultBean.ListBean;
import com.ruitukeji.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 热门攻略 适配器
 * Created by Admin on 2017/8/15.
 */

public class HotStrategyViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public HotStrategyViewAdapter(Context context) {
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
        viewHolderHelper.setText(R.id.tv_describe, listBean.getSummary());


        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_greatNumber, String.valueOf(listBean.getGood_num()));

    }

}