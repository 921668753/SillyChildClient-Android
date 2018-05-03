package com.ruitukeji.scc.mine.mywallet.withdraw;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.ruitukeji.scc.R;

/**
 * 提现成功
 * Created by Administrator on 2017/9/2.
 */

public class WithdrawSuccessActivity extends BaseActivity {

    @BindView(id=R.id.tv_withdrawalamount)
    private TextView tv_withdrawalamount;

    @BindView(id=R.id.tv_currentbalance)
    private TextView tv_currentbalance;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdrawsuccess);
    }

    @Override
    public void initData() {
        super.initData();
        tv_withdrawalamount.setText(getIntent().getStringExtra("withdrawalamount"));
        tv_currentbalance.setText(getIntent().getStringExtra("currentbalance"));
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdraw),true, R.id.titlebar);
    }

}