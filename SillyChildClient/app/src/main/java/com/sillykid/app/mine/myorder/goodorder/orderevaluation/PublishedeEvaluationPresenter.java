package com.sillykid.app.mine.myorder.goodorder.orderevaluation;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.nanchen.compresshelper.FileUtil;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.goodorder.orderevaluation.PublishedeEvaluationBean.DataBean.CommentVoBean;
import com.sillykid.app.retrofit.RequestClient;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public class PublishedeEvaluationPresenter implements PublishedeEvaluationContract.Presenter {
    private PublishedeEvaluationContract.View mView;

    public PublishedeEvaluationPresenter(PublishedeEvaluationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getOrderDetails(String orderId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderid", orderId);
        RequestClient.getOrderDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void upPictures(String imgPath) {
        if (StringUtils.isEmpty(imgPath)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 0);
            return;
        }
        File oldFile = new File(imgPath);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.imagePathError), 0);
            return;
        }
        long fileSize = 0;
        try {
            fileSize = DataCleanManager.getFileSize(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            fileSize = 0;
        }
        if (fileSize >= StringConstants.COMPRESSION_SIZE) {
            oldFile = BitmapCoreUtil.customCompression(oldFile);
        }
        RequestClient.upLoadImg(KJActivityStack.create().topActivity(), oldFile, 0, new ResponseListener<String>() {
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



    @Override
    public void postCommentCreate(CommentVoBean commentVoBean, int store_desccredit, int store_servicecredit, int store_deliverycredit) {
        if (store_desccredit <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectDescriptionMatchingScore), 0);
            return;
        }
        if (store_servicecredit <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectLogisticsServiceRating), 0);
            return;
        }
        if (store_deliverycredit <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectServiceAttitudeScore), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("commentVo", JsonUtil.obj2JsonString(commentVoBean));
        httpParams.put("store_desccredit", store_desccredit);
        httpParams.put("store_servicecredit", store_servicecredit);
        httpParams.put("store_deliverycredit", store_deliverycredit);
        RequestClient.postCommentCreate(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
