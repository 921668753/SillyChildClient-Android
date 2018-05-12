package com.yinglan.scc.adapter.mine.mywallet.coupons;

import android.content.Context;
import android.view.View;

import com.yinglan.scc.R;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.entity.CouponsBean.ResultBean;

import java.text.SimpleDateFormat;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 优惠券  适配器
 * Created by Admin on 2017/8/15.
 */

public class CouponsAdapter extends BGAAdapterViewAdapter<ResultBean> {
    private Context context;
    private SimpleDateFormat dateformat;
    private int usestatus;

    public CouponsAdapter(Context context, int usestatus) {
        super(context, R.layout.item_coupons);
        this.context = context;
        this.usestatus = usestatus;
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean resultBean) {

        viewHolderHelper.setText(R.id.tv_redpacketnum, (int) Double.parseDouble(resultBean.getMoney()) + "");
        viewHolderHelper.setText(R.id.tv_useconditions, context.getResources().getString(R.string.full) + (int) Double.parseDouble(resultBean.getCondition()) + context.getResources().getString(R.string.usable));
        viewHolderHelper.setText(R.id.tv_couponstype, resultBean.getName());
        viewHolderHelper.setText(R.id.tv_expirydate, formatData(resultBean.getUse_end_time()));
        if (usestatus == NumericConstants.couponUnuse) {
            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
            viewHolderHelper.setImageResource(R.id.rl_coupons, R.mipmap.coupon_unused);
        } else if (usestatus == NumericConstants.couponUsed) {
            viewHolderHelper.setImageResource(R.id.rl_coupons, R.mipmap.coupon_unused);
            viewHolderHelper.setVisibility(R.id.iv_image, View.GONE);
        } else {
            viewHolderHelper.setImageResource(R.id.rl_coupons, R.mipmap.coupon_used);
            viewHolderHelper.setVisibility(R.id.iv_image, View.VISIBLE);
        }

        switch (resultBean.getUse_type()) {
            case NumericConstants.couponCharter:
                viewHolderHelper.setText(R.id.tv_couponsexplain, context.getResources().getString(R.string.usableForChart));
                break;
            case NumericConstants.couponStore:
                viewHolderHelper.setText(R.id.tv_couponsexplain, context.getResources().getString(R.string.usableForStore));
                break;
            case NumericConstants.couponHomestay:
                viewHolderHelper.setText(R.id.tv_couponsexplain, context.getResources().getString(R.string.usableForHomestay));
                break;
        }


    }

    /**
     * unix时间戳需要处理之后才可以被转换为日期
     *
     * @return
     */
    public String formatData(long timeorigin) {
        if (timeorigin == 0) {
            return "";
        }
        timeorigin = timeorigin * 1000;
        if (dateformat == null) dateformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateformat.format(timeorigin);
        } catch (Exception e) {
            return "";
        }
    }

}