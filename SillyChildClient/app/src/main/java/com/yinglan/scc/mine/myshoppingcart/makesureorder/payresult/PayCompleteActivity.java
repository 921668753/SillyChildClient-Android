package com.yinglan.scc.mine.myshoppingcart.makesureorder.payresult;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.main.MainActivity;
import com.yinglan.scc.mine.myorder.MyOrderActivity;
import com.yinglan.scc.mine.myshoppingcart.MyShoppingCartActivity;
import com.yinglan.scc.mine.myshoppingcart.makesureorder.MakeSureOrderActivity;
import com.yinglan.scc.mine.myshoppingcart.makesureorder.PaymentOrderActivity;

/**
 * 支付完成/支付失败
 */
public class PayCompleteActivity extends BaseActivity {

    /**
     * 支付状态
     */
    @BindView(id = R.id.tv_payStatus)
    private TextView tv_payStatus;

    @BindView(id = R.id.img_pay)
    private ImageView img_pay;

    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.tv_consignee)
    private TextView tv_consignee;

    @BindView(id = R.id.tv_phone)
    private TextView tv_phone;

    @BindView(id = R.id.tv_deliveryAddress)
    private TextView tv_deliveryAddress;

    /**
     * 支付金额
     */
    @BindView(id = R.id.tv_payMoney)
    private TextView tv_payMoney;

    /**
     * 查看订单
     */
    @BindView(id = R.id.tv_checkOrder, click = true)
    private TextView tv_checkOrder;

    /**
     * 返回首页
     */
    @BindView(id = R.id.tv_returnHomePage, click = true)
    private TextView tv_returnHomePage;

    private String order_id;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paycomplete);
    }

    @Override
    public void initData() {
        super.initData();
        order_id = getIntent().getStringExtra("order_id");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (getIntent().getIntExtra("order_status", 0) == 1) {
            img_pay.setImageResource(R.mipmap.pay_success_icon);
            tv_payStatus.setText(getString(R.string.alipay_succeed));
        } else {
            img_pay.setImageResource(R.mipmap.pay_failure_icon);
            tv_payStatus.setText(getString(R.string.pay_error));
            tv_returnHomePage.setText(getString(R.string.payAgain));
        }
        tv_orderNumber.setText(getIntent().getStringExtra("orderCode"));
        tv_consignee.setText(getIntent().getStringExtra("name"));
        tv_phone.setText(getIntent().getStringExtra("mobile"));
        tv_deliveryAddress.setText(getIntent().getStringExtra("address"));
        tv_payMoney.setText(getIntent().getStringExtra("money"));
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.paySuccess), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_checkOrder:
                KJActivityStack.create().finishActivity(MyShoppingCartActivity.class);
                KJActivityStack.create().finishActivity(MakeSureOrderActivity.class);
                KJActivityStack.create().finishActivity(PaymentOrderActivity.class);
                Intent intent = new Intent(aty, MyOrderActivity.class);
                intent.putExtra("chageIcon", 0);
                skipActivity(aty, intent);
                break;
            case R.id.tv_returnHomePage:
                if (tv_returnHomePage.getText().toString().trim().contains(getString(R.string.payAgain))) {
                    finish();
                    return;
                }
                Intent intent1 = new Intent(aty, MainActivity.class);
                intent1.putExtra("newChageIcon", 0);
                showActivity(aty, intent1);
                KJActivityStack.create().finishOthersActivity(MainActivity.class);
                break;
        }
    }

}
