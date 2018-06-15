package com.sillykid.app.loginregister.register;

import android.net.Uri;
import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.sillykid.app.R;
import com.sillykid.app.entity.loginregister.LoginBean;
import com.sillykid.app.message.interactivemessage.imuitl.UserUtil;
import com.sillykid.app.retrofit.RequestClient;
import com.sillykid.app.utils.AccountValidatorUtil;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * Created by ruitu on 2017/8/24.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mView;

    public RegisterPresenter(RegisterContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String phone, String opt) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("mobile", phone);
        RequestClient.postCaptcha(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postMailCaptcha(String mail, String postCode) {
        if (StringUtils.isEmpty(mail)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText), 0);
            return;
        }
        if (mail.length() < 5) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
            return;
        }
        // String regex = "^[A-Za-z]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";
        if (!AccountValidatorUtil.isEmail(mail)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("mail", mail);
        httpParams.put("opt", postCode);
        RequestClient.postEmailCaptcha(httpParams, new ResponseListener<String>() {
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
    public void postRegister(String phone, String code, String pwd) {
//        if (type.equals("phone")) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }
//        } else {
//            if (StringUtils.isEmpty(phone)) {
//                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText), 0);
//                return;
//            }
//            if (phone.length() < 5) {
//                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
//                return;
//            }
//            if (!AccountValidatorUtil.isEmail(phone)) {
//                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText), 0);
//                return;
//            }
        //  }
        if (StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 0);
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 20) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
            return;
        }
        if (StringUtils.isEmpty(code)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.errorCode), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //  Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("phone", phone);
        httpParams.put("code", code);
        httpParams.put("password", pwd);
        httpParams.put("registration_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        //  httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postRegister(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void loginRongYun(LoginBean bean) {
        RongIM.getInstance().logout();
        if (!StringUtils.isEmpty(bean.getData().getRong_cloud())) {
            RongIM.connect(bean.getData().getRong_cloud(), new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 *
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.i("XJ", "application--RongIM.connect--onSuccess" + userid);
                    /**
                     * 获取用户信息
                     */
                    UserUtil.saveRcTokenId(KJActivityStack.create().topActivity(), bean.getData().getRong_cloud(), userid);
                    if (RongIM.getInstance() != null && bean.getData() != null && !StringUtils.isEmpty(bean.getData().getUsername())) {
                        UserInfo userInfo = new UserInfo(userid, bean.getData().getUsername(), Uri.parse(bean.getData().getFace()));
                        RongIM.getInstance().setCurrentUserInfo(userInfo);
                        RongIM.getInstance().setMessageAttachedUserInfo(true);
                        mView.getSuccess("", 2);
                        return;
                    }
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
                }

                /**
                 * 连接融云失败
                 *
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.i("XJ", "--errorCode" + errorCode);
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
                }
            });
        }
    }
}
