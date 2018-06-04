package com.yinglan.scc.mine.myshoppingcart.makesureorder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.TimeCount;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.entity.mine.mywallet.recharge.AlipayBean;
import com.yinglan.scc.entity.mine.mywallet.recharge.WeChatPayBean;
import com.yinglan.scc.loginregister.LoginActivity;
import com.yinglan.scc.mine.myshoppingcart.makesureorder.payresult.PayCompleteActivity;
import com.yinglan.scc.mine.mywallet.recharge.RechargeActivity;
import com.yinglan.scc.utils.PayUtils;

/**
 * 支付订单
 */
public class PaymentOrderActivity extends BaseActivity implements PaymentOrderContract.View, TimeCount.TimeCountCallBack {

    @BindView(id = R.id.tv_waitingPayment)
    public TextView tv_waitingPayment;
    @BindView(id = R.id.tv_lateCancelled)
    public TextView tv_lateCancelled;

    /**
     * 余额
     */
    @BindView(id = R.id.tv_currentBalance)
    public TextView tv_currentBalance;

    @BindView(id = R.id.img_currentBalance, click = true)
    public ImageView img_currentBalance;

    /**
     * 微信
     */
    @BindView(id = R.id.img_weChatPay, click = true)
    public ImageView img_weChatPay;

    /**
     * 支付宝
     */
    @BindView(id = R.id.img_alipay, click = true)
    public ImageView img_alipay;

    /**
     * 银联
     */
    @BindView(id = R.id.img_unionpayPay, click = true)
    public ImageView img_unionpayPay;

    /**
     * 合计价格
     */
    @BindView(id = R.id.tv_money)
    public TextView tv_money;

    /**
     * 确认订单
     */
    @BindView(id = R.id.tv_confirmPayment, click = true)
    public TextView tv_confirmPayment;

    private String order_id;

    private TimeCount time;

    private String pay_type = "";

