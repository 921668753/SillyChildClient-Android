package com.yinglan.scc.mine.myorder.goodorder;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface GoodOrderContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取用户信息
         */
        void getOrderList(Context context, String status, int page);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


