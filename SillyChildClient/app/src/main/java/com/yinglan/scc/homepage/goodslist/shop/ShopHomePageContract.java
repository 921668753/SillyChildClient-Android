package com.yinglan.scc.homepage.goodslist.shop;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ShopHomePageContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取店铺首页轮播图
         */
        void getInfo();

        /**
         * 获取店铺首页要展示的商品列表
         */
        void getStoreIndexGoods(int storeid);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


