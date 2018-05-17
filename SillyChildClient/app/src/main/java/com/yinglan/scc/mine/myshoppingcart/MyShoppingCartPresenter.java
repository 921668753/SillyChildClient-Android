package com.yinglan.scc.mine.myshoppingcart;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean;
import com.yinglan.scc.mine.myshoppingcart.dialog.DeleteGoodDialog;
import com.yinglan.scc.retrofit.RequestClient;
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean.DataBean.StorelistBean.GoodslistBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/15.
 */

public class MyShoppingCartPresenter implements MyShoppingCartContract.Presenter {


    private MyShoppingCartContract.View mView;

    public MyShoppingCartPresenter(MyShoppingCartContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 获取标记的id
     */
    private String getCartIdList(List<GoodslistBean> cartsList) {
        String cartsIdStr = "";
        for (int i = 0; i < cartsList.size(); i++) {
            if (cartsList.get(i).getIsSelected() == 1) {
                cartsIdStr = cartsIdStr + "," + cartsList.get(i).getId();
            }
        }
        if (StringUtils.isEmpty(cartsIdStr)) {
            return "";
        }
        cartsIdStr = cartsIdStr.substring(1);
        return cartsIdStr;
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

    /**
     * @param cartidsList 删除商品
     */
    @Override
    public void postDeleteGood(List<GoodslistBean> cartidsList) {
        String cartidsStr = getCartIdList(cartidsList);
        if (StringUtils.isEmpty(cartidsStr)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.delete2), 1);
            return;
        }
        DeleteGoodDialog deleteGoodDialog = new DeleteGoodDialog(KJActivityStack.create().topActivity(), KJActivityStack.create().topActivity().getString(R.string.confirmDeletionGoods));
        deleteGoodDialog.setDialogCallBack(new DeleteGoodDialog.DialogCallBack() {
            @Override
            public void confirm() {
                deleteGoodDialog.cancel();
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
                HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                httpParams.put("cartids", cartidsStr);
                RequestClient.postCartDelete(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.getSuccess(response, 1);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.errorMsg(msg, 1);
                    }
                });
            }
        });
        deleteGoodDialog.show();
    }

    /**
     * 更新商品数量
     */
    @Override
    public void postCartUpdate(int cartid, int num, int productid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("cartid", cartid);
        httpParams.put("num", num);
        httpParams.put("productid", productid);
        RequestClient.postCartUpdate(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }

}
