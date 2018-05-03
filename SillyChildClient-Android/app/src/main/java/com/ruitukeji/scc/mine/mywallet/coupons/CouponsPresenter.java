package com.ruitukeji.scc.mine.mywallet.coupons;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.homepage.chartercustom.routes.CheckstandActivity;
import com.ruitukeji.scc.mine.myorder.MyOrderActivity;
import com.ruitukeji.scc.mine.myorder.charterorder.CharterOrderContract;
import com.ruitukeji.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class CouponsPresenter implements CouponsContract.Presenter {
    private CouponsContract.View mView;
    private Uri data;
    private Intent jumpintent;

    public CouponsPresenter(CouponsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getCoupons(String model_type,String type,int store_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m", "Api");
        httpParams.put("c", "User");
        httpParams.put("a", "getPackCouponList");
        httpParams.put("model_type", model_type);
        httpParams.put("type", type);
        if (store_id>0){
            httpParams.put("store_id", store_id);
        }

        RequestClient.getCouponsList(httpParams, new ResponseListener<String>() {
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
