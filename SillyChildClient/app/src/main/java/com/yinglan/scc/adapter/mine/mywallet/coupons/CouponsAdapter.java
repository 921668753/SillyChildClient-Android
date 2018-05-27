package com.yinglan.scc.adapter.mine.mywallet.coupons;

import android.content.Context;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.mine.mywallet.coupons.CouponsBean.DataBean;
import com.yinglan.scc.utils.DataUtil;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 优惠券  适配器
 * Created by Admin on 2017/8/15.
 */

public class CouponsAdapter extends BGAAdapterViewAdapter<DataBean> {

    public CouponsAdapter(Context context) {
        super(context, R.layout.item_coupons);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean dataBean) {
        viewHolderHelper.setText(R.id.tv_redpacketnum, dataBean.getType_money());
        viewHolderHelper.setText(R.id.tv_useconditions, mContext.getString(R.string.full) + dataBean.getMin_goods_amount() + mContext.getString(R.string.usable));
        viewHolderHelper.setText(R.id.tv_couponstype, dataBean.getType_name());
        viewHolderHelper.setText(R.id.tv_expirydate, DataUtil.formatData(StringUtils.toLong(dataBean.getEffective_end()), "yyyy-MM-dd"));
        viewHolderHelper.setText(R.id.tv_couponsexplain, mContext.getString(R.string.only) + dataBean.getUname() + mContext.getString(R.string.customerUse));

//        if (usestatus == NumericConstants.couponUnuse) {
//            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
//            viewHolderHelper.setImageResource(R.id.rl_coupons, R.mipmap.coupon_unused);
//        } else if (usestatus == NumericConstants.couponUsed) {
//            viewHolderHelper.setImageResource(R.id.rl_coupons, R.mipmap.coupon_unused);
//            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
//        } else {
//            viewHolderHelper.setImageResource(R.id.rl_coupons, R.mipmap.coupon_used);
//            viewHolderHelper.setVisibility(R.id.iv_image, View.VISIBLE);
//        }

//        switch (resultBean.getUse_type()) {
//            case NumericConstants.couponCharter:
//                viewHolderHelper.setText(R.id.tv_couponsexplain, context.getResources().getString(R.string.usableForChart));
//                break;
//            case NumericConstants.couponStore:
//                viewHolderHelper.setText(R.id.tv_couponsexplain, context.getResources().getString(R.string.usableForStore));
//                break;
//            case NumericConstants.couponHomestay:
//                viewHolderHelper.setText(R.id.tv_couponsexplain, context.getResources().getString(R.string.usableForHomestay));
//                break;
//        }
    }
}