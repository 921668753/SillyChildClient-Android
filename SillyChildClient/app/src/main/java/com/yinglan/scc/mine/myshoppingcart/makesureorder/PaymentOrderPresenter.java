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
    public void getMyShoppingCartList() {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCartList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
