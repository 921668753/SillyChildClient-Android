package com.yinglan.scc.mine.myshoppingcart.makesureorder;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface PaymentOrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取购物车列表
         */
        void getMyShoppingCartList();
    }

    interface View extends BaseView<Presenter, String> {

    }

}
