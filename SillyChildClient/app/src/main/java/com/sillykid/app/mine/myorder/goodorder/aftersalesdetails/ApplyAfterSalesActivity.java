package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;

/**
 * 申请售后
 */
public class ApplyAfterSalesActivity extends BaseActivity implements ApplyAfterSalesContract.View {

    /**
     * 售后类型
     */
    @BindView(id = R.id.ll_afterType)
    private LinearLayout ll_afterType;

    @BindView(id = R.id.tv_afterType)
    private TextView tv_afterType;

    /**
     * 退款金额
     */
    @BindView(id = R.id.ll_refundAmount)
    private LinearLayout ll_refundAmount;

    @BindView(id = R.id.tv_refundAmount, click = true)
    private TextView tv_refundAmount;

    /**
     * 售后原因
     */
    @BindView(id = R.id.ll_afterWhy)
    private LinearLayout ll_afterWhy;

    @BindView(id = R.id.tv_afterWhy, click = true)
    private TextView tv_afterWhy;

    /**
     * 提交
     */
    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;

    private String order_id;
    private String good_id;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_applyaftersales);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ApplyAfterSalesPresenter(this);
        order_id = getIntent().getStringExtra("order_id");
        good_id = getIntent().getStringExtra("good_id");
        showLoadingDialog(getString(R.string.dataLoad));
        ((ApplyAfterSalesContract.Presenter) mPresenter).getOrderRefund("207");
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.applyAfterSales), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(ApplyAfterSalesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }
}
