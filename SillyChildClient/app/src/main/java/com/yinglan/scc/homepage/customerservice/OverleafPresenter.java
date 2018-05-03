package com.yinglan.scc.homepage.customerservice;


import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.Error;
import com.hyphenate.helpdesk.callback.Callback;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;

import java.util.Locale;
import java.util.Random;

import static com.yinglan.scc.constant.StringNewConstants.HuanXin_PED;

/**
 * Created by ruitu on 2016/9/24.
 */

public class OverleafPresenter implements OverleafContract.Presenter {
    private OverleafContract.View mView;

    public OverleafPresenter(OverleafContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void createRandomAccountThenLoginChatServer() {
        // 自动生成账号,此处每次都随机生成一个账号,为了演示.正式应从自己服务器获取账号
        String accountNumber = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accountNumber", getRandomAccount());
        if (StringUtils.isEmpty(accountNumber)) {
            accountNumber = getRandomAccount();
        }
        String finalAccountNumber = accountNumber;
        ChatClient.getInstance().createAccount(accountNumber, HuanXin_PED, new Callback() {
            @Override
            public void onSuccess() {
                mView.getSuccess(finalAccountNumber, 0);
            }

            @Override
            public void onError(final int errorCode, String error) {
                if (errorCode == Error.USER_ALREADY_EXIST) {
                    mView.errorMsg(finalAccountNumber, errorCode);
                } else {
                    mView.errorMsg(error, errorCode);
                }
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    @Override
    public void login(String uname, String upwd) {
        ChatClient.getInstance().login(uname, upwd, new Callback() {
            @Override
            public void onSuccess() {
                mView.getSuccess("", 1);
            }

            @Override
            public void onError(int code, String error) {
                mView.errorMsg("", 1);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }


    /**
     * demo为了演示功能，此处随机生成账号。
     *
     * @return
     */
    private String getRandomAccount() {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {// 字符串
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {// 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val.toLowerCase(Locale.getDefault());
    }
}
