package com.yinglan.scc.mine.myshoppingcart.makesureorder.payresult;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface PayCompleteContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单部分信息
         */
        void getCartBalance(String cartIds);

    }

    interface View extends BaseView<Presenter, String> {

    }

}
