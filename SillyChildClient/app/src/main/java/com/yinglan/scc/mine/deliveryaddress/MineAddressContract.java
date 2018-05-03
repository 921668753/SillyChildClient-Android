package com.yinglan.scc.mine.deliveryaddress;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MineAddressContract {
    interface Presenter extends BasePresenter {
        /**
         * 收货地址列表
         */
        void allAddress();

        /**
         * 收货地址删除
         */
        void deleteAddress(String paramname, String voule, int resultsource);

        /**
         * 收货地址添加
         */
        void addAddress(String paramname, File voule, int resultsource);

        /**
         *设置默认收货地址
         */
        void defaultAddress(String paramname, File voule, int resultsource);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


