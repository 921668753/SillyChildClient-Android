package com.yinglan.scc.mine.mywallet;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scc.mine.myorder.MyOrderActivity;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MyWalletContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取钱包余额
         */
        void getMyWallet();

    }

    interface View extends BaseView<Presenter, String> {


    }

}


