package com.ruitukeji.scc.mine.mycollection;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.homepage.chartercustom.routes.CheckstandActivity;
import com.ruitukeji.scc.message.ChatMessageActivity;
import com.ruitukeji.scc.mine.myorder.MyOrderActivity;
import com.ruitukeji.scc.mine.myorder.charterorder.CharterOrderContract;
import com.ruitukeji.scc.mine.myorder.orderDetails.CharterOrderDetailsActivity;
import com.ruitukeji.scc.mine.myorder.orderevaluation.PostEvaluationActivity;
import com.ruitukeji.scc.mine.myorder.orderevaluation.SeeEvaluationActivity;
import com.ruitukeji.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MyCollectionPresenter implements MyCollectionContract.Presenter {
    private MyCollectionContract.View mView;
    private Uri data;
    private Intent jumpintent;

    public MyCollectionPresenter(MyCollectionContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCharterCollection(int p,int pageSize) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","User");
        httpParams.put("a","collectPackPro");
        httpParams.put("p", p);
        httpParams.put("pageSize", pageSize);
        RequestClient.getCharterCollectionList(httpParams, new ResponseListener<String>() {
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

    @Override
    public void getRouteCollection(int model_type,int p) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("model_type", model_type);
        httpParams.put("p", p);
        RequestClient.getRouteCollectionList(httpParams, new ResponseListener<String>() {
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
