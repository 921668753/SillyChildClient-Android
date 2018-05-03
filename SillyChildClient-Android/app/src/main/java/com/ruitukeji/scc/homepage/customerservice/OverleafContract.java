package com.ruitukeji.scc.homepage.customerservice;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface OverleafContract {
    interface Presenter extends BasePresenter {

        /**
         * 随机创建一个用户
         */
        void createRandomAccountThenLoginChatServer();

        /**
         * 登录环信服务器
         */
        void login(String uname, String upwd);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


