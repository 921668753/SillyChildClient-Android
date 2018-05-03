package com.yinglan.scc.main;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MineContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取用户信息
         */
        void getInfo();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


