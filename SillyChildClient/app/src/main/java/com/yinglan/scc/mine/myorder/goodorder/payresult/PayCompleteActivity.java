package com.yinglan.scc.mine.myorder.goodorder.payresult;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scc.R;

/**
 * 支付完成/支付失败
 */
public class PayCompleteActivity extends BaseActivity {

    @BindView(id = R.id.tv_checkOrder, click = true)
    private TextView tv_checkOrder;

    @BindView(id = R.id.tv_returnHomePage, click = true)
    private TextView tv_returnHomePage;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paycomplete);
    }

    @Override
    public void initData() {
        super.initData();

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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.paySuccess), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_checkOrder:

                break;
            case R.id.tv_returnHomePage:

                break;
        }
    }









}
