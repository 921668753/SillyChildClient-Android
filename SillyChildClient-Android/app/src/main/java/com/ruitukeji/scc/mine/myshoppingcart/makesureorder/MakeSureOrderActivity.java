package com.ruitukeji.scc.mine.myshoppingcart.makesureorder;

import android.util.TypedValue;
import android.view.View;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.ruitukeji.scc.R;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的购物车中的确认订单
 * Created by Administrator on 2017/9/2.
 */

public class MakeSureOrderActivity extends BaseActivity {
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_makesureorder);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.confirmOrder), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }
}
