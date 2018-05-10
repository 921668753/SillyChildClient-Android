package com.yinglan.scc.mine.mywallet.coupons;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface CouponsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取优惠券列表
         */
        void getCoupons(String model_type, String type, int store_id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


