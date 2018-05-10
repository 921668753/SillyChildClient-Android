package com.yinglan.scc.homepage.goodslist;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

public class GoodsListContract {

    interface Presenter extends BasePresenter {
        /**
         * 账号登录
         */
        void postToLogin(String phone, String pwd);

        /**
         * 获取用户信息
         */
        void getInfo();
    }

    interface View extends BaseView<Presenter, String> {
    }
}