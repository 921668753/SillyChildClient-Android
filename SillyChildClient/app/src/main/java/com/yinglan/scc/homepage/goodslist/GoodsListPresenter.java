package com.yinglan.scc.homepage.goodslist;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.R;
import com.yinglan.scc.retrofit.RequestClient;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ruitu on 2016/9/24.
 */

public class GoodsListPresenter implements GoodsListContract.Presenter {
    private GoodsListContract.View mView;

    public GoodsListPresenter(GoodsListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodsList(int page, int cat, int brand, int seckill, String keyword) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("page", page);
        httpParams.put("cat", cat);
        httpParams.put("brand", brand);
        httpParams.put("seckill", seckill);
        if (!StringUtils.isEmpty(keyword)) {
            httpParams.put("keyword", keyword);
        }
        RequestClient.getGoodsList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }
}
