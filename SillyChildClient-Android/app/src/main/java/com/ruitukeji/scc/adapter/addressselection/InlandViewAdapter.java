package com.ruitukeji.scc.adapter.addressselection;

import android.content.Context;

import com.ruitukeji.scc.R;
import com.ruitukeji.scc.entity.InlandBean.ResultBean;

import java.util.List;


/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class InlandViewAdapter extends CommonAdapter<ResultBean> {
    public InlandViewAdapter(Context context, int layoutId, List<ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, final ResultBean cityBean) {
        holder.setText(R.id.tv_country, cityBean.getName());
        holder.setText(R.id.tv_areaCode, "");
    }
}