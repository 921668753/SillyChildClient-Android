package com.yinglan.scc.mine.mywallet;

import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.mywallet.MyWalletBean;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.mine.mywallet.accountdetails.AccountDetailsActivity;
import com.yinglan.scc.mine.mywallet.coupons.CouponsActivity;
import com.yinglan.scc.mine.mywallet.mybankcard.MyBankCardActivity;
import com.yinglan.scc.mine.mywallet.recharge.RechargeActivity;
import com.yinglan.scc.mine.mywallet.withdrawal.WithdrawalActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的钱包
 * Created by Administrator on 2017/9/2.
 */

public class MyWalletActivity extends BaseActivity implements MyWalletContract.View {

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

    @BindView(id = R.id.tv_yue)
    private TextView tv_yue;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mywallet);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyWalletPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyWalletContract.Presenter) mPresenter).getMyWallet();
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
        titlebar.setLeftDrawable(getResources().getDrawable(R.mipmap.loginback));
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

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_recharge:
                showActivity(this, RechargeActivity.class);
                break;
            case R.id.ll_withdraw:
                Intent withdrawalIntent = new Intent(aty, WithdrawalActivity.class);
                withdrawalIntent.putExtra("bankCardName", "");
                withdrawalIntent.putExtra("bankCardNun", "");
                withdrawalIntent.putExtra("bankCardId", "");
                showActivity(aty, withdrawalIntent);
                break;
            case R.id.ll_coupons:
                showActivity(this, CouponsActivity.class);
                break;
            case R.id.ll_bankCard:
                showActivity(aty, MyBankCardActivity.class);
                break;
        }
    }

    @Override
    public void setPresenter(MyWalletContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            MyWalletBean myWalletBean = (MyWalletBean) JsonUtil.getInstance().json2Obj(success, MyWalletBean.class);
            if (!StringUtils.isEmpty(myWalletBean.getData().getBalance())) {
                tv_yue.setText(getString(R.string.renminbi) + myWalletBean.getData().getBalance());
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusWithdrawalEvent") || ((String) msgEvent.getData()).equals("RxBusAddBankCardEvent")) {
            ((MyWalletContract.Presenter) mPresenter).getMyWallet();
        }
    }

}
