package com.yinglan.scc.mine.personaldata.setsex;

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

public class SetSexPresenter implements SetSexContract.Presenter {
    private SetSexContract.View mView;

    public SetSexPresenter(SetSexContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void setSex(int sex) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("sex", sex);
        RequestClient.postSaveInfo(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
