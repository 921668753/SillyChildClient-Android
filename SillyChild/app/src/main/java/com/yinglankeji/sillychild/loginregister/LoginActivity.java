package com.yinglankeji.sillychild.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.Map;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

/**
 * 登录
 * Created by ruitu ck on 2016/9/14.
 */

public class LoginActivity
    //    extends BaseActivity implements LoginContract.View
{

//    /**
//     * 账号
//     */
//    @BindView(id = R.id.et_accountNumber)
//    private EditText et_accountNumber;
//    /**
//     * 密码
//     */
//    @BindView(id = R.id.et_pwd)
//    private EditText et_pwd;
//    /**
//     * 忘记密码
//     */
//    @BindView(id = R.id.tv_forgotPassword, click = true)
//    private TextView tv_forgotPassword;
//    /**
//     * 登录
//     */
//    @BindView(id = R.id.tv_login, click = true)
//    private TextView tv_login;
//    /**
//     * 注册
//     */
//    @BindView(id = R.id.tv_register, click = true)
//    private TextView tv_register;
//    /**
//     * 取消
//     */
//    @BindView(id = R.id.img_quxiao, click = true)
//    private ImageView img_quxiao;
//    @BindView(id = R.id.img_quxiao1, click = true)
//    private ImageView img_quxiao1;
//
//    /**
//     * 密码显示
//     */
//    @BindView(id = R.id.img_biyan, click = true)
//    private ImageView img_biyan;
//
//    /**
//     * 微信登陆
//     */
//    @BindView(id = R.id.ll_loginweixin, click = true)
//    private LinearLayout ll_loginweixin;
//
//    /**
//     * QQ登陆
//     */
//    @BindView(id = R.id.ll_loginQQ, click = true)
//    private LinearLayout ll_loginQQ;
//
//    private String openid;
//    private String from;
//    private String nickname;
//    private String head_pic;
//    private int sex = 0;
//    private String type;
//
//    @Override
//    public void setRootView() {
//        setContentView(R.layout.activity_login);
//    }
//
//    /**
//     * 初始化数据
//     */
//    @Override
//    public void initData() {
//        super.initData();
//        mPresenter = new LoginPresenter(this);
//        type = getIntent().getStringExtra("type");
//    }
//
//    /**
//     * 渲染view
//     */
//    @Override
//    public void initWidget() {
//        super.initWidget();
//        initTitle();
//        tv_login.setClickable(false);//不可点击
//        changeInputView(et_accountNumber, img_quxiao);
//        changeInputView(et_pwd, img_quxiao1);
//    }
//
//    /**
//     * 设置标题
//     */
//    public void initTitle() {
//        ActivityTitleUtils.initToolbar(aty, getString(R.string.login), true, R.id.titlebar);
//    }
//
//    /**
//     * view监听事件
//     *
//     * @param v
//     */
//    @Override
//    public void widgetClick(View v) {
//        super.widgetClick(v);
//
//        switch (v.getId()) {
//            case R.id.tv_login:
//                showLoadingDialog(MyApplication.getContext().getString(R.string.loggingLoad));
//                ((LoginContract.Presenter) mPresenter).postToLogin(et_accountNumber.getText().toString(), et_pwd.getText().toString());
//                break;
//            case R.id.img_quxiao:
//                et_accountNumber.setText("");
//                break;
//            case R.id.img_quxiao1:
//                et_pwd.setText("");
//                break;
//            case R.id.img_biyan:
//                if (et_pwd.getInputType() == 0x00000081) {
//                    img_biyan.setImageResource(R.mipmap.ic_zhengkai);
//                    et_pwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                } else {
//                    img_biyan.setImageResource(R.mipmap.ic_biyan);
//                    et_pwd.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
//                }
//                et_pwd.setSelection(et_pwd.getText().toString().trim().length());
//                et_pwd.requestFocus();
//                break;
//            case R.id.tv_forgotPassword:
//                Intent intent = new Intent();
//                intent.setClass(aty, RetrievePasswordActivity.class);
//                intent.putExtra("title", getString(R.string.retrievePassword));
//                showActivity(aty, intent);
//                break;
//            case R.id.tv_register:
//                showActivity(aty, RegisterActivity.class);
//                break;
//            case R.id.ll_loginweixin:
//                thirdLogin(SHARE_MEDIA.WEIXIN);
//                break;
//            case R.id.ll_loginQQ:
//                thirdLogin(SHARE_MEDIA.QQ);
//                break;
//            default:
//                break;
//        }
//    }
//
//
//    /**
//     * 微信QQ， 登录
//     * 第三方授权
//     */
//    private void thirdLogin(SHARE_MEDIA platform) {
//        UMShareConfig config = new UMShareConfig();
//        config.isNeedAuthOnGetUserInfo(true);
//        UMShareAPI.get(LoginActivity.this).setShareConfig(config);
//        UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, platform, umAuthListener);
//    }
//
//
//    UMAuthListener umAuthListener = new UMAuthListener() {
//        /**
//         * @desc 授权开始的回调
//         * @param platform 平台名称
//         */
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//            showLoadingDialog(getString(R.string.authorizationLoad));
//        }
//
//        /**
//         * @desc 授权成功的回调
//         * @param share_media 平台名称
//         * @param action 行为序号，开发者用不上
//         * @param map 用户资料返回
//         */
//        @Override
//        public void onComplete(SHARE_MEDIA share_media, int action, Map<String, String> map) {
//            showLoadingDialog(getString(R.string.loggingLoad));
//            String temp = "";
//            //    Toast.makeText(LoginActivity.this, new Gson().toJson(map), Toast.LENGTH_LONG).show(); ouCjs1GFLA4yOb44uCKwIb9-d6Cw
//            for (String key : map.keySet()) {
//                temp = temp + key + " : " + map.get(key) + "\n";
//            }
//            Log.d("tag111", temp);
//            if (map.get("gender") != null && map.get("gender").contains(getString(R.string.nan))) {
//                sex = 1;
//            } else if (map.get("gender") != null && map.get("gender").contains(getString(R.string.nv))) {
//                sex = 2;
//            } else {
//                sex = 0;
//            }
//            openid = map.get("uid");
//            // openid = map.get("openid");
//            Log.d("tag111", openid);
//            nickname = map.get("name");
//            head_pic = map.get("iconurl");
//            from = share_media.toString();
//            if (from != null && from.equals("WEIXIN")) {
//                ((LoginContract.Presenter) mPresenter).postThirdToLogin("", openid, nickname, head_pic, sex);
//            } else {
//                ((LoginContract.Presenter) mPresenter).postThirdToLogin(openid, "", nickname, head_pic, sex);
//            }
//        }
//
//        /**
//         * @desc 授权失败的回调
//         * @param platform 平台名称
//         * @param action 行为序号，开发者用不上
//         * @param t 错误原因
//         */
//        @Override
//        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            dismissLoadingDialog();
//            if (t.getMessage().contains(getString(R.string.authoriseErr3))) {
//                ViewInject.toast(getString(R.string.authoriseErr2));
//                return;
//            }
//            ViewInject.toast(getString(R.string.authoriseErr));
//            //   Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
//        }
//
//        /**
//         * @desc 授权取消的回调
//         * @param platform 平台名称
//         * @param action 行为序号，开发者用不上
//         */
//        @Override
//        public void onCancel(SHARE_MEDIA platform, int action) {
//            dismissLoadingDialog();
//            ViewInject.toast(getString(R.string.authoriseErr1));
//            //  Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
//        }
//    };
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        UMShareAPI.get(this).onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        UMShareAPI.get(this).release();
//    }
//
//
//    @Override
//    public void getSuccess(String s, int flag) {
//        Log.d("tag", s);
//        LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
//        PreferenceHelper.write(this, StringConstants.FILENAME, "accessToken", bean.getResult().getAccessToken());
//        PreferenceHelper.write(this, StringConstants.FILENAME, "expireTime", bean.getResult().getExpireTime());
//        PreferenceHelper.write(this, StringConstants.FILENAME, "refreshToken", bean.getResult().getRefreshToken());
//        PreferenceHelper.write(this, StringConstants.FILENAME, "auth_status", bean.getResult().getAuth_status());
//        PreferenceHelper.write(this, StringConstants.FILENAME, "userId", bean.getResult().getUserId());
//        PreferenceHelper.write(this, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
//        /**
//         * 发送消息
//         */
//        RxBus.getInstance().post(new MsgEvent<String>("RxBusLoginEvent"));
//        MobclickAgent.onProfileSignIn(et_accountNumber.getText().toString());
//        if (type != null && type.equals("personalCenter")) {
//            PreferenceHelper.write(this, StringConstants.FILENAME, "isAvatar", false);
//        } else {
//            PreferenceHelper.write(this, StringConstants.FILENAME, "isAvatar", true);
//        }
//        dismissLoadingDialog();
//        finish();
//    }
//
//    @Override
//    public void errorMsg(String msg, int flag) {
//        dismissLoadingDialog();
//        if (flag == 1 && msg.equals("4001")) {
//            Intent intent = new Intent(aty, BindPhoneActivity.class);
//            intent.putExtra("openid", openid);
//            intent.putExtra("from", from);
//            intent.putExtra("nickname", nickname);
//            intent.putExtra("head_pic", head_pic);
//            intent.putExtra("sex", sex);
//            showActivity(aty, intent);
//            return;
//        }
//        ViewInject.toast(msg);
//    }
//
//    @Override
//    public void setPresenter(LoginContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//    /**
//     * 监听EditText输入改变
//     */
//    public void changeInputView(EditText editText, View view) {
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (editText.getText().toString().length() > 0) {
//                    if (view != null) {
//                        view.setVisibility(View.VISIBLE);
//                    }
//                    if (et_accountNumber.getText().length() == 11 && et_pwd.getText().length() >= 6) {
//                        tv_login.setClickable(true);
//                        tv_login.setBackgroundResource(R.drawable.shape_login);
//                        tv_login.setTextColor(getResources().getColor(R.color.mainColor));
//                    } else {
//                        tv_login.setClickable(false);
//                        tv_login.setBackgroundResource(R.drawable.shape_login1);
//                        tv_login.setTextColor(getResources().getColor(R.color.mainColor));
//                    }
//                } else {
//                    if (view != null) {
//                        view.setVisibility(View.GONE);
//                    }
//                    tv_login.setClickable(false);
//                    tv_login.setBackgroundResource(R.drawable.shape_login1);
//                    tv_login.setTextColor(getResources().getColor(R.color.mainColor));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//    }
//

}
