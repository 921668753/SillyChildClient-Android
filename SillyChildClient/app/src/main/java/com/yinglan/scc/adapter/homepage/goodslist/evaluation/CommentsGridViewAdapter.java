package com.yinglan.scc.adapter.homepage.goodslist.evaluation;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.yinglan.scc.R;
import com.yinglan.scc.utils.DisplayUtil;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Admin on 2017/9/29.
 */

public class CommentsGridViewAdapter extends BGAAdapterViewAdapter<String> {


    private final LayoutParams layoutParams;

    public CommentsGridViewAdapter(Context context) {
        super(context, R.layout.item_gv_charterlist);
        int widthpixels=context.getResources().getDisplayMetrics().widthPixels;
        layoutParams=new LayoutParams(LayoutParams.MATCH_PARENT,(widthpixels- DisplayUtil.dip2px(context,(15 * 5)))/4);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, String model) {

        helper.getView(R.id.img_vehicles).setLayoutParams(layoutParams);
        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model, (ImageView) helper.getView(R.id.img_vehicles), R.mipmap.placeholderfigure1);

    }
}
