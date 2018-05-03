package com.ruitukeji.scc.mall;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MoreClassificationPresenter implements MoreClassificationContract.Presenter {
    private MoreClassificationContract.View mView;

    public MoreClassificationPresenter(MoreClassificationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getMoreClassification() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }
}