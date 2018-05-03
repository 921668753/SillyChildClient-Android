package com.ruitukeji.scc.mine.mycollection;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.ruitukeji.scc.mine.myorder.MyOrderActivity;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MyCollectionContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取收藏的包车产品
         */
        void getCharterCollection(int p, int pageSize);
        /**
         * 获取收藏的路线列表
         */
        void getRouteCollection(int model_type, int p);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


