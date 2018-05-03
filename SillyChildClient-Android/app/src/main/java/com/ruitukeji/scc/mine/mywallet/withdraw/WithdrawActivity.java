package com.ruitukeji.scc.mine.mywallet.withdraw;

import android.content.Intent;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.scc.R;
import com.ruitukeji.scc.constant.NumericConstants;
import com.ruitukeji.scc.constant.StringNewConstants;
import com.ruitukeji.scc.entity.UserInfoBean;
import com.ruitukeji.scc.entity.WithdrawBean;
import com.ruitukeji.scc.loginregister.LoginActivity;
import com.ruitukeji.scc.main.MainActivity;
import com.ruitukeji.scc.utils.DecimalLimit;

/**
 * 提现
 * Created by Administrator on 2017/9/2.
 */

public class WithdrawActivity extends BaseActivity implements WithdrawContract.View {
    private WithdrawContract.Presenter mPresenter;
    private String mymoney;

    @BindView(id = R.id.et_withdrawmoney)
    private EditText et_withdrawmoney;

    @BindView(id = R.id.tv_yue)
    private TextView tv_yue;

    @BindView(id = R.id.tv_withdrawall, click = true)
    private TextView tv_withdrawall;

    @BindView(id = R.id.ll_typealipy, click = true)
    private LinearLayout ll_typealipy;
    @BindView(id = R.id.iv_typealipy)
    private ImageView iv_typealipy;

    @BindView(id = R.id.ll_typeweixin, click = true)
    private LinearLayout ll_typeweixin;
    @BindView(id = R.id.iv_typeweixin)
    private ImageView iv_typeweixin;

    @BindView(id = R.id.et_accountnumber, click = true)
    private EditText et_accountnumber;

    @BindView(id = R.id.et_name, click = true)
    private EditText et_name;

    @BindView(id = R.id.tv_determine, click = true)
    private TextView tv_determine;
    private String withdrawtype;
    private DecimalLimit decimalLimit;
    private String mymoneyfmt;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdraw);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new WithdrawPresenter(this);
        mymoney = getIntent().getStringExtra("mymoney");
        mymoneyfmt = getIntent().getStringExtra("mymoneyfmt");
        tv_yue.setText(mymoneyfmt);
        decimalLimit = new DecimalLimit();
        et_withdrawmoney.setFilters(decimalLimit.getFilter(NumericConstants.DECIMAL_DIGITS));

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        changeImage(iv_typealipy);
        withdrawtype = StringNewConstants.ZhiFuBaoPay;

    }

    /**
     * 修改提现方式
     */
    private void changeImage(ImageView choosetype) {
        iv_typealipy.setImageResource(R.mipmap.mineaddress_unselectxxx);
        iv_typeweixin.setImageResource(R.mipmap.mineaddress_unselectxxx);
        choosetype.setImageResource(R.mipmap.mineaddress_selectxxx);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdraw), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_typealipy:
                changeImage(iv_typealipy);
                withdrawtype = StringNewConstants.ZhiFuBaoPay;
                break;
            case R.id.ll_typeweixin:
                changeImage(iv_typeweixin);
                withdrawtype = StringNewConstants.WeiXinPay;
                break;
            case R.id.tv_withdrawall:
                et_withdrawmoney.setText(mymoney);
                break;
            case R.id.tv_determine:
                mPresenter.getInfo(1);
                break;
        }
    }

    @Override
    public void setPresenter(WithdrawContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 1) {
            UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
            if (StringUtils.toDouble(userInfoBean.getResult().getUser_money()) != StringUtils.toDouble(mymoney)) {
                mymoney = userInfoBean.getResult().getUser_money();
                mymoneyfmt = userInfoBean.getResult().getUser_money_fmt();
                tv_yue.setText(mymoneyfmt);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragmentUserMoney", true);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money", userInfoBean.getResult().getUser_money());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money_fmt", userInfoBean.getResult().getUser_money());
            }
            mPresenter.doWithdraw(et_withdrawmoney.getText().toString(), mymoney, et_accountnumber.getText().toString(), et_name.getText().toString(), withdrawtype);
        } else {
            WithdrawBean wbean = (WithdrawBean) JsonUtil.json2Obj(success, WithdrawBean.class);
            if (wbean == null) {
                ViewInject.toast(getString(R.string.otherError));
                dismissLoadingDialog();
                return;
            }
            if (wbean.getResult() == null) {
                ViewInject.toast(getString(R.string.serverReturnsDataNull));
                dismissLoadingDialog();
                return;
            }
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragmentUserMoney", true);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money", wbean.getResult().getBalance());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money_fmt", getString(R.string.moneySign) + wbean.getResult().getBalance());
            et_withdrawmoney.setText(wbean.getResult().getBalance());
            Intent jumpintent = new Intent(this, WithdrawSuccessActivity.class);
            jumpintent.putExtra("withdrawalamount", wbean.getResult().getAmount());
            jumpintent.putExtra("currentbalance", wbean.getResult().getBalance());
            dismissLoadingDialog();
            showActivity(this, jumpintent);
            finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            finish();
            KJActivityStack.create().finishToThis(LoginActivity.class, MainActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        decimalLimit = null;
    }
}
