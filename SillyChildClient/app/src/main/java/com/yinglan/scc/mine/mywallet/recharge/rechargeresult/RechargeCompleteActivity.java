package com.yinglan.scc.mine.mywallet.recharge.rechargeresult;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scc.R;

/**
 * 充值成功/充值失败
 */
public class RechargeCompleteActivity extends BaseActivity {

    @BindView(id = R.id.tv_returnHomePage, click = true)
    private TextView tv_returnHomePage;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_rechargecomplete);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.rechargeSuccess), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_returnHomePage:

                break;
        }
    }


}
