package com.yinglan.scc.adapter.mine.myshoppingcart;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean.ResultBean.ListBean;
import com.yinglan.scc.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.swipeitemlayout.BGASwipeItemLayout;

/**
 * 我的购物车适配器
 */
public class MyShoppingCartViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    /**
     * 当前处于打开状态的item
     */
    private List<BGASwipeItemLayout> mOpenedSil = new ArrayList<>();

    public MyShoppingCartViewAdapter(Context context) {
        super(context, R.layout.item_shopgoods);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        BGASwipeItemLayout swipeItemLayout = viewHolderHelper.getView(R.id.item_myShoppingCart);
        swipeItemLayout.setDelegate(new BGASwipeItemLayout.BGASwipeItemLayoutDelegate() {
            @Override
            public void onBGASwipeItemLayoutOpened(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutClosed(BGASwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutStartOpen(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        viewHolderHelper.setItemChildClickListener(R.id.img_checkbox);
        viewHolderHelper.setItemChildClickListener(R.id.tv_delete);
        viewHolderHelper.setItemChildClickListener(R.id.tv_subtract);
        viewHolderHelper.setItemChildClickListener(R.id.tv_add);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        BGASwipeItemLayout swipeItemLayout = viewHolderHelper.getView(R.id.item_myShoppingCart);
        if (listBean.getIsEdit() == 0) {
            viewHolderHelper.setVisibility(R.id.img_checkbox, View.GONE);
            swipeItemLayout.setSwipeAble(true);
        } else {
            if (listBean.getIsSelected() == 0) {
                viewHolderHelper.setImageResource(R.id.img_checkbox, R.mipmap.mineaddress_unselectxxx);
            } else {
                viewHolderHelper.setImageResource(R.id.img_checkbox, R.mipmap.mineaddress_selectxxx);
            }
            viewHolderHelper.setVisibility(R.id.img_checkbox, View.VISIBLE);
            swipeItemLayout.setSwipeAble(false);
        }

        /**
         *商品图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getDr_name(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);

        /**
         *商品名字
         */
        viewHolderHelper.setText(R.id.tv_goodName, listBean.getOrder_code());

        /**
         *商品简介
         */
        viewHolderHelper.setText(R.id.tv_goodSynopsis, listBean.getPushTime());

        /**
         *商品价格
         */
        viewHolderHelper.setText(R.id.tv_money, listBean.getPushTime());

        /**
         *商品数量
         */
        viewHolderHelper.setText(R.id.tv_goodNumber, listBean.getPushTime());

    }

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    public void closeOpenedSwipeItemLayout() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.close();
        }
        mOpenedSil.clear();
    }
}