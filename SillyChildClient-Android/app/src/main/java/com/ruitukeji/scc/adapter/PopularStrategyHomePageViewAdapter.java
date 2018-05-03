package com.ruitukeji.scc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.HomePageBean.ResultBean.ActionBean.HotBean;
import com.ruitukeji.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 热门攻略 适配器
 * Created by Admin on 2017/8/15.
 */

public class PopularStrategyHomePageViewAdapter extends BGAAdapterViewAdapter<HotBean> {

    public PopularStrategyHomePageViewAdapter(Context context) {
        super(context, R.layout.item_localtalent);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, HotBean listBean) {
        Log.d("position", position + "");
        LinearLayout ll_localTalent = (LinearLayout) viewHolderHelper.getView(R.id.ll_localTalentcone);
        ll_localTalent.getParent().requestDisallowInterceptTouchEvent(true);
        if (position == 0 && listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
            ll_localTalent.setPadding(30, 0, 30, 0);
        } else {
            if (position == 0) {
                ll_localTalent.setPadding(30, 0, 0, 0);
            } else if (listBean.getStatusL() != null && listBean.getStatusL().equals("last")) {
                ll_localTalent.setPadding(20, 0, 30, 0);
            } else {
                ll_localTalent.setPadding(20, 0, 0, 0);
            }
        }

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getCover_img(), (ImageView) viewHolderHelper.getView(R.id.img_localTalent), R.mipmap.placeholderfigure);

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getUser_name());

        /**
         * 地址
         */
        viewHolderHelper.setText(R.id.tv_address, listBean.getCity());

        /**
         * 类别
         */
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.shopkeepers))) {
            viewHolderHelper.setVisibility(R.id.tv_shopkeepers, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_shopkeepers, View.GONE);
        }
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.landlord))) {
            viewHolderHelper.setVisibility(R.id.tv_landlord, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_landlord, View.GONE);
        }
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.companyGuide))) {
            viewHolderHelper.setVisibility(R.id.tv_companyGuide, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_companyGuide, View.GONE);
        }
        if (listBean.getIs_admin() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_platform, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_user, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_user, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_platform, View.GONE);
        }
        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_zan, listBean.getPraiseNum() + mContext.getString(R.string.zan));

    }

}