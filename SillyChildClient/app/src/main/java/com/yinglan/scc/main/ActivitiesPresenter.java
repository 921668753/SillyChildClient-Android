package com.yinglan.scc.main;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.R;
import com.yinglan.scc.constant.NumericConstants;
import com.yinglan.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ActivitiesPresenter implements ActivitiesContract.Presenter {

    private ActivitiesContract.View mView;

    public ActivitiesPresenter(ActivitiesContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAdvCat() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("acid", "2");
        RequestClient.getAdvCat(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {

                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.getSuccess(msg, 0);
            }
        });
    }

    @Override
    public void getActivities() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getActivities(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {

                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.getSuccess(msg, 1);
            }
        });
    }
}
