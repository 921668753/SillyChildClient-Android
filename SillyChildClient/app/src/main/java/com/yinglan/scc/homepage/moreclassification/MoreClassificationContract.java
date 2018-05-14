package com.yinglan.scc.homepage.moreclassification;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MoreClassificationContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取更多分类信息
         */
        void getMoreClassification();

        /**
         * 获取分类信息
         */
        void getClassification();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


