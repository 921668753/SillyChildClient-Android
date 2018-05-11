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
import com.yinglan.scc.entity.mine.myshoppingcart.MyShoppingCartBean.ResultBean.ListBean;

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
    private String getMsgIdList(List<ListBean> masageList) {
        String msgIdStr = "";
        for (int i = 0; i < masageList.size(); i++) {
            if (masageList.get(i).getIsSelected() == 1) {
                msgIdStr = msgIdStr + "," + masageList.get(i).getId();
            }
        }
        if (StringUtils.isEmpty(msgIdStr)) {
            return "";
        }
        msgIdStr = msgIdStr.substring(1);
        return msgIdStr;
    }

    /**
     * 获取购物车列表
     *
     * @param page 页码
     */
    @Override
    public void getMyShoppingCartList(String type, int page) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("push_type", type);
        httpParams.put("page", page);
        httpParams.put("pageSize", 20);
//        RequestClient.getMessage(httpParams, new ResponseListener<String>() {
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

    /**
     * @param masageList 删除商品
     */
    @Override
    public void postDeleteGood(List<ListBean> masageList) {
        String msgStr = getMsgIdList(masageList);
        if (StringUtils.isEmpty(msgStr)) {
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
                Map map = new HashMap();
                map.put("msg_id", msgStr);
                httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//                RequestClient.postDeleteMessage(httpParams, new ResponseListener<String>() {
//                    @Override
//                    public void onSuccess(String response) {
//                        mView.getSuccess(response, 1);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        mView.errorMsg(msg, 1);
//                    }
//                });
            }
        });
        deleteGoodDialog.show();
    }

    /**
     * 减少商品
     */
    @Override
    public void postReduceGood(int id) {
//                RequestClient.postDeleteMessage(httpParams, new ResponseListener<String>() {
//                    @Override
//                    public void onSuccess(String response) {
//                        mView.getSuccess(response, 2);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        mView.errorMsg(msg, 1);
//                    }
//                });
    }

    /**
     * 增加商品
     */
    @Override
    public void postAddGood(int id) {
//                RequestClient.postDeleteMessage(httpParams, new ResponseListener<String>() {
//                    @Override
//                    public void onSuccess(String response) {
//                        mView.getSuccess(response, 3);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        mView.errorMsg(msg, 1);
//                    }
//                });
    }
}
