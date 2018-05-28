package com.yinglan.scc.mine.myorder.goodorder;

import android.content.Context;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class GoodOrderPresenter implements GoodOrderContract.Presenter {
    private GoodOrderContract.View mView;

    public GoodOrderPresenter(GoodOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getOrderList(Context context, String status, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (!StringUtils.isEmpty(status)) {
            httpParams.put("status", status);
        }
        httpParams.put("page", page);
        RequestClient.getOrderList(context, httpParams, new ResponseListener<String>() {
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
