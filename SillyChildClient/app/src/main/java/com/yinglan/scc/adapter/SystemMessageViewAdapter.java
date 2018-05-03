package com.yinglan.scc.adapter;

import android.content.Context;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.SystemMessageBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 系统消息 适配器
 * Created by Admin on 2017/8/15.
 */

public class SystemMessageViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public SystemMessageViewAdapter(Context context) {
        super(context, R.layout.item_systemmessage);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 标题
         */
        if (StringUtils.toInt(listBean.getArticle_type(), 0) == 1 || StringUtils.toInt(listBean.getArticle_type(), 0) == 2) {
            viewHolderHelper.setText(R.id.tv_title, listBean.getMessage());
        } else {
            viewHolderHelper.setText(R.id.tv_title, listBean.getTitle());
        }
        if (listBean.getIs_read() == 0) {
            viewHolderHelper.setVisibility(R.id.tv_messageTag, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_messageTag, View.GONE);
        }

        /**
         * 时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getCreate_at());

    }

}