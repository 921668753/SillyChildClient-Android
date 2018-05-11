package com.yinglan.scc.mine.myshoppingcart;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean.ResultBean.ListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface MyShoppingCartContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取购物车列表
         */
        void getMyShoppingCartList(String type, int page);

        /**
         * 删除商品
         */
        void postDeleteGood(List<ListBean> masageList);

        /**
         * 减少商品
         */
        void postReduceGood(int id);

        /**
         * 增加商品
         */
        void postAddGood(int id);
    }

    interface View extends BaseView<Presenter, String> {

    }

}
