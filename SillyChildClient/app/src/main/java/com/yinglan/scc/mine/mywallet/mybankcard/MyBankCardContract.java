package com.yinglan.scc.mine.mywallet.mybankcard;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/17.
 */

public interface MyBankCardContract {

    interface Presenter extends BasePresenter {
        /**
         * 发送提现信息
         */
        void getMyBankCard();
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
