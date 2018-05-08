package com.yinglan.scc.mine.mywallet.withdrawal;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/17.
 */

public interface WithdrawalContract {

    interface Presenter extends BasePresenter {
        /**
         * 发送提现信息
         */
        void postWithdrawal(String withdrawalAmount, int bankId);

        /**
         * 是否登录
         */
        void isLogin(int flag);
    }

    interface View extends BaseView<Presenter, String> {
//        /**
//         * http请求正确
//         *
//         * @param s
//         */
//        void getSuccess(String s);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg);
    }


}
