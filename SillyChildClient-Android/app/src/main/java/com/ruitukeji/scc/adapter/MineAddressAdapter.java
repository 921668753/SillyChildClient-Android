package com.ruitukeji.scc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.MineAddressBean.ResultBean.ListBean;
import com.ruitukeji.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 收货地址 适配器
 * Created by Admin on 2017/8/15.
 */

public class MineAddressAdapter extends BGAAdapterViewAdapter<ListBean> {


    public MineAddressAdapter(Context context) {
        super(context, R.layout.item_deliveryaddress);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        helper.setItemChildClickListener(R.id.ll_setdefault);
        helper.setItemChildClickListener(R.id.ll_deliveryaddressedit);
        helper.setItemChildClickListener(R.id.ll_deliveryaddressdelete);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        viewHolderHelper.setText(R.id.tv_deliveryaddressname, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_deliveryaddressphone, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_deliveryaddress, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_number, listBean.getAvatar());
//        viewHolderHelper.setItemChildClickListener(R.id.ll_setdefault);
//        viewHolderHelper.setItemChildClickListener(R.id.ll_deliveryaddressedit);
//        viewHolderHelper.setItemChildClickListener(R.id.ll_deliveryaddressdelete);
        if (listBean.isdefault()) {
            viewHolderHelper.setImageResource(R.id.iv_deliveryaddresschose, R.mipmap.mineaddress_selectxxx);
            viewHolderHelper.setTextColor(R.id.tv_promptcharacters, mContext.getResources().getColor(R.color.greenColors));
        } else {
            viewHolderHelper.setImageResource(R.id.iv_deliveryaddresschose, R.mipmap.mineaddress_unselectxxx);
            viewHolderHelper.setTextColor(R.id.tv_promptcharacters, mContext.getResources().getColor(R.color.deliveryaddressdefault));
        }

    }

}