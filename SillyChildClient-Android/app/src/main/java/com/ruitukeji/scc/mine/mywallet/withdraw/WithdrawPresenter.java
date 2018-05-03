package com.ruitukeji.scc.mine.mywallet.withdraw;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.mine.mywallet.recharge.RechargeContract;
import com.ruitukeji.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class WithdrawPresenter implements WithdrawContract.Presenter {
    private WithdrawContract.View mView;

    public WithdrawPresenter(WithdrawContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getInfo(int flagint) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flagint);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void doWithdraw(String amount,String mymoney,String account,String person,String withdrawalsway) {

        if (TextUtils.isEmpty(amount)||TextUtils.isEmpty(account)||TextUtils.isEmpty(person)){
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.withdrawErrorWord),0);
            return;
        }

        if (Double.parseDouble(amount)>Double.parseDouble(mymoney)){
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.lackBalance),0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("amount",amount);
        httpParams.put("withdrawalsWay",withdrawalsway);
        httpParams.put("account",account);
        httpParams.put("person",person);
//        httpParams.put("bankName","");
//        httpParams.put("bankofDeposit","");
//        httpParams.put("phone","");

        RequestClient.postWithdrawal(httpParams, new ResponseListener<String>() {
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
