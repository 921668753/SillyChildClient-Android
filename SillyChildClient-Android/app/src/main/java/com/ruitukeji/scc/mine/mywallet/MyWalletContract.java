package com.ruitukeji.scc.mine.mywallet;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.ruitukeji.scc.mine.myorder.MyOrderActivity;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MyWalletContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取用户信息
         */
        void getInfo();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


