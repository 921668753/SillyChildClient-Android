package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myorder.goodorder.aftersalesdetails.AfterSalesDetailsBean;
import com.sillykid.app.loginregister.LoginActivity;

/**
 * 售后详情
 */
public class AfterSalesDetailsActivity extends BaseActivity implements AfterSalesDetailsContract.View {

    /**
     * 退款金额：
     */
    @BindView(id = R.id.tv_refundSuccess)
    private TextView tv_refundSuccess;

    /**
     * 服务详情
     */
    @BindView(id = R.id.ll_serviceDetails, click = true)
    private LinearLayout ll_serviceDetails;

    /**
     * 店铺名称：
     */
    @BindView(id = R.id.tv_shopName)
    private TextView tv_shopName;

    /**
     * 售后类型：退货退款
     */
    @BindView(id = R.id.tv_afterSalesType)
    private TextView tv_afterSalesType;

    /**
     * 退款金额：￥520.00
     */
    @BindView(id = R.id.tv_refundAmount)
    private TextView tv_refundAmount;

    /**
     * 退款金额：￥520.00
     */
    @BindView(id = R.id.tv_refundReason)
    private TextView tv_refundReason;

    /**
     * 商品名称：xxxxxx
     */
    @BindView(id = R.id.tv_goodName)
    private TextView tv_goodName;

    /**
     * 订单编号：xxxxxx
     */
    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    /**
     * 订单时间：2018.06.05  12:00:00
     */
    @BindView(id = R.id.tv_orderTime)
    private TextView tv_orderTime;

    private String order_id = "";

    private String good_id = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_aftersalesdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AfterSalesDetailsPresenter(this);
        order_id = getIntent().getStringExtra("order_id");
        good_id = getIntent().getStringExtra("good_id");
        showLoadingDialog(getString(R.string.dataLoad));
        ((AfterSalesDetailsContract.Presenter) mPresenter).getSellBackDetail(order_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_serviceDetails:
                Intent intent = new Intent(aty, ServiceDetailsActivity.class);
                intent.putExtra("order_id", order_id);
                showActivity(aty, intent);
                break;
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.afterSalesDetails), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(AfterSalesDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        AfterSalesDetailsBean afterSalesDetailsBean = (AfterSalesDetailsBean) JsonUtil.getInstance().json2Obj(success, AfterSalesDetailsBean.class);
        tv_refundSuccess.setText(getString(R.string.refundAmount1) + getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(afterSalesDetailsBean.getData().getAlltotal_pay())));

        tv_shopName.setText(getString(R.string.refundAmount1) + afterSalesDetailsBean.getData().getStore_name());

        tv_afterSalesType.setText(getString(R.string.afterType1) + afterSalesDetailsBean.getData().getRemark());

        tv_refundAmount.setText(getString(R.string.refundAmount1) + getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(afterSalesDetailsBean.getData().getAlltotal_pay())));

        tv_refundReason.setText(getString(R.string.refundReason) + afterSalesDetailsBean.getData().getReason());

        tv_goodName.setText(getString(R.string.productName) + afterSalesDetailsBean.getData().getGoodsNames());

        tv_orderNumber.setText(getString(R.string.orderNumber) + afterSalesDetailsBean.getData().getOrdersn());

        tv_orderTime.setText(getString(R.string.refundAmount1) + afterSalesDetailsBean.getData().getOrder_create_time());
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            //  ViewInject.toast(getString(R.string.reloginPrompting));
            skipActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
