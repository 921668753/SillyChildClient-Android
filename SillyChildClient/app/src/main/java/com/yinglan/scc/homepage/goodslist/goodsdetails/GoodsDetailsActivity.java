package com.yinglan.scc.homepage.goodslist.goodsdetails;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.WebViewLayout1;
import com.kymjs.common.StringUtils;
import com.yinglan.scc.R;
import com.yinglan.scc.homepage.goodslist.goodsdetails.comments.CommentsActivity;
import com.yinglan.scc.homepage.goodslist.shop.ShopActivity;
import com.yinglan.scc.mine.myorder.goodorder.orderdetails.OrderDetailsActivity;

/**
 * 商品详情
 * Created by Admin on 2017/8/24.
 */

public class GoodsDetailsActivity extends BaseActivity implements GoodsDetailsContract.View, WebViewLayout1.WebViewCallBack {

    @BindView(id = R.id.web_content)
    private WebViewLayout1 webViewLayout;

    @BindView(id = R.id.ll_shop, click = true)
    private LinearLayout ll_shop;


    @BindView(id = R.id.ll_customerService, click = true)
    private LinearLayout ll_customerService;


    @BindView(id = R.id.ll_follow, click = true)
    private LinearLayout ll_follow;

    @BindView(id = R.id.tv_buyNow, click = true)
    private TextView tv_buyNow;

    @BindView(id = R.id.tv_addShoppingCart, click = true)
    private TextView tv_addShoppingCart;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_goodsdetails);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new GoodsDetailsPresenter(this);
    }

    /**
     * 初始化控件
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        initView();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.orderDetails), true, R.id.titlebar);
    }

    public void initView() {
        webViewLayout.setTitleVisibility(false);
//        if (!StringUtils.isEmpty(url)) {
//            webViewLayout.loadUrl(url);
//        }
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_shop:
                Intent shopIntent = new Intent(aty, ShopActivity.class);
                shopIntent.putExtra("", "");
                showActivity(aty, shopIntent);
                break;
            case R.id.ll_customerService:

                break;
            case R.id.ll_follow:

                break;
            case R.id.tv_buyNow:
                Intent buyNowIntent = new Intent(aty, OrderDetailsActivity.class);
                buyNowIntent.putExtra("", "");
                showActivity(aty, buyNowIntent);
                break;
            case R.id.tv_addShoppingCart:

                break;
        }
    }

    @Override
    public void setPresenter(GoodsDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {

    }

    @Override
    public void backOnclick(String id) {
        if (StringUtils.isEmpty(id)) {
            Intent intent = new Intent(aty, CommentsActivity.class);
            intent.putExtra("", "");
            showActivity(aty, intent);
        } else {
            Intent intent = new Intent(aty, GoodsDetailsActivity.class);
            // intent.putExtra("good_id", listbean.get(postion));
            showActivity(aty, intent);
        }
    }

    @Override
    public void loadFailedError() {

    }
}
