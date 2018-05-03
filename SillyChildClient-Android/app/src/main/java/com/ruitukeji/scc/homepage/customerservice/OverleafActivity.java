package com.ruitukeji.scc.homepage.customerservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.Conversation;
import com.hyphenate.helpdesk.Error;
import com.hyphenate.helpdesk.callback.Callback;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.hyphenate.helpdesk.model.QueueIdentityInfo;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.scc.BuildConfig;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.loginregister.LoginActivity;
import com.ruitukeji.scc.utils.activity.HXBaseActivity;

import java.util.Locale;
import java.util.Random;


/**
 * 环信注册登录过渡页
 * Created by Admin on 2017/9/13.
 */

public class OverleafActivity extends HXBaseActivity {

    private static final String TAG = "OverleafActivity";

    private boolean progressShow;

    private int messageToIndex = 0;
    private Intent intent;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        intent = getIntent();
        // 可以检测是否已经登录过环信，如果登录过则环信SDK会自动登录，不需要再次调用登录操作
        if (ChatClient.getInstance().isLoggedInBefore()) {
            showLoadingDialog(getString(R.string.dataLoad));
            toChatActivity();
            return;
        }
        //随机创建一个用户并登录环信服务器
        PreferenceHelper.write(this, StringConstants.FILENAME, "userId", 0);
        PreferenceHelper.write(this, StringConstants.FILENAME, "accessToken", "");
        PreferenceHelper.write(this, StringConstants.FILENAME, "expireTime", "0");
        PreferenceHelper.write(this, StringConstants.FILENAME, "timeBefore", "0");
        PreferenceHelper.write(this, StringConstants.FILENAME, "accountNumber", "");
        Intent intent = new Intent(this, LoginActivity.class);
        // intent.putExtra("name", "GetOrderFragment");
        startActivity(intent);
        finish();
        //   createRandomAccountThenLoginChatServer();
    }


    private void createRandomAccountThenLoginChatServer() {
        // 自动生成账号,此处每次都随机生成一个账号,为了演示.正式应从自己服务器获取账号
        String account = PreferenceHelper.readString(this, StringConstants.FILENAME, "accountNumber", getRandomAccount());
        if (StringUtils.isEmpty(account)) {
            account = getRandomAccount();
        }
        String userPwd = "123456";
        showLoadingDialog(getString(R.string.system_is_regist));
        String finalAccount = account;
        ChatClient.getInstance().createAccount(account, userPwd, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "demo register success");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //登录环信服务器
                        PreferenceHelper.write(OverleafActivity.this, StringConstants.FILENAME, "accountNumber", finalAccount);
                        login(finalAccount, userPwd);
                    }
                });
            }

            @Override
            public void onError(final int errorCode, String error) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        dismissLoadingDialog();
                        if (errorCode == Error.NETWORK_ERROR) {
                            ViewInject.toast(getString(R.string.network_unavailable));
                        } else if (errorCode == Error.USER_ALREADY_EXIST) {
                            login(finalAccount, "123456");
                            // ViewInject.to ast(getString(R.string.user_already_exists));
                        } else if (errorCode == Error.USER_AUTHENTICATION_FAILED) {
                            ViewInject.toast(getString(R.string.no_register_authority));
                        } else if (errorCode == Error.USER_ILLEGAL_ARGUMENT) {
                            ViewInject.toast(getString(R.string.illegal_user_name));
                        } else {
                            login(finalAccount, "123456");
                            ViewInject.toast(getString(R.string.register_user_fail));
                        }
                        finish();
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    private void login(final String uname, final String upwd) {
        progressShow = true;
        showLoadingDialog(getString(R.string.is_contact_customer));
        // login huanxin server
        ChatClient.getInstance().login(uname, upwd, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "demo login success!");
                if (!progressShow) {
                    return;
                }
                toChatActivity();
            }

            @Override
            public void onError(int code, String error) {
                Log.e(TAG, "login fail,code:" + code + ",error:" + error);
                if (!progressShow) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        dismissLoadingDialog();
                        ViewInject.toast(getString(R.string.is_contact_customer_failure_seconed));
                        finish();
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    private void toChatActivity() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!OverleafActivity.this.isFinishing())
                    //  progressDialog.dismiss();
                    dismissLoadingDialog();
                //此处演示设置技能组,如果后台设置的技能组名称为[shouqian|shouhou],这样指定即分配到技能组中.
                //为null则不按照技能组分配,同理可以设置直接指定客服scheduleAgent
                String queueName = null;
                switch (messageToIndex) {
                    case 2:
                        queueName = "shouhou";
                        break;
                    case 1:
                        queueName = "shouqian";
                        break;
                    default:
                        break;
                }
                Bundle bundle = new Bundle();
                if (!StringUtils.isEmpty(intent.getStringExtra("chatMessage"))) {
                    bundle.putInt("img_selected", 1);
                }
                //设置点击通知栏跳转事件
                Conversation conversation = ChatClient.getInstance().chatManager().getConversation(BuildConfig.HUANXIN_IM);
                String titleName = null;
                if (conversation.getOfficialAccount() != null) {
                    titleName = conversation.getOfficialAccount().getName();
                }
                // 进入主页面
                String email = PreferenceHelper.readString(OverleafActivity.this, StringConstants.FILENAME, "email", getString(R.string.notAvailable));
                String mobile = PreferenceHelper.readString(OverleafActivity.this, StringConstants.FILENAME, "mobile", getString(R.string.notAvailable));
                String nickname = PreferenceHelper.readString(OverleafActivity.this, StringConstants.FILENAME, "nickname", getString(R.string.visitors));
                Intent intent1 = new IntentBuilder(OverleafActivity.this)
                        .setTargetClass(CustomerServiceActivity.class)
                        .setVisitorInfo(ContentFactory.createVisitorInfo(null)
                                .nickName(nickname)
                                .name(nickname)
                                .phone(mobile)
                                .email(email))
                        .setServiceIMNumber(BuildConfig.HUANXIN_IM)
                        .setScheduleQueue(createQueueIdentity(queueName))
                        .setTitleName(titleName)
                        .setShowUserNick(true)
                        .setBundle(bundle)
                        .build();
                if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("0")) {
                    initAirport(intent1);
                } else if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("1")) {
                    initAirport(intent1);
                } else if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("2")) {
                    initSingleTransport(intent1);
                } else if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("3")) {
                    initPersonalTailor(intent1);
                } else if (intent.getStringExtra("chatMessage") != null && intent.getStringExtra("chatMessage").equals("4")) {
                    initByTheDay(intent1);
                }
                startActivity(intent1);
                finish();
            }
        });
    }

    /**
     * 单次接送
     */
    private void initSingleTransport(Intent intent1) {
        intent1.putExtra("vehicleTime", intent.getStringExtra("vehicleTime"));
        intent1.putExtra("fewAdults", intent.getStringExtra("fewAdults"));
        intent1.putExtra("severalChildren", intent.getStringExtra("severalChildren"));
        intent1.putExtra("selectModels", intent.getStringExtra("selectModels"));
        intent1.putExtra("whereDoYouStart", intent.getStringExtra("whereDoYouStart"));
        intent1.putExtra("whereAreGoing", intent.getStringExtra("whereAreGoing"));
        intent1.putExtra("workNumber", intent.getStringExtra("workNumber"));
        intent1.putExtra("twenty_four", intent.getStringExtra("twenty_four"));
        intent1.putExtra("twenty_six", intent.getStringExtra("twenty_six"));
        intent1.putExtra("twenty_eight", intent.getStringExtra("twenty_eight"));
        intent1.putExtra("thirty", intent.getStringExtra("thirty"));
        intent1.putExtra("name", intent.getStringExtra("name"));
        intent1.putExtra("id", intent.getStringExtra("id"));
        intent1.putExtra("contactWay", intent.getStringExtra("contactWay"));
        intent1.putExtra("remark", intent.getStringExtra("remark"));
        intent1.putExtra("chatMessage", intent.getStringExtra("chatMessage"));
    }

    /**
     * 私人订制
     */
    private void initPersonalTailor(Intent intent1) {
        intent1.putExtra("travelTime", intent.getStringExtra("travelTime"));
        intent1.putExtra("origin", intent.getStringExtra("origin"));
        intent1.putExtra("destination", intent.getStringExtra("destination"));
        intent1.putExtra("playNumberDays", intent.getStringExtra("playNumberDays"));

        intent1.putExtra("fewAdults", intent.getStringExtra("fewAdults"));
        intent1.putExtra("severalChildren", intent.getStringExtra("severalChildren"));

        intent1.putExtra("travelPreferences", intent.getStringExtra("travelPreferences"));
        intent1.putExtra("recommendRestaurant", intent.getStringExtra("recommendRestaurant"));
        intent1.putExtra("recommendedAccommodation", intent.getStringExtra("recommendedAccommodation"));
//        intent1.putExtra("twenty_four", intent.getStringExtra("twenty_four"));
//        intent1.putExtra("twenty_six", intent.getStringExtra("twenty_six"));
//        intent1.putExtra("twenty_eight", intent.getStringExtra("twenty_eight"));
//        intent1.putExtra("thirty", intent.getStringExtra("thirty"));
//        intent1.putExtra("name", intent.getStringExtra("name"));
        intent1.putExtra("id", intent.getStringExtra("id"));
//        intent1.putExtra("workNumber", intent.getStringExtra("workNumber"));
//        intent1.putExtra("contactWay", intent.getStringExtra("contactWay"));
//        intent1.putExtra("remark", intent.getStringExtra("remark"));
        intent1.putExtra("chatMessage", intent.getStringExtra("chatMessage"));
    }

    /**
     * 按天包车游
     */
    private void initByTheDay(Intent intent1) {
        intent1.putExtra("origin", intent.getStringExtra("origin"));
        intent1.putExtra("destination", intent.getStringExtra("destination"));
        intent1.putExtra("selectStartEndDateCar", intent.getStringExtra("selectStartEndDateCar"));
        intent1.putExtra("selectModels", intent.getStringExtra("selectModels"));
        intent1.putExtra("workNumber", intent.getStringExtra("workNumber"));
        intent1.putExtra("adultNum", intent.getStringExtra("adultNum"));
        intent1.putExtra("childrenNum", intent.getStringExtra("childrenNum"));
        intent1.putExtra("name", intent.getStringExtra("name"));
        intent1.putExtra("twenty_four", intent.getStringExtra("twenty_four"));
        intent1.putExtra("twenty_six", intent.getStringExtra("twenty_six"));
        intent1.putExtra("twenty_eight", intent.getStringExtra("twenty_eight"));
        intent1.putExtra("thirty", intent.getStringExtra("thirty"));
        intent1.putExtra("contactWay", intent.getStringExtra("contactWay"));
        intent1.putExtra("remark", intent.getStringExtra("remark"));
        intent1.putExtra("id", intent.getStringExtra("id"));
        intent1.putExtra("chatMessage", intent.getStringExtra("chatMessage"));
    }

    /**
     * 接送机
     */
    private void initAirport(Intent intent1) {
        intent1.putExtra("flightNumber", intent.getStringExtra("flightNumber"));
        intent1.putExtra("pickUpAtAirport", intent.getStringExtra("pickUpAtAirport"));
        intent1.putExtra("deliveredSite", intent.getStringExtra("deliveredSite"));
        intent1.putExtra("selectModels", intent.getStringExtra("selectModels"));
        intent1.putExtra("workNumber", intent.getStringExtra("workNumber"));
        intent1.putExtra("name", intent.getStringExtra("name"));
        intent1.putExtra("id", intent.getStringExtra("id"));
        intent1.putExtra("twenty_four", intent.getStringExtra("twenty_four"));
        intent1.putExtra("twenty_six", intent.getStringExtra("twenty_six"));
        intent1.putExtra("twenty_eight", intent.getStringExtra("twenty_eight"));
        intent1.putExtra("thirty", intent.getStringExtra("thirty"));
        intent1.putExtra("id", intent.getStringExtra("id"));
        intent1.putExtra("contactWay", intent.getStringExtra("contactWay"));
        intent1.putExtra("fewAdults", intent.getStringExtra("fewAdults"));
        intent1.putExtra("severalChildren", intent.getStringExtra("severalChildren"));
        intent1.putExtra("departureTime", intent.getStringExtra("departureTime"));
        intent1.putExtra("remark", intent.getStringExtra("remark"));
        intent1.putExtra("chatMessage", intent.getStringExtra("chatMessage"));
    }


    public QueueIdentityInfo createQueueIdentity(String queueName) {
        if (TextUtils.isEmpty(queueName)) {
            return null;
        }
        QueueIdentityInfo info = ContentFactory.createQueueIdentityInfo(null);
        info.queueName(queueName);
        return info;
    }


//    public VisitorInfo createVisitorInfo() {
//             VisitorInfo info = ContentFactory.createVisitorInfo(null);
//        info.nickName(nickname)
//                .name(nickname)
//                .phone(mobile)
//                .email(email);
//        return info;
//    }


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
