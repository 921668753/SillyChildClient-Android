package com.yinglan.scc.mine.myshoppingcart;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean.DataBean.StorelistBean.GoodslistBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface MyShoppingCartContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取购物车列表
         */
        void getMyShoppingCartList();

        /**
         * 删除商品
         */
        void postDeleteGood(List<GoodslistBean> masageList);

        void postDeleteGood(GoodslistBean goodslistBean);

        /**
         * 更新商品数量
         */
        void postCartUpdate(int cartid, int num, int productid, int flag);
    }

    interface View extends BaseView<Presenter, String> {

    }

}
