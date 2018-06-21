package com.sillykid.app.homepage.goodslist.goodsdetails;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.retrofit.RequestClient;

/**
 * Created by ruitu on 2017/9/24.
 */

public class GoodsDetailsPresenter implements GoodsDetailsContract.Presenter {
    private GoodsDetailsContract.View mView;

    public GoodsDetailsPresenter(GoodsDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodDetail(int goodsid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        RequestClient.getGoodDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postFavoriteAdd(int goodsid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        RequestClient.postFavoriteAdd(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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

    @Override
    public void postUnfavorite(int goodsid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsid", goodsid);
        RequestClient.postUnfavorite(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void postOrderBuyNow(int goodId, int num, int product_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("productid", product_id);
        httpParams.put("num", num);
        RequestClient.postOrderBuyNow(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 3);
            }
        });
    }

    @Override
    public void postAddCartGood(int goodsid, int num, int product_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("productid", product_id);
        httpParams.put("num", num);
        RequestClient.postAddCartGood(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 4);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 4);
            }
        });
    }

    @Override
    public void getIsLogin(Context context, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getIsLogin(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

}
