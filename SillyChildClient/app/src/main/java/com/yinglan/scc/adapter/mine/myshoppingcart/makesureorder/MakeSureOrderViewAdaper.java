package com.yinglan.scc.adapter.mine.myshoppingcart.makesureorder;

import android.content.Context;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.myshoppingcart.makesureorder.MakeSureOrderBean.DataBean.GoodsBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 确认订单
 */
public class MakeSureOrderViewAdaper extends BGAAdapterViewAdapter<GoodsBean> {


    public MakeSureOrderViewAdaper(Context context) {
        super(context, R.layout.item_makesureorder);
    }

    @Override
    protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, GoodsBean model) {
        /**
         *商品图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getGoods_img(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);

        /**
         *商品名字
         */
        viewHolderHelper.setText(R.id.tv_goodName, model.getGoods_name());

        /**
         *商品简介
         */
        viewHolderHelper.setText(R.id.tv_goodDescribe, model.getSpecs());

        /**
         *商品价格
         */
        viewHolderHelper.setText(R.id.tv_money, mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));

        /**
         *商品数量
         */
        viewHolderHelper.setText(R.id.tv_goodNumber, String.valueOf(model.getNum()));
    }
}
