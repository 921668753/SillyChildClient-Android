package com.yinglan.scc.mine.personaldata;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PersonalDataContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取用户信息
         */
        void getInfo();

        /**
         * 更改用户信息
         */
        void setupInfo(String paramname, String voule, int resultsource);

        /**
         * 更改用户地址
         */
        void setupInfo(String country, String countryvoule, String city, String cityvoule, int resultsource);

        /**
         * 上传图片
         */
        void upPictures(String paramname,int resultsource);

        /**
         *更改傻孩子账号
         */
        void changeShzCode(String shz_code);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


