package com.yinglan.scc.loginregister.register;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.R;
import com.yinglan.scc.retrofit.RequestClient;
import com.yinglan.scc.utils.AccountValidatorUtil;


import cn.jpush.android.api.JPushInterface;

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
    public void postCode(String phone, String countroy_code, String opt) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
            return;
        }
        if (phone.length() < 5) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }
        if (countroy_code.equals("86") && phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        // Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("mobile", phone);
        httpParams.put("countroy_code", countroy_code);
        String codeI = String.valueOf(System.currentTimeMillis());
        String codeId = CipherUtils.md5(codeI.substring(2, codeI.length() - 1));
        httpParams.put("codeId", codeId);
        String validationI = phone.substring(1, phone.length() - 1) + codeId.substring(3, codeId.length() - 1);
        String validationId = CipherUtils.md5(validationI);
        httpParams.put("validationId", validationId);
        httpParams.put("opt", opt);
        // httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postCaptcha(httpParams, new ResponseListener<String>() {
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
    public void postRegister(String phone, String type, String countroy_code, String code, String pwd, String pwd1, String recommendcode) {
        if (type.equals("phone")) {
            if (StringUtils.isEmpty(phone)) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
                return;
            }
            if (phone.length() < 5) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
                return;
            }
            if (countroy_code.equals("86") && phone.length() != 11) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
                return;
            }
        } else {
            if (StringUtils.isEmpty(phone)) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText), 0);
                return;
            }
            if (phone.length() < 5) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
                return;
            }
            if (!AccountValidatorUtil.isEmail(phone)) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText), 0);
                return;
            }
        }
        if (StringUtils.isEmpty(code)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.errorCode), 0);
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 0);
            return;
        }
        if (StringUtils.isEmpty(pwd1)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText3), 0);
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 20) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
            return;
        }
        if (!pwd.equals(pwd1)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.passwordMismatch), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //  Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("username", phone);
        httpParams.put("type", type);
        httpParams.put("code", code);
        httpParams.put("apply_code", recommendcode);
        httpParams.put("password", CipherUtils.md5("TPSHOP" + pwd));
        httpParams.put("countroy_code", countroy_code);
        httpParams.put("push_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        //  httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postRegister(httpParams, new ResponseListener<String>() {
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
    public void loginHuanXin(String phone, String pwd) {

        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面

            ChatClient.getInstance().logout(true, new Callback() {
                @Override
                public void onSuccess() {
                    loginHuanXin1(phone, pwd);
                }

                @Override
                public void onError(int i, String s) {
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr), 2);
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
            return;
        }
        loginHuanXin1(phone, pwd);
    }


    public void loginHuanXin1(String phone, String pwd) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr), 0);
            return;
        }
        ChatClient.getInstance().login(phone, pwd, new Callback() {
            @Override
            public void onSuccess() {
                mView.getSuccess("", 2);
            }

            @Override
            public void onError(int code, String error) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr), 2);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }
}