    private PayUtils payUtils = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paymentorder);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PaymentOrderPresenter(this);
        order_id = getIntent().getStringExtra("order_id");
        payUtils = new PayUtils(this);
        time = new TimeCount();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        String money = getIntent().getStringExtra("money");
        tv_money.setText(money);
        String last_time = getIntent().getStringExtra("last_time");
        time.setTimeCountCallBack(this);
        time.setMillisCountDown(StringUtils.toLong(last_time) * 1000, 1000);
        time.start();
        String balance = getIntent().getStringExtra("balance");
        tv_currentBalance.setText(MathUtil.keepTwo(StringUtils.toDouble(balance)));
        if (StringUtils.toDouble(tv_money.getText().toString().trim()) > StringUtils.toDouble(tv_currentBalance.getText().toString().trim())) {
            clearImg(img_weChatPay);
            pay_type = "weixin";
        } else {
            clearImg(img_currentBalance);
            pay_type = "qianbao";
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_currentBalance:
                if (StringUtils.toDouble(tv_money.getText().toString().trim()) > StringUtils.toDouble(tv_currentBalance.getText().toString().trim())) {
                    return;
                }
                clearImg(img_currentBalance);
                pay_type = "qianbao";
                break;
            case R.id.img_weChatPay:
                clearImg(img_weChatPay);
                pay_type = "weixin";
                break;
            case R.id.img_alipay:
                clearImg(img_alipay);
                pay_type = "zhifubao";
                break;
            case R.id.img_unionpayPay:
                clearImg(img_unionpayPay);
                pay_type = "yinlian";
                break;
            case R.id.tv_confirmPayment:
                showLoadingDialog(getString(R.string.paymentLoad));
                ((PaymentOrderContract.Presenter) mPresenter).getOnlinePay(order_id, pay_type);
                break;
        }
    }

    /**
     * 更改图片
     */
    private void clearImg(ImageView img) {
        img_currentBalance.setImageResource(R.mipmap.top_ups_unselected);
        img_weChatPay.setImageResource(R.mipmap.top_ups_unselected);
        img_alipay.setImageResource(R.mipmap.top_ups_unselected);
        img_unionpayPay.setImageResource(R.mipmap.top_ups_unselected);
        img.setImageResource(R.mipmap.top_ups_selected);
    }


    @Override
    public void setPresenter(PaymentOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (pay_type.contains("qianbao")) {
            jumpPayComplete(1);
        } else if (pay_type.contains("weixin")) {
            WeChatPayBean weChatPayBean = (WeChatPayBean) JsonUtil.getInstance().json2Obj(success, WeChatPayBean.class);
            if (weChatPayBean.getData() == null || StringUtils.isEmpty(weChatPayBean.getData().getAppid())) {
                dismissLoadingDialog();
                ViewInject.toast(getString(R.string.payParseError));
                return;
            }
            if (payUtils == null) {
                payUtils = new PayUtils(this);
            }
            dismissLoadingDialog();
            payUtils.doPayment(weChatPayBean.getData().getAppid(), weChatPayBean.getData().getPartnerid(), weChatPayBean.getData().getPrepayid(), weChatPayBean.getData().getPackageX(), weChatPayBean.getData().getNoncestr(), weChatPayBean.getData().getTimestamp(), weChatPayBean.getData().getSign());
        } else if (pay_type.contains("zhifubao")) {
            AlipayBean alipayBean = (AlipayBean) JsonUtil.getInstance().json2Obj(success, AlipayBean.class);
            if (alipayBean.getData() == null || StringUtils.isEmpty(alipayBean.getData().getOrderString())) {
                dismissLoadingDialog();
                ViewInject.toast(getString(R.string.payParseError));
                return;
            }
            if (payUtils == null) {
                payUtils = new PayUtils(this);
            }
            dismissLoadingDialog();
            payUtils.doPay(alipayBean.getData().getOrderString());
        } else if (pay_type.contains("yinlian")) {
            //从网络开始,获取交易流水号即TN（通过网络请求从后台获取到TN）
            if (payUtils == null) {
                payUtils = new PayUtils(this);
            }
            //   payUtils.doStartUnionPayPlugin(submitBouncedDialog, tn, MODE);
        }

    }

    /**
     * 跳转支付完成页面
     *
     * @param order_status 1成功 0失败
     */
    public void jumpPayComplete(int order_status) {
        Intent intent = new Intent(aty, PayCompleteActivity.class);
        intent.putExtra("order_status", order_status);
        intent.putExtra("order_id", order_id);
        intent.putExtra("orderCode", getOrderCode());
        intent.putExtra("name", getName());
        intent.putExtra("mobile", getMobile());
        intent.putExtra("address", getAddress());
        intent.putExtra("money", getPayMoney());
        showActivity(aty, intent);
    }

    public String getOrderCode() {
        return getIntent().getStringExtra("orderCode");
    }

    public String getName() {
        return getIntent().getStringExtra("name");
    }

    public String getMobile() {
        return getIntent().getStringExtra("mobile");
    }

    public String getAddress() {
        return getIntent().getStringExtra("address");
    }

    public String getOrderId() {
        return order_id;
    }

    public String getPayMoney() {
        return getIntent().getStringExtra("money");
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        if (pay_type.contains("qianbao")) {
            jumpPayComplete(0);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void onFinishTime() {
        tv_waitingPayment.setText(getString(R.string.tradingClosed));
        tv_lateCancelled.setVisibility(View.GONE);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        String millisUntilFinish = "";
        long minute = millisUntilFinished / 60000;
        long seconds = millisUntilFinished % 60000;
        if (minute > 0) {
            millisUntilFinish = minute + getString(R.string.minute) + seconds + getString(R.string.seconds);
        } else {
            millisUntilFinish = seconds + getString(R.string.seconds);
        }
        tv_lateCancelled.setText(getString(R.string.lateCancelled) + millisUntilFinish + getString(R.string.lateCancelled1));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            ViewInject.toast(getString(R.string.alipay_succeed));
            jumpPayComplete(1);
        } else if (str.equalsIgnoreCase("fail")) {
            ViewInject.toast(getString(R.string.alipay_order_error));
            jumpPayComplete(0);
        } else if (str.equalsIgnoreCase("cancel")) {
            ViewInject.toast(getString(R.string.alipay_order_cancel));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        payUtils = null;
        if (time != null) {
            time.onFinish();
            time.cancel();
        }
        time = null;
    }
}
