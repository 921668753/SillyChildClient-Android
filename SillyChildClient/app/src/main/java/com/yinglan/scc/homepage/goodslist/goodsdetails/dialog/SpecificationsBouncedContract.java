package com.yinglan.scc.homepage.goodslist.goodsdetails.dialog;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SpecificationsBouncedContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取商品规格
         */
        void getGoodsSpec(Context context, int id);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


