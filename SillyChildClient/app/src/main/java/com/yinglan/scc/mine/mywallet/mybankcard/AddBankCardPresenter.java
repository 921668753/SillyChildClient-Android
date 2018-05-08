package com.yinglan.scc.mine.mywallet.mybankcard;

import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scc.R;
import com.yinglan.scc.application.MyApplication;
import com.yinglan.scc.retrofit.RequestClient;
import com.yinglan.scc.utils.AccountValidatorUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/17.
 */

public class AddBankCardPresenter implements AddBankCardContract.Presenter {

    private AddBankCardContract.View mView;

    public AddBankCardPresenter(AddBankCardContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String phone, String type) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.hintAccountText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.inputPhone), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", phone);
        String codeI = String.valueOf(System.currentTimeMillis());
        String codeId = CipherUtils.md5(codeI.substring(2, codeI.length() - 1));
        map.put("codeId", codeId);
        String validationI = phone.substring(1, phone.length() - 1) + codeId.substring(3, codeId.length() - 1);
        String validationId = CipherUtils.md5(validationI);
        map.put("validationId", validationId);
        map.put("opt", type);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
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
    public void getBank() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getBank(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }


    @Override
    public void postAddBankCard(String cardholder, String bankCardNumber, int bank_id, String openingBank, String phone, String verificationCode) {
        if (StringUtils.isEmpty(cardholder)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.accountHolderName1), 0);
            return;
        }
        String all = "^[A-Za-z\\u4e00-\\u9fa5]{2,10}";//{2,10}表示字符的长度是2-10
        Pattern pattern = Pattern.compile(all);
        boolean tf = Pattern.matches(all, cardholder);
        if (!tf) {
           mView.errorMsg(MyApplication.getContext().getString(R.string.hintName1), 0);
            return;
        }
        if (StringUtils.isEmpty(bankCardNumber)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.bankCardNumber1), 0);
            return;
        }
        if (bank_id < 1) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.withdrawalsBank1), 0);
            return;
        }
        if (StringUtils.isEmpty(openingBank)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.openingBank1), 0);
            return;
        }
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.accountText), 0);
            return;
        }
        if (phone.length() != 11 || !AccountValidatorUtil.isMobile(phone)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.inputPhone), 0);
            return;
        }
        if (StringUtils.isEmpty(verificationCode)) {
            mView.errorMsg(MyApplication.getContext().getString(R.string.errorCode), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("card_holder_man", cardholder);
        map.put("bank_card", bankCardNumber);
        map.put("with_drawal_bank_id", bank_id);
        map.put("bank", openingBank);
        map.put("tel", phone);
        map.put("captcha", verificationCode);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
//        RequestClient.postAddBankCard(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 2);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }
}
