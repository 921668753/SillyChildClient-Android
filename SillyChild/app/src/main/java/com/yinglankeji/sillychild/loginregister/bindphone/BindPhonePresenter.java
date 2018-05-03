package com.yinglankeji.sillychild.loginregister.bindphone;


import java.util.HashMap;
import java.util.Map;


/**
 * Created by ruitu on 2016/9/24.
 */
public class BindPhonePresenter
      //  implements BindPhoneContract.Presenter
{

//    private BindPhoneContract.View mView;
//
//    public BindPhonePresenter(BindPhoneContract.View view) {
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
////    @Override
////    public void postBindPhone(String phone, String code) {
////        if (StringUtils.isEmpty(phone)) {
////            mView.error(MyApplication.getContext().getString(R.string.hintAccountText));
////            return;
////        }
////        if (phone.length() != 11) {
////            mView.error(MyApplication.getContext().getString(R.string.inputPhone));
////            return;
////        }
////        if (StringUtils.isEmpty(code)) {
////            mView.error(MyApplication.getContext().getString(R.string.errorCode));
////            return;
////        }
////        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
////        Map<String, Object> map = new HashMap<String, Object>();
////        map.put("account", phone);
////        map.put("captcha", code);
////        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
////        RequestClient.postResetpwd(httpParams, new ResponseListener<String>() {
////            @Override
////            public void onSuccess(String response) {
////                mView.getSuccess(response, 1);
////            }
////
////            @Override
////            public void onFailure(String msg) {
////                mView.error(msg);
////            }
////        });
////    }
//
//    @Override
//    public void postThirdLoginAdd(String openid, String from, String nickname, String avatar, int sex, String captcha, String tel, String recomm_code) {
//        if (StringUtils.isEmpty(tel)) {
//            mView.error(MyApplication.getContext().getString(R.string.hintAccountText));
//            return;
//        }
//        if (tel.length() != 11 || !AccountValidatorUtil.isMobile(tel)) {
//            mView.error(KJActivityStack.create().topActivity().getString(R.string.inputPhone));
//            return;
//        }
//        if (StringUtils.isEmpty(captcha)) {
//            mView.error(MyApplication.getContext().getString(R.string.errorCode));
//            return;
//        }
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (from != null && from.equals("WEIXIN")) {
//            map.put("wx_openid", openid);
//        } else {
//            map.put("qq_openid", openid);
//        }
//        map.put("nickname", nickname);
//        map.put("avatar", avatar);
//        map.put("sex", sex);
//        map.put("captcha", captcha);
//        map.put("tel", tel);
//        map.put("recomm_code", recomm_code);
//        map.put("pushToken", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
//        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postThirdLoginAdd(httpParams, new ResponseListener<String>() {
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
