package com.yinglan.scc.adapter.homepage.moreclassification;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.HomePageBean.ResultBean.ActionBean.HotBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 更多分类 适配器
 * Created by Admin on 2017/8/15.
 */

public class MoreClassificationViewAdapter extends BGAAdapterViewAdapter<HotBean> {

    public MoreClassificationViewAdapter(Context context) {
        super(context, R.layout.item_moreclassification);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, HotBean listBean) {
        Log.d("position", position + "");

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_boutiqueLine), R.mipmap.placeholderfigure);

        /**
         * 背景色
         */
        if (listBean.getCity_id() == 0) {
            viewHolderHelper.setVisibility(R.id.tv_tab, View.INVISIBLE);
            viewHolderHelper.setBackgroundColorRes(R.id.ll_moreClassification, R.color.whiteColors);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_tab, View.VISIBLE);
            viewHolderHelper.setBackgroundColorRes(R.id.ll_moreClassification, R.color.f1F4F6Colors);
        }

        /**
         * 名字
         */
        viewHolderHelper.setText(R.id.tv_moreClassification, listBean.getPraiseNum());

    }

}