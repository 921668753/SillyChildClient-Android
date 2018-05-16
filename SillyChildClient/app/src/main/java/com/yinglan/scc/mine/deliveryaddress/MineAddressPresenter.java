package com.yinglan.scc.mine.deliveryaddress;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.retrofit.RequestClient;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MineAddressPresenter implements MineAddressContract.Presenter {
    private MineAddressContract.View mView;

    public MineAddressPresenter(MineAddressContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void allAddress() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.upLoadImg(httpParams,0, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

    @Override
    public void deleteAddress(String paramname, String voule, int resultsource) {

    }

    @Override
    public void addAddress(String paramname, File voule, int resultsource) {

    }

    @Override
    public void defaultAddress(String paramname, File voule, int resultsource) {

    }
}
