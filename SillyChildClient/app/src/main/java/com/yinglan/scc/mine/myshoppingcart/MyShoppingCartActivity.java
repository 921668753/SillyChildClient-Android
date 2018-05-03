package com.yinglan.scc.mine.myshoppingcart;

import android.util.TypedValue;
import android.view.View;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.yinglan.scc.R;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的购物车
 * Created by Administrator on 2017/9/2.
 */

public class MyShoppingCartActivity extends BaseActivity {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myshoppingcart);
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
        titlebar.setTitleText(R.string.myShoppingCart);
        titlebar.setRightText(R.string.edit);
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();

            }
        };
        titlebar.setDelegate(simpleDelegate);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }
}
