package com.yinglankeji.sillychild.loginregister;



import java.util.HashMap;
import java.util.Map;


/**
 * Created by ruitu on 2016/9/24.
 */

public class LoginPresenter
  //      implements LoginContract.Presenter
{
//    private LoginContract.View mView;
//
//    public LoginPresenter(LoginContract.View view) {
//        mView = view;
//        mView.setPresenter(this);
//    }
//
//    @Override
//    public void postToLogin(String phone, String pwd) {
//        if (StringUtils.isEmpty(phone)) {
//            mView.errorMsg(MyApplication.getContext().getString(R.string.hintAccountText), 0);
//            return;
//        }
//        if (phone.length() != 11 || !AccountValidatorUtil.isMobile(phone)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.inputPhone), 0);
//            return;
//        }
//        if (StringUtils.isEmpty(pwd)) {
//            mView.errorMsg(MyApplication.getContext().getString(R.string.hintPasswordText), 0);
//            return;
//        }
//        if (pwd.length() < 6 || pwd.length() > 20) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
//            return;
//        }
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("account", phone);
//        map.put("password", CipherUtils.md5("WUZAI" + pwd + "TIANXIA"));
//        map.put("wxOpenid", "");
//        map.put("pushToken", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
//        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postLogin(httpParams, new ResponseListener<String>() {
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
//    }
//
//    @Override
//    public void postThirdToLogin(String qq_openid, String wx_openid, String nickname, String avatar, int sex) {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (!StringUtils.isEmpty(qq_openid)) {
//            map.put("qq_openid", qq_openid);
//        }
//        if (!StringUtils.isEmpty(wx_openid)) {
//            map.put("wx_openid", wx_openid);
//        }
//        map.put("nickname", nickname);
//        map.put("avatar", avatar);
//        map.put("sex", sex);
//        map.put("pushToken", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
//        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postThirdLogin(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
//    }

}
