package com.yinglan.scc.mine.myshoppingcart.makesureorder;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.R;
import com.yinglan.scc.retrofit.RequestClient;

/**
 * Created by Administrator on 2018/5/15.
 */

public class PaymentOrderPresenter implements PaymentOrderContract.Presenter {


    private PaymentOrderContract.View mView;

    public PaymentOrderPresenter(PaymentOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 获取购物车列表
     */
    @Override
    public void getOnlinePay(String order_id, String pay_type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_id", order_id);
        httpParams.put("order_type", "DD");
        httpParams.put("pay_type", pay_type);
        RequestClient.getOnlinePay(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
