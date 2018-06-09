package com.sillykid.app.mine.myorder.goodorder.ordertracking;

import android.view.View;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.sillykid.app.R;

/**
 * 订单跟踪
 * Created by Administrator on 2017/9/28.
 */

public class OrderTrackingActivity extends BaseActivity implements WebViewLayout.WebViewCallBack {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_helpcenter);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        // initTitle();
        webViewLayout.setTitleText(getString(R.string.orderTracking));
        webViewLayout.setBackImgResource(R.mipmap.back);
        webViewLayout.setWebViewCallBack(this);
        //   webViewLayout.loadUrl(URLConstants.ABOUTUSURL);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.orderTracking), true, R.id.titlebar);
    }

    @Override
    public void backOnclick() {
        finish();
    }

    @Override
    public void loadFailedError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }


}
