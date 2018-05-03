package com.ruitukeji.scc.adapter;

import android.content.Context;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.MyOrderPicturesBean.ResultBean;
import com.ruitukeji.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 司导消息 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodOrderPicturesAdapter extends BGAAdapterViewAdapter<ResultBean> {
    private Context context;

    public GoodOrderPicturesAdapter(Context context) {
        super(context, R.layout.item_goodsorder_images);
        this.context = context;
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getOrderString(), (ImageView) viewHolderHelper.getView(R.id.iv_goodspictures), R.mipmap.placeholderfigure1);
    }

}