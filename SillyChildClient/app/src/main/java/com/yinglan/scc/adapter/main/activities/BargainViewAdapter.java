package com.yinglan.scc.adapter.main.activities;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.common.cklibrary.utils.myview.HorizontalListView;
import com.yinglan.scc.R;

import com.yinglan.scc.entity.main.ActivitiesBean.ResultBean.ListBean;
import com.yinglan.scc.utils.DisplayUtil;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页——活动特价商品列表  适配器
 */
public class BargainViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    private LayoutParams layoutParams;
    private int minspacing;
    private int maxspacing;

    public BargainViewAdapter(Context context, HorizontalListView listView) {
        super(context, R.layout.item_bargain);
        maxspacing = DisplayUtil.dip2px(context, 15);
        minspacing = DisplayUtil.dip2px(context, 10);
        int width = DisplayUtil.getWidthForScreen(context, 25, 2f, 3f);
        layoutParams = new LayoutParams(width, width / 2);
        LayoutParams layoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, width / 2 + (int) (context.getResources().getDimension(R.dimen.dimen60)));
        listView.setLayoutParams(layoutParams2);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        Log.d("position", position + "");
        RelativeLayout rl_region = (RelativeLayout) viewHolderHelper.getView(R.id.rl_region);
        rl_region.getParent().requestDisallowInterceptTouchEvent(true);
//        if (position == 0 && listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
//            rl_region.setPadding(maxspacing, 0, maxspacing, 0);
//        } else {
        if (position == 0) {
            rl_region.setPadding(maxspacing, 0, 0, 0);
            //    } else if (listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
            rl_region.setPadding(minspacing, 0, maxspacing, 0);
        } else {
            rl_region.setPadding(minspacing, 0, 0, 0);
        }
        //    }
        ((ImageView) viewHolderHelper.getView(R.id.img_region)).setLayoutParams(layoutParams);
        /**
         * 图片
         */
       // GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_region), R.mipmap.placeholderfigure);
//
//        /**
//         * 地区
//         */
//        viewHolderHelper.setText(R.id.tv_region, listBean.getName());

    }

}