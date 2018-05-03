package com.ruitukeji.scc.mine.personaldata.setsignature;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SetSignaturePresenter implements SetSignatureContract.Presenter {
    private SetSignatureContract.View mView;

    public SetSignaturePresenter(SetSignatureContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void setupInfo(String personalized_signature) {
        if (TextUtils.isEmpty(personalized_signature)){
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.fillSignature),0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("personalized_signature",personalized_signature);
        RequestClient.putInfo(httpParams, new ResponseListener<String>() {
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
