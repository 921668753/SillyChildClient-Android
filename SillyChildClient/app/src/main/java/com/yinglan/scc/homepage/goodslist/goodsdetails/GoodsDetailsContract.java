package com.yinglan.scc.homepage.goodslist.goodsdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface GoodsDetailsContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取商品详情
         */
        void getGoodDetail(int goodsid);

        /**
         * 收藏商品
         */
        void postFavoriteAdd(int goodsid);

        /**
         * 取消商品
         */
        void postUnfavorite(int goodsid);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


