package com.yinglankeji.sillychild.loginregister.registerretrievepassword;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by ruitu on 2016/9/24.
 */
public class RegisterPresenter
      //  implements RegisterContract.Presenter
{

//    private RegisterContract.View mView;
//
//    public RegisterPresenter(RegisterContract.View view) {
//        mView = view;
//        mView.setPresenter(this);
//    }
//
//    @Override
//    public void postCode(String phone, String type) {
//        if (StringUtils.isEmpty(phone)) {
//            mView.error(MyApplication.getContext().getString(R.string.hintAccountText));
//            return;
//        }
//        if (phone.length() != 11 || !AccountValidatorUtil.isMobile(phone)) {
//            mView.error(KJActivityStack.create().topActivity().getString(R.string.inputPhone));
//            return;
//        }
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("mobile", phone);
//        String codeI = String.valueOf(System.currentTimeMillis());
//        String codeId = CipherUtils.md5(codeI.substring(2, codeI.length() - 1));
//        map.put("codeId", codeId);
//        String validationI = phone.substring(1, phone.length() - 1) + codeId.substring(3, codeId.length() - 1);
//        String validationId = CipherUtils.md5(validationI);
//        map.put("validationId", validationId);
//        map.put("opt", type);
//        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postCaptcha(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.error(msg);
//            }
//        });
//    }
//
//    @Override
//    public void postResetpwd(String phone, String code, String pwd) {
//        if (StringUtils.isEmpty(phone)) {
//            mView.error(MyApplication.getContext().getString(R.string.hintAccountText));
//            return;
//        }
//        if (phone.length() != 11 || !AccountValidatorUtil.isMobile(phone)) {
//            mView.error(KJActivityStack.create().topActivity().getString(R.string.inputPhone));
//            return;
//        }
//        if (StringUtils.isEmpty(code)) {
//            mView.error(MyApplication.getContext().getString(R.string.errorCode));
//            return;
//        }
//        if (StringUtils.isEmpty(pwd)) {
//            mView.error(MyApplication.getContext().getString(R.string.hintPasswordText));
//            return;
//        }
//        if (pwd.length() < 6 || pwd.length() > 20) {
//            mView.error(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1));
//            return;
//        }
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("account", phone);
//        map.put("captcha", code);
//        map.put("new_password", CipherUtils.md5("WUZAI" + pwd + "TIANXIA"));
//        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postResetpwd(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 2);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.error(msg);
//            }
//        });
//    }
//
//    @Override
//    public void postRegister(String phone, String code, String pwd, String recommendcode) {
//        if (StringUtils.isEmpty(phone)) {
//            mView.error(MyApplication.getContext().getString(R.string.hintAccountText));
//            return;
//        }
//        if (phone.length() != 11 || !AccountValidatorUtil.isMobile(phone)) {
//            mView.error(KJActivityStack.create().topActivity().getString(R.string.inputPhone));
//            return;
//        }
//        if (StringUtils.isEmpty(code)) {
//            mView.error(MyApplication.getContext().getString(R.string.errorCode));
//            return;
//        }
//        if (StringUtils.isEmpty(pwd)) {
//            mView.error(MyApplication.getContext().getString(R.string.hintPasswordText));
//            return;
//        }
//        if (pwd.length() < 6 || pwd.length() > 20) {
//            mView.error(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1));
//            return;
//        }
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("user_name", phone);
//        map.put("captcha", code);
//        map.put("password", CipherUtils.md5("WUZAI" + pwd + "TIANXIA"));
//        map.put("recomm_code", recommendcode);
//        map.put("pushToken", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
//        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postRegister(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.error(msg);
//            }
//        });
//    }
}
