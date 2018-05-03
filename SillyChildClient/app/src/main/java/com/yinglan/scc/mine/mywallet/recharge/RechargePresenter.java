package com.yinglan.scc.mine.mywallet.recharge;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.R;
import com.yinglan.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class RechargePresenter implements RechargeContract.Presenter {
    private RechargeContract.View mView;

    public RechargePresenter(RechargeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void doRecharge(String account,String payWay,double amount) {

        if (TextUtils.isEmpty(account)){
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.inputAccount),0);
            return;
        }

        if (amount<0.01||amount>10000){
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.rechargeConfine),0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("payWay",payWay);
        httpParams.put("amount",amount+"");
        RequestClient.getRecharge(httpParams, new ResponseListener<String>() {
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
