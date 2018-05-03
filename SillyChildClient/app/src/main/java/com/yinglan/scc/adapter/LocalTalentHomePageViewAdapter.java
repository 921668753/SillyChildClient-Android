package com.yinglan.scc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.HomePageBean.ResultBean.ActionBean.LocalBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页 当地达人 适配器
 * Created by Admin on 2017/8/15.
 */

public class LocalTalentHomePageViewAdapter extends BGAAdapterViewAdapter<LocalBean> {

    public LocalTalentHomePageViewAdapter(Context context) {
        super(context, R.layout.item_localtalent);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, LocalBean listBean) {
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
        viewHolderHelper.setText(R.id.tv_name, listBean.getName());

        /**
         * 地址
         */
        viewHolderHelper.setText(R.id.tv_address, listBean.getCity());

        /**
         * 类别
         */
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.shopkeepers)) || listBean.getLable() == 4) {
            viewHolderHelper.setVisibility(R.id.tv_shopkeepers, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_shopkeepers, View.GONE);
        }
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.landlord)) || listBean.getLable() == 3) {
            viewHolderHelper.setVisibility(R.id.tv_landlord, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_landlord, View.GONE);
        }
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.companyGuide)) || listBean.getLable() == 2) {
            viewHolderHelper.setVisibility(R.id.tv_companyGuide, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_companyGuide, View.GONE);
        }
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.user)) || listBean.getLable() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_user, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_user, View.GONE);
        }
        if (listBean.getType_info() != null && listBean.getType_info().contains(mContext.getString(R.string.platform)) || listBean.getIs_admin() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_platform, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_platform, View.GONE);
        }
        /**
         * 赞数
         */
        viewHolderHelper.setText(R.id.tv_zan, listBean.getGood_num() + mContext.getString(R.string.zan));

    }

}