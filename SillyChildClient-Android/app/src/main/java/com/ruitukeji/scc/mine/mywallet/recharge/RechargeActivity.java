package com.ruitukeji.scc.mine.mywallet.recharge;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import com.ruitukeji.scc.entity.AlipayBean;
import com.ruitukeji.scc.loginregister.LoginActivity;
import com.ruitukeji.scc.main.MainActivity;
import com.ruitukeji.scc.utils.DecimalLimit;
import com.ruitukeji.scc.utils.PayUtils;

/**
 * 充值
 * Created by Administrator on 2017/9/2.
 */

public class RechargeActivity extends BaseActivity implements RechargeContract.View, View.OnTouchListener {

    private RechargeContract.Presenter mPresenter;

    @BindView(id = R.id.et_phone)
    private EditText et_phone;

    @BindView(id = R.id.btn_leftup, click = true)
    private Button btn_leftup;

    @BindView(id = R.id.btn_middleup, click = true)
    private Button btn_middleup;

    @BindView(id = R.id.btn_rightup, click = true)
    private Button btn_rightup;

    @BindView(id = R.id.btn_leftmiddle, click = true)
    private Button btn_leftmiddle;

    @BindView(id = R.id.btn_middlemiddle, click = true)
    private Button btn_middlemiddle;

    @BindView(id = R.id.btn_rightmiddle, click = true)
    private Button btn_rightmiddle;

    @BindView(id = R.id.btn_leftdown, click = true)
    private Button btn_leftdown;

    @BindView(id = R.id.btn_middledown, click = true)
    private Button btn_middledown;

    @BindView(id = R.id.et_rightdown, click = true)
    private EditText et_rightdown;

    @BindView(id = R.id.ll_payweixin, click = true)
    private LinearLayout ll_payweixin;
    @BindView(id = R.id.iv_payweixin)
    private ImageView iv_payweixin;

    @BindView(id = R.id.ll_payzfb, click = true)
    private LinearLayout ll_payzfb;
    @BindView(id = R.id.iv_payzfb)
    private ImageView iv_payzfb;

