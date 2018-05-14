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
    public void getActivities(String city) {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getHome(httpParams, city, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.getSuccess(response, 0);
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.errorMsg(msg, 0);
//                    }
//                });
//            }
//        });
    }
}
