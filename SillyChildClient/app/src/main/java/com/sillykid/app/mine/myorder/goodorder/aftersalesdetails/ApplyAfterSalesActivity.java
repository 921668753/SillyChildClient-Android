package com.sillykid.app.mine.myorder.goodorder.aftersalesdetails;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;

/**
 * 申请售后
 */
public class ApplyAfterSalesActivity extends BaseActivity {


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


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_applyaftersales);
    }
}
