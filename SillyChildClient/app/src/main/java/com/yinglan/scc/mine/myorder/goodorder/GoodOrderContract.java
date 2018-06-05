package com.yinglan.scc.mine.myorder.goodorder;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scc.entity.mine.myorder.GoodOrderBean.DataBean.ResultBean;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface GoodOrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单信息
         */
        void getOrderList(Context context, String status, int page);

        /**
         * 取消订单
         */
        void postOrderCancel(Context context, int orderid);

        /**
         * 提醒发货
         */
        void postOrderRemind(Context context, int orderid);

        /**
         * 获取钱包余额
         */
        void getMyWallet(Context context, ResultBean resultBean);

        /**
         * 确认收货
         */
        void postOrderConfirm(Context context, int orderid);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


