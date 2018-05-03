package com.ruitukeji.scc.mine.setup;

import android.app.Activity;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.rxvolley.client.ProgressListener;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.loginregister.LoginActivity;
import com.ruitukeji.scc.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/11.
 */

public class SetUpPresenter implements SetUpContract.Presenter {

    private SetUpContract.View mView;

    public SetUpPresenter(SetUpContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getUpdateApp() {


    }

    /**
     * @param updateAppUrl 下载app
     */
    @Override
    public void downloadApp(String updateAppUrl) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download));
        ProgressListener progressListener = new ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
                String size = MathUtil.keepZero(((double) transferredBytes) * 100 / totalSize) + "%";
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download) + size);
            }
        };
        RequestClient.downloadApp(updateAppUrl, progressListener, new ResponseListener<String>() {
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
    public void logOutHuanXin(Activity activity) {
        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面
            ChatClient.getInstance().logout(true, new Callback() {
                @Override
                public void onSuccess() {
                    /**
                     * 退出app退出登录
                     */
                    logOutAppService(activity);
                }

                @Override
                public void onError(int i, String s) {
                    logOutHuanXin(activity);
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
            return;
        }
        logOutAppService(activity);
    }

    /**
     * 退出app登录
     */

    private void logOutAppService(Activity activity) {
        PreferenceHelper.write(activity, StringConstants.FILENAME, "userId", 0);
        PreferenceHelper.write(activity, StringConstants.FILENAME, "accessToken", "");
        PreferenceHelper.write(activity, StringConstants.FILENAME, "expireTime", "0");
        PreferenceHelper.write(activity, StringConstants.FILENAME, "timeBefore", "0");
        PreferenceHelper.write(activity, StringConstants.FILENAME, "accountNumber", "");
        PreferenceHelper.write(activity, StringConstants.FILENAME, "isRefreshInfo", false);
        PreferenceHelper.write(activity, StringConstants.FILENAME, "isReLogin", true);
        mView.getSuccess("", 1);
    }

}
