package com.yinglan.scc.mine.mywallet;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.UserInfoBean;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.main.MainActivity;
import com.yinglan.scc.mine.mywallet.accountdetails.AccountDetailsActivity;
import com.yinglan.scc.mine.mywallet.coupons.CouponsActivity;
import com.yinglan.scc.mine.mywallet.recharge.RechargeActivity;
import com.yinglan.scc.mine.mywallet.withdrawal.WithdrawalActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的钱包
 * Created by Administrator on 2017/9/2.
 */

public class MyWalletActivity extends BaseActivity implements MyWalletContract.View {

    private MyWalletContract.Presenter mPresenter;

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.ll_recharge, click = true)
    private LinearLayout ll_recharge;

    @BindView(id = R.id.ll_withdraw, click = true)
    private LinearLayout ll_withdraw;

    @BindView(id = R.id.ll_coupons, click = true)
    private LinearLayout ll_coupons;

    @BindView(id = R.id.ll_bankCard, click = true)
    private LinearLayout ll_bankCard;

    private String mymoney;
    private UserInfoBean userInfoBean;

    @BindView(id = R.id.tv_yue)
    private TextView tv_yue;
    private String accountname;
    private Intent jumpintent;
    private String mymoneyfmt;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mywallet);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyWalletPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        mPresenter.getInfo();
//        fitUserInfo();

    }

    private void fitUserInfo() {
        mymoney = PreferenceHelper.readString(aty, StringConstants.FILENAME, "user_money", "-1");
        mymoneyfmt = PreferenceHelper.readString(aty, StringConstants.FILENAME, "user_money_fmt", "-1");
        if (TextUtils.isEmpty(mymoneyfmt) || mymoneyfmt.equals("-1")) {
            showLoadingDialog(getString(R.string.dataLoad));
            mPresenter.getInfo();
            return;
        } else {
            tv_yue.setText(mymoneyfmt);
        }

        accountname = PreferenceHelper.readString(aty, StringConstants.FILENAME, "mobile", "-1");
        if (TextUtils.isEmpty(accountname)) {
            accountname = PreferenceHelper.readString(aty, StringConstants.FILENAME, "email", "-1");
            if (TextUtils.isEmpty(accountname) || accountname.equals("-1")) {
                showLoadingDialog(getString(R.string.dataLoad));
                mPresenter.getInfo();
                return;
            }
        } else if (accountname.equals("-1")) {
            showLoadingDialog(getString(R.string.dataLoad));
            mPresenter.getInfo();
            return;
        }

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setBackgroundColor(Color.TRANSPARENT);
        titlebar.setLeftDrawable(getResources().getDrawable(R.mipmap.agreement));
        titlebar.getTitleCtv().setTextColor(getResources().getColor(R.color.whiteColors));
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.whiteColors));
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickRightCtv() {
                showActivity(aty, AccountDetailsActivity.class);
            }

            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                finish();
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myWallet), getString(R.string.accountDetails), R.id.titlebar, simpleDelegate);
    }

    /**
     * 用户信息本地化
     */
    private void saveUserInfo() {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "user_id", userInfoBean.getResult().getUser_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "email", userInfoBean.getResult().getEmail());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", userInfoBean.getResult().getSex());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "birthday", userInfoBean.getResult().getBirthday() + "");
        PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money", userInfoBean.getResult().getUser_money());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money_fmt", userInfoBean.getResult().getUser_money_fmt());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "countroy_code", userInfoBean.getResult().getCountroy_code());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", userInfoBean.getResult().getMobile());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "head_pic", userInfoBean.getResult().getHead_pic());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", userInfoBean.getResult().getNickname());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "level", userInfoBean.getResult().getLevel());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "shz_code", userInfoBean.getResult().getShz_code());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "personalized_signature", userInfoBean.getResult().getPersonalized_signature());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "fans_num", userInfoBean.getResult().getFans_num());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "attention_num", userInfoBean.getResult().getAttention_num());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "good_num", userInfoBean.getResult().getGood_num());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "collection_num", userInfoBean.getResult().getCollection_num());
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_recharge:
                jumpintent = new Intent(this, RechargeActivity.class);
                jumpintent.putExtra("accountname", accountname);
                showActivity(this, jumpintent);
                break;
            case R.id.ll_withdraw:
                jumpintent = new Intent(this, WithdrawalActivity.class);
                jumpintent.putExtra("mymoney", mymoney);
                jumpintent.putExtra("mymoneyfmt", mymoneyfmt);
                showActivity(this, jumpintent);
                break;
            case R.id.ll_coupons:
                showActivity(this, CouponsActivity.class);
                break;
            case R.id.ll_bankCard:
                Intent intent1 = new Intent(aty, MainActivity.class);
                intent1.putExtra("newChageIcon", 2);
                showActivity(this, intent1);
                finish();
                break;
        }
    }

    @Override
    public void setPresenter(MyWalletContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
        mymoney = userInfoBean.getResult().getUser_money();
        mymoneyfmt = userInfoBean.getResult().getUser_money_fmt();
        tv_yue.setText(mymoneyfmt);
        if (TextUtils.isEmpty(userInfoBean.getResult().getMobile())) {
            accountname = userInfoBean.getResult().getUser_id() + "";
        } else {
            accountname = userInfoBean.getResult().getMobile();
        }
        saveUserInfo();
        dismissLoadingDialog();


    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        tv_yue.setText("");
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this, LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLoadingDialog(getString(R.string.dataLoad));
        mPresenter.getInfo();
    }
}
