package com.ruitukeji.scc.mine.mywallet;

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

public class MyWalletPresenter implements MyWalletContract.Presenter {
    private MyWalletContract.View mView;

    public MyWalletPresenter(MyWalletContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
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
