package com.ruitukeji.scc.main;

import com.common.cklibrary.common.KJActivity;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.dialog.VIPPermissionsDialog;
import com.ruitukeji.scc.entity.UserInfoBean;
import com.ruitukeji.scc.mine.vipemergencycall.VipEmergencyCallActivity;
import com.ruitukeji.scc.retrofit.RequestClient;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MinePresenter implements MineContract.Presenter {
    private MineContract.View mView;

    public MinePresenter(MineContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getInfo() {
        HttpParams httpParams = new HttpParams();
        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
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
