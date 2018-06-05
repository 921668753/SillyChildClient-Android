package com.sillykid.app.adapter.homepage.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.search.RecentSearchBean;
import com.zhy.view.flowlayout.FlowLayout;

import java.util.List;

/**
 * 最近搜索
 */
public class RecentSearchTagAdapter extends TagNewAdapter<RecentSearchBean.DataBean> {

    private final LayoutInflater mInflater;

    public RecentSearchTagAdapter(Context context, List<RecentSearchBean.DataBean> datas) {
        super(datas);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, RecentSearchBean.DataBean s) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_recentsearch, parent, false);
        tv.setText(s.getName());
        return tv;
    }
}
