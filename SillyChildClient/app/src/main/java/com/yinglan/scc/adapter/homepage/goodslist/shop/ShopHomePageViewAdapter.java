package com.yinglan.scc.adapter.homepage.goodslist.shop;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.homepage.goodslist.shop.ShopHomePageBean.DataBean;
import com.yinglan.scc.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 首页——店铺首页  适配器
 * Created by Administrator on 2017/9/6.
 */

public class ShopHomePageViewAdapter extends BGARecyclerViewAdapter<DataBean> {

    public ShopHomePageViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_mallhomepage);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, DataBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getSmall(), (ImageView) helper.getView(R.id.img_good), R.mipmap.placeholderfigure);
        helper.setText(R.id.tv_goodName, model.getName());
        helper.setText(R.id.tv_goodSynopsis, model.getName());
        helper.setText(R.id.tv_goodMoney, model.getPrice());
        helper.setText(R.id.tv_brand, model.getName());
        if (TextUtils.isEmpty(model.getName())) {
            helper.setVisibility(R.id.ll_bottomLabel, View.VISIBLE);
            helper.setVisibility(R.id.img_proprietary, View.GONE);
            helper.setVisibility(R.id.img_timedSpecials, View.VISIBLE);
        } else {
            helper.setVisibility(R.id.ll_bottomLabel, View.GONE);
        }
    }

//    @Override
//    public void onBindViewHolder(GoodsListView goodsListView, int position) {
//        GlideImageLoader.glideOrdinaryLoader(mcontext, list.get(position).getImg(), goodsListView.img_good, R.mipmap.placeholderfigure);
//        goodsListView.tv_goodName.setText(list.get(position).getTitle());
//        goodsListView.tv_goodSynopsis.setText(list.get(position).getTitle());
//        goodsListView.tv_goodMoney.setText(list.get(position).getTitle());
//        goodsListView.tv_brand.setText(list.get(position).getTitle());
//        if (TextUtils.isEmpty(list.get(position).getSubTitle())) {
//            goodsListView.ll_bottomLabel.setVisibility(View.GONE);
//            goodsListView.tv_proprietary.setVisibility(View.GONE);
//            goodsListView.tv_timedSpecials.setVisibility(View.VISIBLE);
//        } else {
//            goodsListView.tv_proprietary.setVisibility(View.GONE);
//            goodsListView.tv_timedSpecials.setVisibility(View.VISIBLE);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class GoodsListView extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        ImageView img_good;
//        TextView tv_goodName;
//        TextView tv_goodSynopsis;
//        TextView tv_goodMoney;
//        TextView tv_brand;
//        LinearLayout ll_bottomLabel;
//        TextView tv_proprietary;
//        TextView tv_timedSpecials;
//
//        private GoodsListItemOnClickListener itemlistener;
//
//        public GoodsListView(View itemView, GoodsListItemOnClickListener listener) {
//            super(itemView);
//            img_good = (ImageView) itemView.findViewById(R.id.img_good);
//            tv_goodName = (TextView) itemView.findViewById(R.id.tv_goodName);
//            tv_goodSynopsis = (TextView) itemView.findViewById(R.id.tv_goodSynopsis);
//            tv_brand = (TextView) itemView.findViewById(R.id.tv_brand);
//            ll_bottomLabel = (LinearLayout) itemView.findViewById(R.id.ll_bottomLabel);
//            tv_proprietary = (TextView) itemView.findViewById(R.id.tv_proprietary);
//            tv_timedSpecials = (TextView) itemView.findViewById(R.id.tv_timedSpecials);
//            this.itemlistener = listener;
//            itemView.setOnClickListener(this);
//        }
//
//
//        @Override
//        public void onClick(View view) {
//            if (itemlistener != null) itemlistener.goodsListOnItemClick(view, getPosition());
//        }
//    }
//
//    public interface GoodsListItemOnClickListener {
//        void goodsListOnItemClick(View view, int postion);
//
//        //  void masonryOnLongItemClick(View view, int postion);
//    }

}
