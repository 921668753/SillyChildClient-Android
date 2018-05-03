package com.ruitukeji.scc.mine.mywallet.withdraw;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface WithdrawContract {
    interface Presenter extends BasePresenter {
        /**
         * 我的 我的钱包 提现
         */
        void doWithdraw(String amount, String mymoney, String account, String person, String withdrawalsway);

        /**
         * 获取用户信息
         */
        void getInfo(int flagint);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


