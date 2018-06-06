package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.sillykid.app.entity.mine.myorder.GoodOrderBean.DataBean.ResultBean;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ApplyAfterSalesContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取售后信息
         */
        void getOrderRefund(String order_id);

        /**
         * 提交售后信息
         */
        void postOrderRefund(int orderid, String typeCode, String reasonCode, String apply_alltotal);

    }

    interface View extends BaseView<Presenter, String> {
    }

}

