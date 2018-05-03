package com.yinglan.scc.mine.mywallet.accountdetails;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AccountDetailsPresenter implements AccountDetailsContract.Presenter {
    private AccountDetailsContract.View mView;

    public AccountDetailsPresenter(AccountDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAccountDetail(String startTime,String endTime,int type,int p,int pageSize) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("m","Api");
        httpParams.put("c","User");
        httpParams.put("a","accountLog");
        httpParams.put("startTime",startTime);
        httpParams.put("endTime",endTime);
        httpParams.put("type",type);
        httpParams.put("p",p);
        httpParams.put("pageSize",pageSize);
        RequestClient.getPayRecord(httpParams, new ResponseListener<String>() {
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
