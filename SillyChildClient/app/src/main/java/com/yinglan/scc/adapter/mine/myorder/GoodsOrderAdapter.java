package com.yinglan.scc.adapter.mine.myorder;

import android.content.Context;

import com.common.cklibrary.utils.myview.ChildListView;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.GoodOrderBean.ResultBean.ListBean;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商品订单列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodsOrderAdapter extends BGAAdapterViewAdapter<ListBean> {

    private List<GoodOrderAdapter> list = new ArrayList<GoodOrderAdapter>();

    public GoodsOrderAdapter(Context context) {
        super(context, R.layout.item_goodsorder);
    }


    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        viewHolderHelper.setText(R.id.tv_orderNumber, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_goodStatus, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_goodNumber, mContext.getString(R.string.general) + 2 + mContext.getString(R.string.items));
        viewHolderHelper.setText(R.id.tv_goodsMoney, listBean.getAvatar());
        ChildListView clv_shopgoods = (ChildListView) viewHolderHelper.getView(R.id.clv_shopgoods);
        GoodOrderAdapter adapter = new GoodOrderAdapter(mContext);
        clv_shopgoods.setAdapter(adapter);
        adapter.clear();
        //    adapter.addNewData(charterOrderBean.getResult().getList());
        list.add(adapter);

//        /**
//         * 图片
//         */
//        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.img_localTalent));
//
//        /**
//         * 姓名
//         */
//        viewHolderHelper.setText(R.id.tv_name, listBean.getName());
//
//        /**
//         * 地址
//         */
//        viewHolderHelper.setText(R.id.tv_address, "泰国·曼谷");
//
//        /**
//         * 类别
//         */
//        viewHolderHelper.setText(R.id.tv_sort, "泰国·曼谷");
//
//        /**
//         * 赞数
//         */
//        viewHolderHelper.setText(R.id.tv_greatNumber, "赞" + mContext.getString(R.string.zan));
    }


    @Override
    public void clear() {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).clear();
            }
            list.clear();
        }
        super.clear();
    }
}