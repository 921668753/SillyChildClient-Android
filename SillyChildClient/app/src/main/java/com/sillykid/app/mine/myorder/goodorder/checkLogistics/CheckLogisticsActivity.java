package com.sillykid.app.mine.myorder.goodorder.checkLogistics;

import android.view.View;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.sillykid.app.R;

/**
 * 查看物流
 * Created by Administrator on 2017/9/28.
 */

public class CheckLogisticsActivity extends BaseActivity {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_helpcenter);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        webViewLayout.setTitleVisibility(false);
        //   webViewLayout.loadUrl(URLConstants.ABOUTUSURL);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.aboutUs), true, R.id.titlebar);
    }

}