    @BindView(id = R.id.tv_determinepay, click = true)
    private TextView tv_determinepay;
    private double rechargemoney = 0;
    private String payWay;
    private String choosemoney;
    private InputMethodManager inputmanager;
    private PayUtils payUtils;
    private DecimalLimit decimalLimit;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_recharge);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new RechargePresenter(this);
        inputmanager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        decimalLimit = new DecimalLimit();
        et_rightdown.setFilters(decimalLimit.getFilter(NumericConstants.DECIMAL_DIGITS));
        et_rightdown.setOnTouchListener(this);
        et_phone.setOnTouchListener(this);
        clearBtns(btn_leftup);
        clearimg(iv_payweixin);
        payWay = StringNewConstants.WeiXinPay;
        et_phone.setText(getIntent().getStringExtra("accountname"));

    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.accountRecharge), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.btn_leftup:
                clearBtns(btn_leftup);
                break;
            case R.id.btn_middleup:
                clearBtns(btn_middleup);
                break;
            case R.id.btn_rightup:
                clearBtns(btn_rightup);
                break;
            case R.id.btn_leftmiddle:
                clearBtns(btn_leftmiddle);
                break;
            case R.id.btn_middlemiddle:
                clearBtns(btn_middlemiddle);
                break;
            case R.id.btn_rightmiddle:
                clearBtns(btn_rightmiddle);
                break;
            case R.id.btn_leftdown:
                clearBtns(btn_leftdown);
                break;
            case R.id.btn_middledown:
                clearBtns(btn_middledown);
                break;
            case R.id.ll_payweixin:
                clearimg(iv_payweixin);
                payWay = StringNewConstants.WeiXinPay;
                break;
            case R.id.ll_payzfb:
                clearimg(iv_payzfb);
                payWay = StringNewConstants.ZhiFuBaoPay;
                break;

            case R.id.tv_determinepay:
                showLoadingDialog(getResources().getString(R.string.submissionLoad));
                if (!TextUtils.isEmpty(et_rightdown.getText().toString())) {
                    rechargemoney = StringUtils.toDouble(et_rightdown.getText().toString());
                }
                mPresenter.doRecharge(et_phone.getText().toString(), payWay, rechargemoney);
                break;
        }
    }

    private void clearBtns(Button btn) {
        btn_leftup.setBackgroundResource(R.drawable.shape_addshoppingcarte);
        btn_leftup.setTextColor(getResources().getColor(R.color.textColor));
        btn_middleup.setBackgroundResource(R.drawable.shape_addshoppingcarte);
        btn_middleup.setTextColor(getResources().getColor(R.color.textColor));
        btn_rightup.setBackgroundResource(R.drawable.shape_addshoppingcarte);
        btn_rightup.setTextColor(getResources().getColor(R.color.textColor));
        btn_leftmiddle.setBackgroundResource(R.drawable.shape_addshoppingcarte);
        btn_leftmiddle.setTextColor(getResources().getColor(R.color.textColor));
        btn_middlemiddle.setBackgroundResource(R.drawable.shape_addshoppingcarte);
        btn_middlemiddle.setTextColor(getResources().getColor(R.color.textColor));
        btn_rightmiddle.setBackgroundResource(R.drawable.shape_addshoppingcarte);
        btn_rightmiddle.setTextColor(getResources().getColor(R.color.textColor));
        btn_leftdown.setBackgroundResource(R.drawable.shape_addshoppingcarte);
        btn_leftdown.setTextColor(getResources().getColor(R.color.textColor));
        btn_middledown.setBackgroundResource(R.drawable.shape_addshoppingcarte);
        btn_middledown.setTextColor(getResources().getColor(R.color.textColor));

        if (btn == null) {
            et_rightdown.setCursorVisible(true);
        } else {
            inputmanager.hideSoftInputFromWindow(et_rightdown.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            et_rightdown.setText("");
            et_rightdown.setCursorVisible(false);
            et_phone.setCursorVisible(false);
            btn.setBackgroundResource(R.drawable.shape_login1);
            btn.setTextColor(getResources().getColor(R.color.whiteColors));
            choosemoney = btn.getText().toString();
            rechargemoney = Double.parseDouble(choosemoney.substring(0, choosemoney.length() - 1));
        }
    }

    private void clearimg(ImageView img) {
        iv_payweixin.setImageResource(R.mipmap.mineaddress_unselectxxx);
        iv_payzfb.setImageResource(R.mipmap.mineaddress_unselectxxx);
        img.setImageResource(R.mipmap.mineaddress_selectxxx);
    }

    @Override
    public void setPresenter(RechargeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        AlipayBean alipaybean = (AlipayBean) JsonUtil.getInstance().json2Obj(success, AlipayBean.class);
        if (alipaybean==null||alipaybean.getResult()==null){
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.payParseError));
        }else{
            if (!TextUtils.isEmpty(payWay) && payWay.equals(StringNewConstants.WeiXinPay)) {
                AlipayBean.ResultBean.WxPayParamsBean wxPayParamsBean = alipaybean.getResult().getWxPayParams();
                if (wxPayParamsBean==null){
                    dismissLoadingDialog();
                    ViewInject.toast(getString(R.string.payParseError));
                }else{
                    if (payUtils == null) payUtils = new PayUtils(this, RechargeActivity.class);
                    dismissLoadingDialog();
                    payUtils.doPayment(wxPayParamsBean.getAppid(), wxPayParamsBean.getPartnerid(), wxPayParamsBean.getPrepayid(), wxPayParamsBean.getPackageX(), wxPayParamsBean.getNoncestr(), wxPayParamsBean.getTimestamp(), wxPayParamsBean.getSign());
                }
               } else if (!TextUtils.isEmpty(payWay) && payWay.equals(StringNewConstants.ZhiFuBaoPay)) {
                if (payUtils == null) payUtils = new PayUtils(this, RechargeActivity.class);
                dismissLoadingDialog();
                payUtils.doPay(alipaybean.getResult().getAliPayParams());
            }
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
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
            switch (view.getId()) {
                case R.id.et_rightdown:
                    clearBtns(null);
                    et_rightdown.setCursorVisible(true);// 再次点击显示光标
                    break;
                case R.id.et_phone:
                    et_phone.setCursorVisible(true);// 再次点击显示光标
                    break;
            }

        }
        return false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        decimalLimit = null;
    }
}
