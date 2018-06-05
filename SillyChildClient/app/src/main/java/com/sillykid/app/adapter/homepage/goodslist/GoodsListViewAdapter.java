package com.sillykid.app.adapter.homepage.goodslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.goodslist.GoodsListBean.DataBean;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页——分类商品列表  适配器
 * Created by Administrator on 2017/9/6.
 */

public class GoodsListViewAdapter extends BGARecyclerViewAdapter<DataBean> {

    public GoodsListViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_mallhomepage);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {
        GlideImageLoader.glideLoaderRaudio(mContext, model.getThumbnail(), (ImageView) helper.getView(R.id.img_good), 5, R.mipmap.placeholderfigure);
        helper.setText(R.id.tv_goodName, model.getName());
        helper.setText(R.id.tv_goodSynopsis, model.getBrief());
        helper.setText(R.id.tv_goodMoney, mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));
        helper.setText(R.id.tv_brand, model.getBrand_name());
        if (StringUtils.isEmpty(model.getStore_name()) && StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
        } else if (!StringUtils.isEmpty(model.getStore_name()) && StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.VISIBLE);
            helper.setVisibility(R.id.tv_timedSpecials, View.GONE);
        } else if (StringUtils.isEmpty(model.getStore_name()) && !StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.GONE);
            helper.setVisibility(R.id.tv_timedSpecials, View.VISIBLE);
            helper.setText(R.id.tv_timedSpecials, model.getGoods_tag());
        } else if (!StringUtils.isEmpty(model.getStore_name()) && !StringUtils.isEmpty(model.getGoods_tag())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.VISIBLE);
            helper.setVisibility(R.id.tv_timedSpecials, View.VISIBLE);
            helper.setText(R.id.tv_timedSpecials, model.getGoods_tag());
        } else {
            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
        }
    }

}
