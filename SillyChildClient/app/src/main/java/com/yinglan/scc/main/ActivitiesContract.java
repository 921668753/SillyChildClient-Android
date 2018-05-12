package com.yinglan.scc.main;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ActivitiesContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取首页信息
         */
        void getHomePage(String city);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


